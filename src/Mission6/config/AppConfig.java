package Mission6.config;

import Mission6.repository.MemberRepository;
import Mission6.repository.MemoryMemberRepository;
import Mission6.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 지침 3-3에 따라 자동 주입(@Service, @Repository) 방식의 정상 동작을 검증하기 위해
 * 기존 설정 클래스의 @Configuration 및 @Bean 코드를 완전히 주석 처리하여 비활성화합니다.
 */

// @Configuration
public class AppConfig {

    // @Bean
    // public MemberRepository memberRepository() {
    //     return new MemoryMemberRepository();
    // }

    // @Bean
    // public MemberService memberService() {
    //     return new MemberService(memberRepository());
    // }
}