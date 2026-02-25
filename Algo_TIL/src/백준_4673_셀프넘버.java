import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4673 백준 셀프 넘버
 */

public class 백준_4673_셀프넘버 {
	static boolean[] arr;

	public static void main(String[] args) throws IOException {
		// 셀프 넘버 : 자기 자신과 모든 자리수를 더한 결과물 이 없는 존재
		// 10000 이하의 모든 셀프 넘버를 하나씩 출력하라

		arr = new boolean[10001];

		for (int i = 1; i < 10000; i++) {
			int selfNumber = d(i);

			if (selfNumber <= 10000) {
				arr[selfNumber] = true;
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < 10001; i++) {
			if (!arr[i]) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	public static int d(int n) {
		int value = n;
		int temp = n;

		while (temp > 0) {
			value += temp % 10;
			temp /= 10;
		}
		return value;
	}
}