import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 4353 문자열 제곱
 */
public class 백준_4353_문자열제곱 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String str = br.readLine();
			if (str.equals(".")) {
				break;
			}
			sb.append(findPi(str)).append("\n");
		}
		System.out.println(sb.toString().trim());
	}

	// 실패 탐지
	private static int findPi(String str) {
		int idx = 0;
		int len = str.length();
		int[] pi = new int[len];

		for (int i = 1; i < len; i++) {
			while (idx > 0 && str.charAt(i) != str.charAt(idx)) {
				idx = pi[idx - 1];
			}

			if (str.charAt(i) == str.charAt(idx)) {
				pi[i] = ++idx;
			}
		}

		int lastPi = pi[len - 1]; // 가장 긴 접두사=접미사 길이
		int unitLen = len - lastPi; // 반복 마디의 최소 길이 후보

		// 나머지가 0이라면 제곱의 가능성을 가진 애들
		if (len % unitLen == 0) {
			return len / unitLen;
		} else {
			return 1;
		}

	}
}