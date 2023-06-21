package lotto;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LottoGameApplication {

	public static void main(String[] args) {

		while (true) {

			// 1. 로또 번호 입력받기
			System.out.println("1~45까지의 숫자 6개를 입력해주세요");
			Scanner sc = new Scanner(System.in); // 스캐너 사용
			int[] myNum = new int[6];

			for (int i = 0; i < myNum.length; i++) {
				myNum[i] = sc.nextInt();
				if (myNum[i] > 45 || myNum[i] < 1) {
					System.out.println("1~45 범위를 초과했습니다. 다시 입력해주세요.");
					i--;
				}
				for (int j = 0; j < i; j++) {
					if (myNum[i] == myNum[j]) {
						System.out.println("중복된 숫자를 입력했습니다. 다시 입력해주세요.");
						i--;
					}
				}
			}
			System.out.println("입력한 숫자는 " + Arrays.toString(myNum) + " 입니다.");

			// 2. 로또 당첨 번호 생성
			Random rnd = new Random(); // 랜덤 사용
			int[] prizeNum = new int[6]; // 크기 6의 배열 생성

			for (int i = 0; i < 6; i++) {
				prizeNum[i] = rnd.nextInt(45) + 1; // 1~45까지의 숫자
				for (int j = 0; j < i; j++) {
					if (prizeNum[i] == prizeNum[j]) { // 중복 숫자 제거
						i--;
						break;
					}
				}
			}
			System.out.println("로또 당첨 번호는 " + Arrays.toString(prizeNum) + " 입니다.");

			// 3. 당첨 확인
			int count = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (myNum[i] == prizeNum[j]) {
						count++;
					}
				}
			}

			switch (count) {
			case 6: {
				System.out.println("1등!! 인생 역전 축하합니다.");
			}
			case 5: {
				System.out.println("2등!! 아깝네요");
			}
			case 4: {
				System.out.println("3등! 바나나우유 하나만요");
			}
			case 3: {
				System.out.println("4등. 소박한 행복");
			}
			default: {
				System.out.println("** 꽝 **");
			}
			}

			// 4. 프로그램 종료 여부
			System.out.println("다시 도전하시겠습니까? y/n");
			String yn = sc.next();
			if (yn.equals("y") || yn.equals("Y")) {
				continue;
			} else if (yn.equals("n") || yn.equals("N")) {
				System.out.println("로또 프로그램을 종료합니다");
				System.exit(0);
				sc.close();
			} else {
				System.out.println("잘못된 입력입니다");
				yn = sc.next();
			}
		}
	}
}
