import java.util.Scanner;

public class second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Count = 0;

        while (true) {
            System.out.print("저장할 아기 사자 수를 5 이상 입력해주세요. ");

            if (scanner.hasNextInt()) {
                Count = scanner.nextInt();

                if (Count >= 5) {
                    break;
                } else {
                    System.out.println("[오류] 5 이상 입력해주세요. \n");
                }
            }
        }

        String[] lionNames = new String[Count];
        System.out.println(); // 줄바꿈

        // --- 여기서부터 수정된 부분입니다 ---
        System.out.println("아기 사자 이름을 입력해주세요:");

        for (int i = 0; i < Count; i++) {
            // "n번째 입력하세요" 문구를 지우고, scanner.next()를 사용하여
            // 한 번에 입력된 이름들을 공백이나 줄바꿈을 기준으로 차례대로 배열에 쏙쏙 넣습니다.
            lionNames[i] = scanner.next();
        }
        // -----------------------------------

        System.out.println("총 아기 사자 수: " + Count + "명");
        System.out.println("--- 아기 사자 명단을 최종적으로 출력합니다. ---");

        // 결과 출력
        for (int i = 0; i < Count; i++) {
            System.out.println((i + 1) + ". " + lionNames[i]);
        }

        scanner.close();
    }
}