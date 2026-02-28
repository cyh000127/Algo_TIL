import java.util.*;
import java.io.*;

/**
 * 1562 계단 수
 */
public class 백준_1562_계단수 {
	static int N;
	static final int num = 1_000_000_000;
	static long ans;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 특정 수의 +1 -1 하는 수를 계단수라고 하는데
		// N자리 수 0~9가 모두 쓰인 모든 위치가 계단수인 수 개수를 구하여라

		// 정답을 1,000,000,000으로 나눈 나머지를 출력한다.

		N = Integer.parseInt(br.readLine());

		// 1 ~ 8 -> N만큼의 *2가 생김
		// 9 -> N-1 만큼의 *2가 생김
		// 물론 여기서 고려할 것 -> 9나 0이 되는 순간 분기가 계속 생김

		// -> 0~9까지의 비트가 필요함
		// 0000000001 ~ 1111111111

		// 2^10 -1 = 1023

		dp = new long[N + 1][10][1024];
		// N+1개의 크기
		// 10개의 숫자
		// 10개의 숫자가 모두 등장한지 확인하기 위한 1024칸 ( 1111111111 <- 1023)

		// dp를 -1로 초기화
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < 10; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		ans = 0;
		// 맨 앞은 0으로 시작할 수 없음
		for (int i = 1; i < 10; i++) {
			ans = (ans + stairsNum(1, i, 1 << i)) % num;
		}

		System.out.println(ans);

	}

	private static long stairsNum(int idx, int lastNum, int visit) {
		// 종료조건 idx == N
		if (idx == N) {
			return visit == 1023 ? 1 : 0;
		}

		// 이미 값이 구해져있는 곳이라면 계산 x
		if (dp[idx][lastNum][visit] != -1) {
			return dp[idx][lastNum][visit];
		}

		long cnt = 0;

		// lastNum이 0 보다 클때는 -1
		if (lastNum > 0) {
			// visit에 or 연산을 통해 1<< latNum -1을 집어넣기
			cnt = (cnt + stairsNum(idx + 1, lastNum - 1, visit | (1 << (lastNum - 1)))) % num;
		}

		// 9보다 작을때는 +1
		if (lastNum < 9) {
			// visit에 or 연산을 통해 1<< latNum +1을 집어넣기
			cnt = (cnt + stairsNum(idx + 1, lastNum + 1, visit | (1 << (lastNum + 1)))) % num;
		}

		return dp[idx][lastNum][visit] = cnt % num;
	}
}