package Mission4.package2;

import Mission4.role.Lion;
import Mission4.role.Role;
import Mission4.role.Staff;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Role> members = new ArrayList<>();
        Map<String, List<Role>> partMap = new HashMap<>();

        while (true) {
            System.out.println("\n====== 🦁 멤버 관리 시스템 ======");
            System.out.println("1. 멤버 등록");
            System.out.println("2. 전체 멤버 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 파트별 조회");
            System.out.println("5. 종료");
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
                for (Role m : members) {
                    if (m.getName().equals(name)) {
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

                Role newMember = null;
                if (roleChoice == 1) {
                    System.out.print("🆔 학번: ");
                    String id = scanner.nextLine();
                    newMember = new Lion(name, major, cohort, part, id);
                } else if (roleChoice == 2) {
                    newMember = new Staff(name, major, cohort, part);
                }

                if (newMember != null) {
                    members.add(newMember);
                    partMap.computeIfAbsent(part, k -> new ArrayList<>()).add(newMember);
                    System.out.println("✅ 등록 완료: " + name);
                }

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
                System.out.println("\n— 💻 파트별 조회 —");
                System.out.println("📁 등록된 파트: " + partMap.keySet());
                System.out.print("조회할 파트: ");
                String searchPart = scanner.nextLine();

                if (partMap.containsKey(searchPart)) {
                    System.out.println("\n✨ [" + searchPart + " 파트 멤버]");
                    List<Role> partMembers = partMap.get(searchPart);
                    for (int i = 0; i < partMembers.size(); i++) {
                        Role m = partMembers.get(i);
                        System.out.printf("%d. %s (%s) - %d기\n", i + 1, m.getName(), m.getRoleName(), m.getCohort());
                    }
                } else {
                    System.out.println("해당 파트에 등록된 멤버가 없습니다.");
                }

            } else if (choice == 5) {
                break;
            }
        }
        scanner.close();
    }
}