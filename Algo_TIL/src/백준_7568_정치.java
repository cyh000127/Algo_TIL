import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 7568 백준 덩치
 */

public class 백준_7568_정치 {
	public static void main(String[] args) throws IOException {
		// 키 , 몸무게를 비교해서 두가지 값 모두 큰 사람은 우위를 점함
		// 등수를 정하라 (둘 중 하나만 크다면 등수를 비교할 수 없음)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine()); // 사람 수

		int[][] body = new int[K][3]; // 등수를 [K][2]에 저장

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			body[i][0] = Integer.parseInt(st.nextToken());
			body[i][1] = Integer.parseInt(st.nextToken());
			body[i][2] = 1;
		}

		for (int i = 0; i < K - 1; i++) {
			for (int j = i + 1; j < K; j++) {
				if (body[i][0] > body[j][0] && body[i][1] > body[j][1]) {
					body[j][2]++;
				} else if (body[i][0] < body[j][0] && body[i][1] < body[j][1]) {
					body[i][2]++;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int[] index : body) {
			int rank = index[2];
			sb.append(rank).append(" ");
		}

		System.out.println(sb.toString());
	}
}