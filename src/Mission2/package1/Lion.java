package Mission2.package1;

public class Lion {
    public String name;
    String major;
    private final int generation;

    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    // 💡 [개선 1] 문자열 빈값 검증 공통 메서드 (중복 로직 제거)
    private boolean isStringEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // 💡 [개선 2] 이름 검증 메서드 분리
    private boolean isNameValid() {
        if (isStringEmpty(this.name)) {
            System.out.println("❌ 이름이 비어 있습니다.");
            return false;
        }
        return true;
    }

    // 💡 [개선 2] 전공 검증 메서드 분리
    private boolean isMajorValid() {
        if (isStringEmpty(this.major)) {
            System.out.println("❌ 전공이 비어 있습니다.");
            return false;
        }
        return true;
    }

    // 💡 [개선 2] 기수 검증 메서드 분리
    private boolean isGenerationValid() {
        if (this.generation < 1) {
            System.out.println("❌ 기수는 1 이상이어야 합니다.");
            return false;
        }
        return true;
    }

    // 전체 상태를 최종적으로 검증하는 메서드
    public boolean validateState() {
        boolean isValid = true;

        // 각 메서드를 실행하여 하나라도 false가 나오면 전체 결과도 false로 처리
        // (주의: 모든 에러 메시지를 출력하기 위해 각각 따로 if문 처리)
        if (!isNameValid()) {
            isValid = false;
        }
        if (!isMajorValid()) {
            isValid = false;
        }
        if (!isGenerationValid()) {
            isValid = false;
        }

        return isValid;
    }

    public void printInfo() {
        System.out.printf("👤 이름: %s | 🎓 전공: %s | 📌 기수: %d\n", name, major, generation);
    }
}