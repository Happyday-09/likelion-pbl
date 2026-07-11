package Mission10.assignment.service;

import Mission10.assignment.domain.Assignment;
import Mission10.assignment.dto.AssignmentCreateRequest;
import Mission10.assignment.dto.AssignmentUpdateRequest;
import Mission10.assignment.repository.AssignmentRepository;
import Mission10.global.exception.AssignmentNotFoundException;
import Mission10.global.exception.MemberNotFoundException;
import Mission10.member.domain.Member;
import Mission10.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Assignment create(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: " + memberId));
        Assignment assignment = new Assignment(request.getTitle(), request.getDescription(), member);
        return assignmentRepository.save(assignment);
    }

    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
            .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));
    }

    @Transactional
    public Assignment update(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = findById(id);
        assignment.updateInfo(request.getTitle(), request.getDescription());
        return assignmentRepository.save(assignment);
    }

    @Transactional
    public void delete(Long id) {
        Assignment assignment = findById(id);
        assignmentRepository.delete(assignment);
    }
}
