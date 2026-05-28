package Mission6.repository;

import Mission6.domain.Member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}