import java.util.*;
import java.io.*;

/**
 * 30804 과일 탕후루
 */
public class 백준_30804_과잍탕후루 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1~9 과일을 가지는 탕후루를 만들건데
		// 과일의 종류를 2개만 써야함
		// 꼬치 앞, 뒤에서 뺄 수 있을때
		// 내가 만드는 탕후루가 주어지고
		// 그 탕후루의 과일종류를 몇 개 뺄 수 있을지

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		String str = br.readLine();
		for (int i = 0, j = 0; j < N; i += 2, j++) {
			arr[j] = str.charAt(i) - '0';
		}

		// 과일 종류, 개수 저장
		int[] count = new int[10];
		int left = 0;
		int right = 0;
		int fruitTypes = 0; // 현재 범위 내 과일 종류 수
		int maxLen = 0;

		while (right < N) {
			// 오른쪽 과일 추가
			if (count[arr[right]] == 0) {
				fruitTypes++;
			}
			count[arr[right]]++;

			// 과일 종류가 2개를 초과하면 left를 이동시켜 범위를 줄임
			while (fruitTypes > 2) {
				count[arr[left]]--;
				if (count[arr[left]] == 0) {
					fruitTypes--;
				}
				left++;
			}

			maxLen = Math.max(maxLen, right - left + 1);
			right++;
		}

		System.out.println(maxLen);
	}
}