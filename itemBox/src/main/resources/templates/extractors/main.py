from namuwiki import get_local_foods
import csv

#나무위키에서 지역 전통 음식 가져오기
local_foods = get_local_foods()


# CSV 파일로 데이터 저장
with open('food_data.csv', 'w', newline='', encoding="utf-8-sig") as csvfile:
    writer = csv.writer(csvfile)

    # CSV 파일의 첫 번째 행에 열 이름 추가
    writer.writerow(['Local', 'Foods'])

    # 데이터를 CSV 파일에 쓰기
    for region, foods in local_foods.items():
        writer.writerow([region, ', '.join(foods)])

