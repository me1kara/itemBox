package store.han.itemBox.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") //라이브 서버에서 확인용으로
public class DataController {
    @Value("${TimeMoney.serviceKey}")
    private String timePerMoneyKey;
    @GetMapping("/data/timeMoney")
    public ResponseEntity<String> getSiGub(){
        try {
            StringBuilder requestUrl = new StringBuilder("http://api.odcloud.kr/api/15068774/v1/uddi:21d816e5-6c44-4e30-903d-e98e30a4f227?");
            requestUrl.append("serviceKey=").append(timePerMoneyKey);
            URL url = new URL(requestUrl.toString());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보

            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                sb.append(line);
            }

            JSONObject obj = new JSONObject(sb.toString()); // json으로 변경 (역직렬화)
            return ResponseEntity.ok(obj.getJSONArray("data").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("status:실패했습니다");

    }


}
