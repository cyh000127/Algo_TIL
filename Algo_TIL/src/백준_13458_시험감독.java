import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 13458 시험감독
 */
public class 백준_13458_시험감독 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 N개의 시험장.
		// i번의 시험장에 있는 응시자 수는 Ai 명

		// 총 감독 + 부 감독 두 종류가 존재함
		// 총 감독 = B명 감시 가능
		// 부 감독 = C명 감시 가능

		// 총감은 1명 부감은 얼마든지 있어도 됨

		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] Ai = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			Ai[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		long ans = 0;
		// 한반에 최소 필요한 감독관 수
		for (int i = 0; i < N; i++) {
			// 각 반에서 총 감독관이 감당할 수 있는 수
			int tmp = Ai[i] - B;

			if (tmp <= 0) {
				// 총 감독관으로만 해결할 수 있다면
				ans += 1;
			} else if (tmp % C != 0) {
				// tmp를 C로 나눈 나머지가 존재한다면 + 1
				ans += 2 + tmp / C;
			} else {
				// 정확히 나눠진다면 총감독관 한명만 더함
				ans += 1 + tmp / C;
			}

		}
		System.out.println(ans);
	}
}