from requests import get
from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.chrome.options import Options

import re


result = {}

def get_local_foods() :
    base_url = f"https://namu.wiki/w/%ED%95%9C%EA%B5%AD%20%EC%9A%94%EB%A6%AC/%EC%A7%80%EC%97%AD%EB%B3%84#s-12"
    response = get(base_url)
    if response.status_code !=200:
        print("can't request website")
    else :
        options = webdriver.ChromeOptions()
        # 창 숨기는 옵션 추가
        options.add_argument("headless")
        options.add_argument('--log-level=3') # 브라우저 로그 레벨을 낮춤
        options.add_argument('--disable-loging') # 로그를 남기지 않음
        browser = webdriver.Chrome(options=options)
        browser.get(base_url)
        soup = BeautifulSoup(browser.page_source,"html.parser")
        
        #지역A조 사울,인천,경기

        local_ul = soup.find_all('div',class_='XeGWRsxd')
        local_titles = soup.find_all('h2',class_='_9tFl4Dx4')

        for n in range(2,10) :
            get_local_food(local_ul,local_titles,n)

        get_jeju_food(local_ul)

        return result

        #seoul, incheon, suwon, yongin, uijeongbu, paju, pyeongtaek, pocheon, gwangju, ansan, ext
        

def get_local_food(local_ul,local_titles,n) :    
    #지역n조
    local_a = local_ul[n].find('ul',class_='CS4El3r0',recursive=False)
    a_list  = local_a.find_all('li',recursive=False)       
    #n조 each
    for a in a_list :
        #n조 지역이름
        a_title = a.find('div',class_='hQuAzr23',recursive=False).get_text(strip=True)
        if a_title == '지역 전체' : 
            a_title = re.sub(r'\[.*\]', '', local_titles[n].get_text()).strip().split('.')[1].strip()
        
        #해당 지역 음식들
        fl = a.find('ul',class_='CS4El3r0')
        if fl!=None :
            food_list = fl.find_all('li',recursive=False)
            foods = []
            for sl in food_list :
                #요소에서 텍스트를 추출하고 정규식으로 정형화함
                food = sl.find('div',class_='hQuAzr23').get_text(strip=True)
                text1 = re.sub(r'\[\d+\]', '', food)
                if ':' in text1 :
                    foods.append(text1.split(':')[0])
                else : 
                    foods.append(text1)
            result[a_title] = foods

def get_jeju_food(local_ul) :

    #제주
    local_a = local_ul[10].find('ul',class_='CS4El3r0',recursive=False)
    a_list  = local_a.find_all('li',recursive=False)
    food_list = []       
    #n조 each
    for a in a_list :
        #n조 음식이름
        food = a.find('div',class_='hQuAzr23',recursive=False).get_text(strip=True)
        text = re.sub(r'\[\d+\]', '', food)
        food_list.append(text)
    result['제주도'] = food_list


#잡코리아 페이지갯수구하기 
def get_page_count(keyword) :
    base_url = f"https://www.jobkorea.co.kr/Search/?stext={keyword}&local=I000&careerType=1%2C4&edu=3%2C0"
    response = get(base_url)
    if response.status_code != 200:
        print("can't request website")
    else : 
        soup = BeautifulSoup(response.text,"html.parser")
        item_count = soup.find('p',class_='filter-text').find('strong').string
        print(item_count)
        
        if item_count != None :
            page_count = 0
            item_c = int(item_count)
            if item_c % 20 ==0 :
                page_count = item_c/20
            else : 
                page_count = int((item_c/20)+1)
            return page_count

#잡코리아 직업 구하기
def extract_jobkorea_jobs(keyword) :
    pages = get_page_count(keyword)
    print("Found", pages, "pages")
    your_pages = int(input(f'총 {pages} 중 몇 페이지까지 원해?'))

    if your_pages>pages :
        your_pages = pages
    
    results = []
    page_list = range(your_pages)
    for page in page_list:
        url = f"https://www.jobkorea.co.kr/Search/?stext={keyword}&local=I000&careerType=1%2C4&edu=3%2C0&tabType=recruit&Page_No={page+1}"
        response = get(url)
        if response.status_code != 200:
            print("can't request website")
        else : 
            soup = BeautifulSoup(response.text, "html.parser")
            #여기 고쳐야됨 
            jobs = soup.find_all('div',class_='list-default')
            job_list = jobs[0].find_all('li')
            for job in job_list:
                option = job.find('p',class_='option')
                company = job.select_one('.post-list-corp>a').string.replace(',',' ')
                location = option.find('span', class_='long')
                date = option.find('span', class_='date')
                link = job.select_one('.post-list-info>a')['href']
                title = job.select_one('.post-list-info>a')
                rate = get_rate(company)

                job_data = {
                    "link" : f"https://www.jobkorea.co.kr{link}",
                    "company" : company,
                    "location" : location.string.replace(',',' '),
                    "position" : title.get_text().replace('\r\n','').replace(',',' ').strip(),
                    "date" : date.string.replace(',',' '),
                    "rate" : rate
                }
                results.append(job_data)
            
    return results


        # for job_section in jobs:
        #     job_posts = job_section.find_all('li')
        #     job_posts.pop(-1)
        #     for post in job_posts:
        #         anchors = post.find_all('a')
        #         anchor = anchors[1]
        #         link = anchor['href']
        #         company, kind, region = anchor.find_all('span', class_="company")
        #         title = anchor.find('span', class_="title")
        #         job_data = {
        #             "link" : f"https://weworkremotely.com{link}",
        #             "company" : company.string.replace(',',' '),
        #             "position" : region.string.replace(',',' '),
        #             "location" : title.string.replace(',',' ')
        #         }
        #         results.append(job_data)
        # return results



#잡플래닛 점수 구하기봇
def get_rate(keyword) :
    url = f'https://www.jobplanet.co.kr/search?query={keyword}'    
    response = get(url)
    if(response.status_code != 200) :
        print('fail')
    else :
        options = webdriver.ChromeOptions()
        # 창 숨기는 옵션 추가
        options.add_argument("headless")
        options.add_argument('--log-level=3') # 브라우저 로그 레벨을 낮춤
        options.add_argument('--disable-loging') # 로그를 남기지 않음
        browser = webdriver.Chrome(options=options)
        browser.get(url)
        soup = BeautifulSoup(browser.page_source, "html.parser")
        coms = soup.select_one('.is_company_card')
        if coms != None :
            divs = coms.find_all('div')
            for company in divs :
                title = company.find('a',class_='tit')
                if title != None :
                    bold = title.find('b')
                    text = title.get_text()
                    if bold!=None :
                        if bold.string == keyword :
                            rate_point = company.find('span',class_='rate_ty02')                        
                            return float(rate_point.string)
                    elif text!=None :
                         print(text)
                         print(keyword)
                         text = text.replace('㈜','').replace('(주)','').strip()
                         keyword = keyword.replace('㈜','').replace('(주)','').strip()
                         if text == keyword :
                            print('일치확인')
                            rate_point = company.find('span',class_='rate_ty02')                        
                            return float(rate_point.string)
            return 0
        else:
            return 0


