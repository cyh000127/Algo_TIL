import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1305 광고
 */
public class 백준_1305_광고 {

	public static void main(String[] args) throws IOException {

		// 특정 광고 문구가 반복될 때
		// 그 문구가 몇번 반복되고 잇는건지 찾아라

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자 길이 L
		int L = Integer.parseInt(br.readLine());

		String str = br.readLine();

		System.out.println(findPi(str, L));
	}

	private static int findPi(String str, int l) {
		int max = 0;
		int[] arr = new int[l];
		int idx = 0;

		for (int i = 1; i < l; i++) {
			while (idx > 0 && str.charAt(i) != str.charAt(idx)) {
				idx = arr[idx - 1];
			}

			if (str.charAt(i) == str.charAt(idx)) {
				arr[i] = ++idx;
			}
		}
		int len = l - arr[l - 1];

		// 전체 눈에 보이는 길이 l에서 실패함수의 마지막 값을 빼주면 가능
		if (arr[l - 1] == 0) {
			return l;
		} else {
			return len;
		}

	}
}