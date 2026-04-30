package Mission2.package1;

import java.util.Scanner;

public class Step1 {

    // 💡 [개선] main 밖으로 문자열 빈값 검증 로직을 분리
    private boolean isStringEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🦁 아기사자 이름을 입력해주세요.");
        String name = scanner.nextLine();

        System.out.println("🎓 전공을 입력해주세요.");
        String major = scanner.nextLine();

        System.out.println("📌 기수를 입력해주세요.");
        int generation = scanner.nextInt();

        System.out.println("📌 입력값 검증을 진행합니다.");

        boolean isValid = true;

        // 💡 분리해둔 isStringEmpty() 메서드를 재사용하여 가독성 상승!
        if (isStringEmpty(name)) {
            System.out.println("❌ 이름은 비어 있을 수 없습니다.");
            isValid = false;
        }
        if (isStringEmpty(major)) {
            System.out.println("❌ 전공은 비어 있을 수 없습니다.");
            isValid = false;
        }
        if (generation < 1) {
            System.out.println("❌ 기수는 1 이상이어야 합니다.");
            isValid = false;
        }

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