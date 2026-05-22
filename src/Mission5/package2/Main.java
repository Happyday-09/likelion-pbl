package Mission5.package2;

import Mission5.role.Lion;
import Mission5.role.Role;
import Mission5.role.Staff;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔧 저장소를 선택하세요:");
        System.out.println("1. MemoryMemberRepository (실제 저장)");
        System.out.println("2. MockMemberRepository (더미 데이터)");
        System.out.print("선택: ");
        int repoType = Integer.parseInt(scanner.nextLine());

        // AppConfig를 통한 의존성 주입 (Main에서 new MemberService를 직접 하지 않음)
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(repoType);

        while (true) {
            System.out.println("\n🦁 ===== 멋사 멤버 관리 시스템 (Step 2: DI 적용) ===== 🦁");
            System.out.println("1. ➕ 멤버 등록");
            System.out.println("2. 📋 전체 멤버 조회");
            System.out.println("3. 🔍 이름으로 검색");
            System.out.println("4. 🚪 종료");
            System.out.print("선택: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.print("👤 역할 선택 (1: 아기사자, 2: 운영진):\n");
                int roleChoice = Integer.parseInt(scanner.nextLine());

                System.out.println("📝 정보 입력");
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("전공: ");
                String major = scanner.nextLine();
                System.out.print("기수: ");
                int cohort = Integer.parseInt(scanner.nextLine());
                System.out.print("파트: ");
                String part = scanner.nextLine();
                System.out.print("학번: ");
                String studentId = scanner.nextLine();

                Role role;
                if (roleChoice == 1) {
                    role = new Lion(name, major, cohort, part, studentId);
                } else {
                    role = new Staff(name, major, cohort, part, studentId);
                }
                memberService.registerMember(role);

            } else if (choice == 2) {
                memberService.printAllMembers();
            } else if (choice == 3) {
                System.out.print("🔍 검색할 이름: ");
                String name = scanner.nextLine();
                memberService.searchMember(name);
            } else if (choice == 4) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
        scanner.close();
    }
}