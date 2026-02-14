import java.util.*;
import java.io.*;

/**
 * 17404 RGB 거리 2
 */
public class 백준_17404_RGB거리2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// RGB 거리에는 집 N개가 있음
		// 1~ N 까지의 집이 순서대로 있음

		// 빨,초,파 중 하나로 색칠
		// 아래의 규칙에 맞게 색칠

		// 1. 1번집은 2번,N번과 같지 않아야함 (앞,뒤 연결)
		// 2. N번 집의 색은 N-1번, 1번 집의 색과 다름
		// 3. i번 집의 색은 i-1, i+1 집의 색과 다름

		int N = Integer.parseInt(br.readLine());// N<=1000

		int INF = 1_000_000;

		int[][] arr = new int[N][3];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 3가지 색을 색칠
		int[][] dp = new int[N][3];

		int ans = INF;

		// 3가지의 색
		for (int k = 0; k < 3; k++) {
			// 3가지 색을 제외한 나머지는 INF로 초기화
			for (int i = 0; i < 3; i++) {
				if (i == k)
					dp[0][i] = arr[0][i];
				else
					dp[0][i] = INF;
			}

			// 각 색의 비용 최소값 계산
			for (int i = 1; i < N; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
			}

			// 정답인 최솟값을 구하는 부분
			for (int i = 0; i < 3; i++) {
				// 맨 뒷집과 맨 앞집이 달라야하는 조건
				if (i != k) {
					ans = Math.min(ans, dp[N - 1][i]);
				}
			}
		}
		System.out.println(ans);
	}
}