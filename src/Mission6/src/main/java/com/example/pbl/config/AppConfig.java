package com.example.pbl.config;

import com.example.pbl.repository.MemberRepository;
import com.example.pbl.repository.MemoryMemberRepository;
import com.example.pbl.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// ✅ 수동 주입 방식 (Step 1)
// Mission5의 AppConfig와 동일한 역할 - 객체 생성과 조립을 전담
// 자동 주입(@Service, @Repository) 확인 후 아래 @Configuration 주석 처리
//@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}
