package Mission10.member.service;

import Mission10.global.exception.DuplicateMemberException;
import Mission10.global.exception.MemberNotFoundException;
import Mission10.member.domain.Member;
import Mission10.member.domain.RoleType;
import Mission10.member.dto.*;
import Mission10.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createLion(LionCreateRequest request) {
        validateDuplicateName(request.getName());
        Member member = new Member(
            request.getName(), request.getMajor(), request.getPart(),
            request.getGeneration(), RoleType.LION, request.getStudentId(), null
        );
        return memberRepository.save(member);
    }

    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        validateDuplicateName(request.getName());
        Member member = new Member(
            request.getName(), request.getMajor(), request.getPart(),
            request.getGeneration(), RoleType.STAFF, null, request.getPosition()
        );
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Member> findByPart(String part) {
        return memberRepository.findByPart(part);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + id));
    }

    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member member = findById(id);
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updateStudentId(request.getStudentId());
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member member = findById(id);
        member.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        member.updatePosition(request.getPosition());
        return memberRepository.save(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }

    private void validateDuplicateName(String name) {
        memberRepository.findByName(name).ifPresent(m -> {
            throw new DuplicateMemberException("이미 존재하는 이름입니다. name: " + name);
        });
    }
}
