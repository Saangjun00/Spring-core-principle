package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // 메서드 이름 자유롭게 줄 수 있고 스프링 빈이 스프링 코드에 의존하지 않음
        // 코드가 아니라 설정 정보를 사용하기 때문에 코트를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있음
//        @Bean(initMethod = "init", destroyMethod = "close")     // @Bean의 destoryMethod는 추론 기능이 적용되어 있음
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
