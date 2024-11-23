package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 초기화, 소멸 인터페이스 단점: 스프링 전용 인터페이스, 초기화, 소멸 메서드 이름 변경 X
public class NetworkClient { // implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // @PostConstruct, @PreDestroy 특징
    // 애노테이션 하나만 붙이면 되기 때문에 편리함
    // 스프링이 아닌 다른 컨테이너에서도 동작함
    // 컴포넌트 스캔과도 잘 어울림
    // 유일한 단점: 외부 라이브러리에는 적용하지 못함
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
