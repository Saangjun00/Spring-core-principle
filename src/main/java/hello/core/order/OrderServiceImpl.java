package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    // final 변수가 붙은 것을 자동으로 생성자를 만들어줌
public class OrderServiceImpl implements OrderService {

    // 3. 필드 주입(외부에서 변경이 불가능해서 테스트하기 힘들기 때문에 비추천)
//    @Autowired
    private final MemberRepository memberRepository;

//    @Autowired
    private final DiscountPolicy discountPolicy;

    // @Autowired 매칭
    // 1. 타입 매칭
    // 2. 타입 매칭 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭
//    @Autowired
//    private DiscountPolicy rateDiscountPolicy;

    // 1. 생성자 주입
    // 생성자 주입을 사용하면 final 키워드를 사용할 수 있음
    // -> 필드를 누락한 경우 컴파일 오류를 발생시킴

    // @Qualifier 매칭
    // 1. @Qulifier끼리 매칭
    // 2. 빈 이름 매칭
    // 3. NoSuchBeanDefinitionException 예외 발생
    @Autowired      // 생성자가 한 개일 경우 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

/*
    // 2. 수정자 주입(setter 주입)
    @Autowired(required = false)    // 주입할 대상이 동작하기 위해
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
*/

/*
    // 4. 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

