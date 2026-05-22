package Mission5.package2;

// 객체의 생성과 조립을 전담하는 설정 클래스 (IoC 컨테이너 역할)
public class AppConfig {

    public MemberService memberService(int repoType) {
        return new MemberService(memberRepository(repoType));
    }

    private MemberRepository memberRepository(int repoType) {
        if (repoType == 1) {
            return new MemoryMemberRepository();
        } else {
            return new MockMemberRepository();
        }
    }
}