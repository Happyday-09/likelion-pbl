package Mission2.package1;

import java.util.Scanner;

public class Step1 {
    void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 사용자 입력 처리
        System.out.println("🦁 아기사자 이름을 입력해주세요.");
        String name = scanner.nextLine();

        System.out.println("🎓 전공을 입력해주세요.");
        String major = scanner.nextLine();

        System.out.println("📌 기수를 입력해주세요.");
        int generation = scanner.nextInt();

        System.out.println("📌 입력값 검증을 진행합니다.");

        // 2. main 메서드에서 직접 유효성 검증
        boolean isValid = true;
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ 이름은 비어 있을 수 없습니다.");
            isValid = false;
        }
        if (major == null || major.trim().isEmpty()) {
            System.out.println("❌ 전공은 비어 있을 수 없습니다.");
            isValid = false;
        }
        if (generation < 1) {
            System.out.println("❌ 기수는 1 이상이어야 합니다.");
            isValid = false;
        }

        // 3. 검증 결과에 따른 흐름 제어 및 객체 생성
        if (isValid) {
            System.out.println("⏩ 입력값 검증을 통과하여 아기사자 객체 생성을 진행합니다.");
            Lion lion = new Lion(name, major, generation);
            System.out.println("✅ 아기사자 객체를 성공적으로 생성하였습니다.");

            System.out.println("🦁 아기사자 정보를 출력합니다.");
            lion.printInfo();
        }

        scanner.close();
    }
}