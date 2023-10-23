package store.han.itemBox.test;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import store.han.itemBox.controller.HomeController;
import store.han.itemBox.controller.QuotesController;
import store.han.itemBox.entity.Quotes;
import store.han.itemBox.service.QuotesService;

//@WebMvcTest(HomeController.class) 컨트롤러 테스트 할때 사용
//@AutoConfigureWebMvc MockMvc 주입용 컨트롤러 테스트 할때 사용2 but 없어도 됨

//@SpringBootTest(classes = {QuotesService.class})
// or
//ExtendsWith(SpringExtends.class)
//import({TestServiceImpl.class, TestService2.class}) 빈을 받고자 하는 클래스


public class TestLifeCycle {
    /*
    @Autowired
    private MockMvc mockMvc; , Mvc 테스트 용도로 사용한 진짜 bean

    @MockBean
    private QuotesService, 테스트할 클래스가 주입받은 가짜 bean
    */
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
            given : 가짜객체에 대한 서비스로직과 리턴값을 '상정'하는것
            BDDMockito.given(QuotesService.random()).willReturn(new Quotes("1"));

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
        //


    }

    @Test
    @Disabled
    void test3(){
        System.out.println("## test3");
    }
}
