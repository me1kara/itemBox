package store.han.itemBox.test;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import store.han.itemBox.repository.QuotesRepository;
import store.han.itemBox.service.QuotesService;

import java.util.Optional;

//@WebMvcTest(HomeController.class) 컨트롤러 테스트 할때 사용
//@AutoConfigureWebMvc MockMvc 주입용 컨트롤러 테스트 할때 사용2 but 없어도 됨

//@SpringBootTest(classes = {QuotesService.class})
// or
//@ExtendWith(SpringExtension.class)
//@Import({QuotesService.class}) 컨테이너에서 받아올 bean을 기술,


public class TestLifeCycle {
    /*
    @Autowired
    private MockMvc mockMvc; , Mvc 테스트 용도로 사용한 가짜 Controller, 위에 입력한 controller 경로들이 주입됨

    @MockBean
    private QuotesService, 위에 클래스에서 받아온 bean이 주입받은 bean을 가짜 빈으로 만들어서 주입해줘야 됨.진짜 bean은 성립이 안 됨

    */

    @Autowired
    QuotesService quotesService;

    @Autowired
    QuotesRepository quotesRepository;

    @BeforeAll
    static void beforeAll(){
        System.out.println("## Before All");

    }
    @AfterAll
    static void afterAll(){
        System.out.println("## after All");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("## Before Each");

    }
    @AfterEach
    void afterEach(){
        System.out.println("## after Each");
    }

    @Test
    void mvcTest() throws Exception {
        System.out.println("## 컨트롤러 필요 테스트코드");
        /*
            given : 가짜객체에 대한 서비스로직과 리턴값을 '상정'해놓아야함
            BDDMockito.given(QuotesService.random()).willReturn(new Quotes("1"));
            컨테이너에서 받은 controller bean의 di인 quotesService를 가짜 빈으로 만들었음
            해당 가짜 bean의 특정 메서드에 대해 설정함

            get,post : rest-api를 체크
            jsonPath : 결과 증명
            print : 결과값 출력

            MockMvcRequestBuilders.get("");
            MockMvcRequestBuilders.post("");
            MockMvcResultMatchers.status();
            MockMvcResultMatchers.jsonPath("");
            MockMvcResultHandlers.print();

            요청시 json을 넘길 경우 json 생성법

            구글 json api
            Gson gson = new Gson();
            String json = gson.toJson(DTO객체);

            or

            String json = new ObjectMapper.writeValueAsString(DTO)
            사용예제
            mockMvc.perform(get("/quotes/random")
                if 넘기는 값이 있을시
                .content("json")
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.text").exists())
            .andDo(print())

            보통 요청을 하면 컨트롤러->서비스->db 순으로 가는데 이를 각각 확인해보는 것
            컨트롤러를 확인하면 서비스도 확인되는 결과가 나오는거 아닌가? -> given은 내가 해당상황을 상정하는 것

            verify : 해당 서비스로직이 정상적으로 실행됐는지 체크
            Mockito.verify(QuotesService).random();

            컨트롤러의 반환결과가 given에서 상정해놓은 일치해야겠지?

        */

    }

    @Test
    @DisplayName("test name 2")
    void test2(){
        System.out.println("## test2");
        //Mockito.when(quotesService2.random()).thenReturn(new Quotes(1l,"하이"))
        //Assertions.assertEquals("하이", new Quotes("1l","2").getText());

    }

    @Test
    @Disabled
    void test3(){
        System.out.println("## test3");
        System.out.println("확ㄴ잉용ㅇㅇ");
        
    }
}
