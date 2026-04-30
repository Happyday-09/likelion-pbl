package Mission2.package1;

public class Lion {
    // 1. 서로 다른 접근 제어자를 가진 필드 선언
    public String name;         // 외부에서 직접 접근 가능
    String major;               // 같은 패키지 내에서만 접근 가능 (default)
    private final int generation;     // 클래스 내부에서만 접근 가능

    // 2. 생성자: 객체의 초기 상태만 설정 (출력 없음)
    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    // 3. (Step 2용) 객체 스스로 자신의 상태를 검증하는 메서드
    public boolean validateState() {
        boolean isValid = true;

        if (this.name == null || this.name.trim().isEmpty()) {
            System.out.println("❌ 이름이 비어 있습니다.");
            isValid = false;
        }
        if (this.major == null || this.major.trim().isEmpty()) {
            System.out.println("❌ 전공이 비어 있습니다.");
            isValid = false;
        }
        if (this.generation < 1) {
            System.out.println("❌ 기수는 1 이상이어야 합니다.");
            isValid = false;
        }

        return isValid;
    }

    // 4. 정보 출력 메서드
    public void printInfo() {
        System.out.printf("👤 이름: %s | 🎓 전공: %s | 📌 기수: %d\n", name, major, generation);
    }
}