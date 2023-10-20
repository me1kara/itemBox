package store.han.itemBox.test;


import org.junit.jupiter.api.*;

public class TestLifeCycle {
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
    void test1(){
        System.out.println("## test1");
    }

    @Test
    @DisplayName("test name 2")
    void test2(){
        System.out.println("## test2");
    }

    @Test
    @Disabled
    void test3(){
        System.out.println("## test3");
    }
}
