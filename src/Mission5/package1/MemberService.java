package Mission5.package1;

import Mission5.role.Role;
import java.util.List;

public class MemberService {
    // Service 내부에서 Repository를 직접 생성 (강한 결합)
    private final MemberRepository repository = new MemberRepository();

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