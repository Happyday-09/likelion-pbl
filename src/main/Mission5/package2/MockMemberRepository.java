package Mission5.package2;

import Mission5.role.Lion;
import Mission5.role.Role;
import Mission5.role.Staff;
import java.util.Arrays;
import java.util.List;

public class MockMemberRepository implements MemberRepository {
    private final Role mockLion = new Lion("김더미", "컴퓨터공학과", 14, "백엔드", "202020202");
    private final Role mockStaff = new Staff("이운영", "소프트웨어학과", 13, "기획", "201919191");

    @Override
    public void save(Role role) {
        System.out.println("[Mock] 더미 데이터라 실제 저장은 되지 않습니다. (입력된 이름: " + role.getName() + ")");
    }

    @Override
    public Role findByName(String name) {
        System.out.println("[Mock] 항상 더미 데이터를 반환합니다.");
        return mockLion;
    }

    @Override
    public List<Role> findAll() {
        System.out.println("[Mock] 항상 더미 리스트를 반환합니다.");
        return Arrays.asList(mockLion, mockStaff);
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}