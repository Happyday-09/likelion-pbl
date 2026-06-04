package Mission4.package1;

import Mission4.role.Lion;
import Mission4.role.Role;
import Mission4.role.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Role> members = new ArrayList<>();

        while (true) {
            System.out.println("\n====== 🦁 멤버 관리 시스템 ======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("\n— 📝 멤버 등록 —");
                System.out.print("역할 선택 (1: 아기사자, 2: 운영진): ");
                int roleChoice = scanner.nextInt();
                scanner.nextLine();

                System.out.print("👤 이름: ");
                String name = scanner.nextLine();

                boolean isDuplicate = false;
                for (Role member : members) {
                    if (member.getName().equals(name)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate) {
                    System.out.println("❌ 등록 실패: 이미 존재하는 이름입니다.");
                    continue;
                }

                System.out.print("🎓 전공: ");
                String major = scanner.nextLine();
                System.out.print("📌 기수: ");
                int cohort = scanner.nextInt();
                scanner.nextLine();
                System.out.print("💻 파트: ");
                String part = scanner.nextLine();

                if (roleChoice == 1) {
                    System.out.print("🆔 학번: ");
                    String studentId = scanner.nextLine();
                    members.add(new Lion(name, major, cohort, part, studentId));
                } else if (roleChoice == 2) {
                    members.add(new Staff(name, major, cohort, part));
                }
                System.out.println("✅ 등록 완료: " + name);

            } else if (choice == 2) {
                System.out.println("\n— 📋 전체 멤버 목록 —");
                for (int i = 0; i < members.size(); i++) {
                    Role m = members.get(i);
                    System.out.printf("%d. [%s] %s - %d기\n", i + 1, m.getRoleName(), m.getName(), m.getCohort());
                }
                System.out.println("📊 총 " + members.size() + "명");

            } else if (choice == 3) {
                System.out.print("검색할 이름: ");
                String searchName = scanner.nextLine();
                boolean found = false;
                for (Role m : members) {
                    if (m.getName().equals(searchName)) {
                        m.printDetails();
                        String canSubmit = m.getPolicy().checkSubmission() ? "✅ 가능" : "❌ 불가능";
                        System.out.println("📝 과제 제출 가능 여부: " + canSubmit);
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("검색 결과가 없습니다.");

            } else if (choice == 4) {
                break;
            }
        }
        scanner.close();
    }
}