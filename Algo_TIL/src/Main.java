import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 3190 뱀
 */
public class Main {
	static int N, K, L, nr, nc, dir, prevTime, liveTime;
	static boolean[][] map, appleMap;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N * N의 보드
		// 뱀은 가장 왼쪽 위에서 시작 (0,0)
		// 뱀의 시작 길이는 1 (오른쪽을 향함)

		// 1. 몸 길이를 늘려 다음 칸에 머리를 놓음
		// 2. 벽이나 자기 자신에게 부딪히면 종료
		// 3. 이동 칸에 사과가 있다면 꼬리는 멈춰있음
		// 4. 이동 칸에 사과가 없다면 꼬리 칸을 비워줌

		// 게임이 몇 초 걸리는지 구하셈

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine()); // 사과의 개수
		map = new boolean[N][N];
		// 사과의 위치
		appleMap = new boolean[N][N];
		map[0][0] = true;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 사과는 시작이 (1,1)
			appleMap[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		L = Integer.parseInt(br.readLine());

		dir = 0;
		liveTime = 0;
		nr = 0;
		nc = 0;
		tail = new ArrayDeque<>();

		tail.add(new int[] { 0, 0 }); // 첫 이동은 tail,head가 둘 다 0, 0
		tail.add(new int[] { 0, 0 }); // 두번 째 이동은 tail 0,0 head가 1,0

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			// 이전 명령과 지금 명령의 시간 차이를 계산
			int time = Integer.parseInt(st.nextToken());
			int order = time - prevTime;

			snake(order, dir);

			dir = calcDir(st.nextToken()); // dir 갱신
			prevTime = time;
		}

		// 모든 명령을 수행하고도 살아있다면 실행할 로직
		snake(N, dir); // 지도 크기만큼 진행시키면 반드시 끝나게 되어있음
	}

// 시계 방향 (우하좌상)
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static Queue<int[]> tail;

	// time초 동안 dir방향으로 이동 -> 이동이 끝났다면 int 를 반환
	private static void snake(int time, int dir) {
		for (int i = 1; i <= time; i++) {

			nr += dr[dir];
			nc += dc[dir];

			// 종료 조건
			if (nr >= N || nr < 0 || nc < 0 || nc >= N || map[nr][nc]) {
				liveTime += i;
				// liveTime을 출력하고 종료
				System.out.println(liveTime);
				System.exit(0);
				break;
			}

			map[nr][nc] = true;

			if (appleMap[nr][nc]) {
				appleMap[nr][nc] = false;
				continue;
			}

			// 머리 부분 추가
			tail.add(new int[] { nr, nc });

			// 꼬리 삭제
			int[] nt = tail.poll();
			map[nt[0]][nt[1]] = false;
		}
		liveTime += time;
		return;
	}

	private static int calcDir(String token) {

		// L 왼쪽 (-1), D 오른쪽(+1)
		// 기본은 오른쪽
		if (token.equals("L")) {
			// -1 == 3
			if (dir == 0) {
				return dir = 3;
			} else
				return dir -= 1;
		} else {
			// 4 == 0
			return dir = (dir + 1) % 4;
		}
	}
}