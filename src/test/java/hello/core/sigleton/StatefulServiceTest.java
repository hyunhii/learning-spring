package hello.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);

        //ThreadB: B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    @Test
    void statefulServiceSingleton2() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService2 statefulService1 = ac.getBean("statefulService2", StatefulService2.class);
        StatefulService2 statefulService2 = ac.getBean("statefulService2", StatefulService2.class);

        //ThreadA: A사용자 10000원 주문
        int userA = statefulService1.order("userA", 10000);

        //ThreadB: B사용자 20000원 주문
        int userB = statefulService2.order("userB", 20000);


        Assertions.assertThat(userA).isEqualTo(10000);
        Assertions.assertThat(userB).isEqualTo(20000);
    }



    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatefulService2 statefulService2() {
            return new StatefulService2();
        }
    }
}