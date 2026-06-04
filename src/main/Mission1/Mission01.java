package Mission1;

import java.util.Scanner;

class LionManager {

    public int getLionCount(Scanner scanner) {
        int count;
        while (true) {
            System.out.print("저장할 아기사자 수를 5 이상 입력하세요.\n ");

            if (scanner.hasNextInt()) {
                count = scanner.nextInt();

                if (count >= 5) {
                    break;
                } else {
                    System.out.println("올바르지 않은 입력입니다. 5 이상의 숫자를 다시 입력해주세요.\n");
                }
            } else {
                // 숫자가 아닌 문자가 입력되었을 때 무한 루프를 방지합니다.
                System.out.println("잘못된 입력입니다. 숫자로 입력해주세요.\n");
                scanner.nextLine();
            }
        }
        return count;
    }

    public String[] getLionNames(Scanner scanner, int count) {
        String[] names = new String[count];
        System.out.println("\n아기사자 이름을 입력해주세요. ");

        for (int i = 0; i < count; i++) {
            names[i] = scanner.next();
        }
        return names;
    }

    public void printLionNames(String[] names) {
        System.out.println("\n총 아기사자 명단을 최종적으로 출력합니다.");

        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + ". " + names[i]);
        }
    }
}

public class Mission01 {

    // 피드백 반영 1: 전통적이고 표준적인 main 메서드 형태
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 피드백 반영 2: 기능이 분리된 관리자(Mission1.LionManager) 객체 생성
        LionManager manager = new LionManager();

        // 1. 인원 수 입력받기
        int lionCount = manager.getLionCount(scanner);

        // 2. 이름 입력받기
        String[] lionNames = manager.getLionNames(scanner, lionCount);

        // 3. 결과 출력하기
        manager.printLionNames(lionNames);

        scanner.close();
    }
}