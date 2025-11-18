import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14502 연구소
 */
public class Main {
	static int N, M;
	static int[][] map;
	static ArrayList<int[]> virusList;
	
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 0 허공, 1 벽, 2 바이러스
		// 바이러스는 4방향으로 계속 움직임

		// 벽을 세 개 놓은 후 바이러스가 퍼질 수 없는 영역을 안전 영역이라고 할 때
		// 안전영역의 최댓값을 구하셈

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		Queue<int[]> virus = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {

				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 2) {
					virus.add(new int[] { i, j });
				}
				map[i][j] = tmp;
			}
		}
		// dfs를 통한 벽 세우기
		dfs(0);

		System.out.println(ans);
	}

	private static void dfs(int wallCnt) {

		// 벽이 세개 세워졌다면 최대값 찾기
		if (wallCnt == 3) {
			findMax();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(wallCnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void findMax() {
		// 1. 시뮬레이션용 맵 복사
		int[][] copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		// 2. 지역 큐 q를 생성하고 초기 바이러스 위치 주입
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] v : virusList) {
			q.add(v);
		}

		// 3. BFS 정석 로직 (이중 for문 제거)
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (copyMap[nr][nc] == 0) {
						copyMap[nr][nc] = 2;
						virusList.add(new int[] { nr, nc });
					}
				}
			}
		}

		// 4. 안전 영역 카운트
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		ans = Math.max(cnt, ans);
	}
}