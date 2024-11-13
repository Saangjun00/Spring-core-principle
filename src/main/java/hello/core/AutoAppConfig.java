package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 기존 예제 코드를 살리기 위해서 기존 AppConfig를 컴포넌트 스캔 대상에서 제외시킴
@ComponentScan(
        basePackages = "hello.core",    // 탐색할 패키지의 시작 위치를 지정하고 하위 패키지까지 모두 탐색
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*
    // 수동 빈 등록이 우선권을 가짐
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
*/

}
