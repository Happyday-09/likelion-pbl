import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    int lionCount;

    while (true) {
        System.out.print("저장할 아기사자 수를 5 이상 입력하세요. \n ");

        if (scanner.hasNextInt()) {
            lionCount = scanner.nextInt();

            if (lionCount >= 5) {
                break;
            } else {
                System.out.println("올바르지 않은 입력입니다. 5 이상의 숫자를 다시 입력해주세요.\n");
            }
        }
    }

    String[] lionNames = new String[lionCount];
    System.out.println();

    // 2. 이름 입력 (방식 1: 엔터로 구분해서 한 번에 복붙)
    System.out.println("아기사자 이름을 입력해주세요. ");

    for (int i = 0; i < lionCount; i++) {
        lionNames[i] = scanner.next();
    }


    System.out.println("총 아기사자 명단을 최종적으로 출력합니다.");

    for (int i = 0; i < lionCount; i++) {
        System.out.println((i + 1) + ". " + lionNames[i]);
    }

    scanner.close();
}