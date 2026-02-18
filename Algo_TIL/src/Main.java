import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 4353 문자열 제곱
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 특정 문자열이 주어졌을 때
		// 그 문자열이 어떤 문자열 a를 몇 제곱한지 구하여라 + 최대값으로 구하셈
		//
		

		while (true) {
			String str = br.readLine();
			if (str.equals(".")) {
				System.out.println(sb.toString().trim());
				break;
			}

			sb.append(findPi(str)).append("\n");

		}

	}

	private static Integer findPi(String str) {
		int idx = 0;
		int len = str.length();
		int[] pi = new int[len];
		int max = 1;

		for (int i = 1; i < len; i++) {
			while (idx > 1 && str.charAt(i) != str.charAt(idx)) {
				idx = pi[idx - 1];
			}

			if (str.charAt(i) == str.charAt(idx)) {
				pi[i] = ++idx;
			}

		}

		for (int i = 0; i < len; i++) {
			if (max < pi[i]) {
				max = pi[i];
			}
		}

		return max;
	}
}