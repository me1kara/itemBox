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



