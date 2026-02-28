import java.util.*;
import java.io.*;

/**
 * 2098 외판원 순회
 */
public class 백준_2098_외판원순회 {
	static int maxCost = 16_000_001; // 16 * 1_000_000
	static int[][] arr, dp;
	static int N, maxVisit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1~N까지의 도시를 전부 돌건데
		// 가장 비용이 적게 드는 방법을 말해라
		// 참고로 한번 갔던 도시는 다시 가지 못함

		// x번 도시에서 y번 도시까지 가는 비용을 각각 나타내 줄때
		// 가장 이득인 방법을 찾아라
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				arr[i][j] = cost;
			}
		}

		// N개의 도시에 대한 방문 여부 = 2의 N승 -1
		// 현재 도시, 방문 상태
		dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		// 어디서 시작하든 순회하기 떄문에 결과는 동일하게 나올거임
		// 즉, 0에서 시작해도 정답은 나옴
		System.out.println(visit(0, 1));
	}

	private static int visit(int now, int visit) {
		if (visit == (1 << N) - 1) {
			// 시작점으로 돌아가는 길이 있다면 그 비용 반환 -> 없으면 maxCost;
			return arr[now][0] == 0 ? maxCost : arr[now][0];
		}

		// 계산된 값이 있다면 반환
		if (dp[now][visit] != -1) {
			return dp[now][visit];
		}

		dp[now][visit] = maxCost;

		for (int i = 0; i < N; i++) {
			// 아직 방문하지 않았고(& = and) 가는 길이 있다면
			if ((visit & (1 << i)) == 0 && arr[now][i] != 0) {
				// 현재까지의 비용 + 다음 도시부터의 최소 비용 중 가장 작은 값 선택
				dp[now][visit] = Math.min(dp[now][visit], visit(i, visit | (1 << i)) + arr[now][i]);
			}
		}

		return dp[now][visit];
	}
}