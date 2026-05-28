package Mission6.service;

import Mission6.domain.Member;
import Mission6.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 스프링 빈 주입 시 생성자가 단 1개만 존재하므로 @Autowired 어노테이션을 생략해도 자동으로 주입됩니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}