import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 14499 주사위 굴리기
 */
public class 백준_14499_주사위굴리기 {
	static int[][] map;
	static int[] diceValue;
	static int nr, nc;
	static int N, M, x, y, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N * M의 지도가 존재
		// 지도에는 동서남북이 존재함
		// 지도의 좌표는 (r, c) (행, 열)
		// 지도에는 정수가 쓰여있음
		// 지도에 있는 정수가 0이라면 주사위의 바닥면의 수가 복사
		// 0이 아니라면 주사위의 바닥면에 그 수가 복사, 바닥은 0

		// 주사위의 좌표와 이동시키는 명령이 주어졌을 때 주사위가 이동할때마다 상단에 쓰여진 값을 구하여라

		// 바깥으로 나갈거같으면 명령 무시

		// N, M, 주사위의 위치 x, y 명령의 개수 K

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		diceValue = new int[7];

		Arrays.fill(diceValue, 0);
		// 모든 칸을 0으로 초기화
		// 주사위는 (1, 6), (2, 5), (3, 4)끼리 짝임
		// 기존 방향과 수평으로 진행 한다면 바뀌지 않지만
		// 기존 방향과 수직으로 진행하는 순간 지금 바닥에 있는 면 + 다른 면을 배열에 넣어야 함

		int dir = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		StringBuilder sb = new StringBuilder();

		nr = x;
		nc = y;

		// 이동
		st = new StringTokenizer(br.readLine());
		while (K-- > 0) {
			int move = Integer.parseInt(st.nextToken()) - 1; // index 때문에 꼭 1을 빼줘야 함
			// 지도 밖으로 나간다면 continue;
			if (nr + dr[move] >= N || nr + dr[move] < 0 || nc + dc[move] >= M || nc + dc[move] < 0) {
				continue;
			}

			sb.append(roll(move)).append("\n");

		}
		System.out.println(sb.toString().trim());
	}

	// 동(0), 서(1), 북(2), 남(3)
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	private static int roll(int move) {
		int tmp = diceValue[3];

		// 주사위를 돌리는 것이 아닌 주사위가 담고 있는 배열의 값을 돌리는 식으로 풀이
		switch (move) {
		case 0:
			diceValue[3] = diceValue[4];
			diceValue[4] = diceValue[6];
			diceValue[6] = diceValue[2];
			diceValue[2] = tmp;
			break;
		case 1:
			diceValue[3] = diceValue[2];
			diceValue[2] = diceValue[6];
			diceValue[6] = diceValue[4];
			diceValue[4] = tmp;
			break;
		case 2:
			diceValue[3] = diceValue[5];
			diceValue[5] = diceValue[6];
			diceValue[6] = diceValue[1];
			diceValue[1] = tmp;
			break;
		case 3:
			diceValue[3] = diceValue[1];
			diceValue[1] = diceValue[6];
			diceValue[6] = diceValue[5];
			diceValue[5] = tmp;
			break;
		}

		nr += dr[move];
		nc += dc[move];

		// 1. 새로운 숫자 주사위에 저장
		// 2. 맨 위의 숫자 return
		if (map[nr][nc] == 0) {
			map[nr][nc] = diceValue[6];
		} else {
			diceValue[6] = map[nr][nc];
			map[nr][nc] = 0;
		}

		return diceValue[3];
	}
}