package Mission2.package2;

// 다른 패키지에 있는 Lion 클래스를 사용하기 위해 import
import Mission2.package1.Lion;

public class Step3 {
    void main(String[] args) {
        System.out.println("🦁 아기사자 객체를 생성합니다.");
        Lion lion = new Lion("김멋대", "컴퓨터공학과", 14);

        System.out.println("🦁 아기사자 정보를 출력합니다.");
        lion.printInfo();

        // ---------------------------------------------------------
        // 1. public 필드 접근 시도 (성공)
        // ---------------------------------------------------------
        System.out.println("\n📌 Step 3-1. public 필드 접근을 시도합니다.");
        System.out.println("👉 name 필드 값을 변경합니다.");

        lion.name = "홍길동"; // 접근 제어자가 public이므로 정상적으로 변경됨
        System.out.println("✅ public 필드 접근 성공");

        System.out.println("🦁 아기사자 정보를 출력합니다.");
        lion.printInfo();
    }
}