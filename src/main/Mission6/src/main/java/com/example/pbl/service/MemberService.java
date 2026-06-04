package com.example.pbl.service;

import com.example.pbl.repository.MemberRepository;
import com.example.pbl.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;

// ✅ Mission5 package2/MemberService.java → @Service로 자동 등록
// Mission5에서 AppConfig가 new MemberService(repository)로 직접 조립하던 것을
// 이제 스프링 컨테이너가 대신 처리
@Service
public class MemberService {

    private final MemberRepository repository;

    // 생성자가 1개 → @Autowired 생략 가능
    // Mission5에서 생성자 주입(DI)으로 설계했던 구조를 그대로 유지
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void registerMember(Role role) {
        if (repository.existsByName(role.getName())) {
            System.out.println("❌ 이미 등록된 이름입니다.");
            return;
        }
        repository.save(role);
        System.out.println("✅ 등록 완료: " + role.getName());
    }

    public void printAllMembers() {
        List<Role> members = repository.findAll();
        if (members.isEmpty()) {
            System.out.println("등록된 멤버가 없습니다.");
            return;
        }
        System.out.println("👥 ===== 전체 멤버 목록 =====");
        for (Role member : members) {
            member.printInfo();
            System.out.println("-------------------------");
        }
    }

    public void searchMember(String name) {
        Role member = repository.findByName(name);
        if (member == null) {
            System.out.println("❌ 해당 이름의 멤버를 찾을 수 없습니다.");
            return;
        }
        System.out.println("🎯 ===== 검색 결과 =====");
        member.printInfo();
    }
}
