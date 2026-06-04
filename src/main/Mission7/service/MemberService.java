package Mission7.service;

import Mission7.domain.role.Lion;
import Mission7.domain.role.Role;
import Mission7.domain.role.Staff;
import Mission7.dto.*;
import Mission7.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public LionResponse createLion(LionCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Lion lion = new Lion(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.save(lion);
        return LionResponse.from(lion);
    }

    public StaffResponse createStaff(StaffCreateRequest request) {
        if (memberRepository.existsByName(request.getName())) {
            return null;
        }
        Staff staff = new Staff(
                request.getName(),
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.save(staff);
        return StaffResponse.from(staff);
    }

    public Object findMember(String name) {
        Optional<Role> optional = memberRepository.findByName(name);
        if (optional.isEmpty()) {
            return null;
        }
        Role member = optional.get();
        if (member instanceof Lion lion) {
            return LionResponse.from(lion);
        }
        if (member instanceof Staff staff) {
            return StaffResponse.from(staff);
        }
        return null;
    }

    public LionResponse updateLion(String name, LionUpdateRequest request) {
        if (memberRepository.findByName(name).isEmpty()) {
            return null;
        }
        Lion updated = new Lion(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getStudentId()
        );
        memberRepository.updateByName(name, updated);
        return LionResponse.from(updated);
    }

    public StaffResponse updateStaff(String name, StaffUpdateRequest request) {
        if (memberRepository.findByName(name).isEmpty()) {
            return null;
        }
        Staff updated = new Staff(
                name,
                request.getMajor(),
                request.getGeneration(),
                request.getPart(),
                request.getPosition()
        );
        memberRepository.updateByName(name, updated);
        return StaffResponse.from(updated);
    }

    public boolean deleteMember(String name) {
        return memberRepository.deleteByName(name);
    }
}