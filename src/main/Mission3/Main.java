package Mission3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======== 🦁 아기사자 정보 입력 ========");
        System.out.print("👤 이름: ");
        String lionName = scanner.nextLine();
        System.out.print("🎓 전공: ");
        String lionMajor = scanner.nextLine();
        System.out.print("📌 기수: ");
        int lionGeneration = Integer.parseInt(scanner.nextLine());
        System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
        String lionPart = scanner.nextLine();
        System.out.print("🆔 학번: ");
        String lionStudentId = scanner.nextLine();
        System.out.println();

        Member babyLion = new BabyLion(lionName, lionMajor, lionGeneration, lionPart, lionStudentId);

        System.out.println("======== 👨‍💼 운영진 정보 입력 ========");
        System.out.print("👤 이름: ");
        String staffName = scanner.nextLine();
        System.out.print("🎓 전공: ");
        String staffMajor = scanner.nextLine();
        System.out.print("📌 기수: ");
        int staffGeneration = Integer.parseInt(scanner.nextLine());
        System.out.print("💻 파트 (백엔드/프론트엔드/기획/디자인): ");
        String staffPart = scanner.nextLine();
        System.out.print("⭐ 직책 (대표/부대표/파트장/멘토): ");
        String staffPosition = scanner.nextLine();
        System.out.println();

        Member staff = new Staff(staffName, staffMajor, staffGeneration, staffPart, staffPosition);

        System.out.println("======== 📋 결과 출력 ========");
        babyLion.printInfo();
        System.out.println("--------------------------------");
        staff.printInfo();
        System.out.println("================================");

        scanner.close();
    }
}