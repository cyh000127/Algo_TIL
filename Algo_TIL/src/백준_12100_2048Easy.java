import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 12100 2048(Easy)
 */
public class 백준_12100_2048Easy {
	static int N;
	static int maxValue = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// N * N board 존재
		// 한번에 한방향의 끝으로 전부 이동시킴
		// 이동 중 같은 수를 가진 경우 합침 (x2)
		// 한번 합쳐진 수는 합쳐질 수 없음 (boolean)
		// 1<= N <= 20

		// 5번 움직여서 얻을 수 있는 가장 큰 수를 구하셈

		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];

		int currMax = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				arr[i][j] = tmp;

				if (tmp > currMax) {
					currMax = tmp;
				}
			}
		}

		// maxValue 변수를 선언하고
		// 결국 최고 숫자보다 *2^n이 가장 높은 숫자이기 떄문에
		// 이를 이용해서 재귀를 돌리면서 기존 max 보다 낮다면 바로 탈출
		dfs(0, currMax, arr);

		System.out.println(maxValue);
	}

	// 상 하 좌 우
	static int[] dir = { 1, 2, 3, 4 };

	private static void dfs(int cnt, int max, int[][] arr) {
		if (cnt == 10) {
			if (maxValue < max) {
				maxValue = max;
			}
			return;
		}

		// 남은 횟수(10 - cnt) 동안 매번 합쳐진다고 가정할 때의 최대 잠재력
		if (max * Math.pow(2, 10 - cnt) <= maxValue) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 배열을 깊은 복사를 통해 관리함
			int[][] copyArray = new int[N][N];
			for (int j = 0; j < N; j++) {
				copyArray[j] = arr[j].clone();
			}

			int[][] now = play(dir[i], copyArray);

			int nowMax = findMax(now);

			// 지금 만들 수 있는 최대가 이론상 최대 이동까지 불가능 하다면 가지치기
			if (max / nowMax - 1 > 10 - cnt) {
				continue;
			}

			dfs(cnt + 1, nowMax, now);
		}
	}

	// 나중에 줄일 수는 있을거 같은데 일단 findMax를 통해 지금의 최대값을 찾음
	private static int findMax(int[][] now) {
		int x = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (now[i][j] > x) {
					x = now[i][j];
				}
			}
		}
		return x;
	}

	/*
	 * 미는 로직 1. 상,하는 행(가로)을 계산 / 좌,우는 열(세로)를 계산 2. 반복문을 통해 0이 아닌 수라면 차곡차곡 쌓음.
	 * 3.반복문이기 때문에 이전 수를 tmp에 저장 -> 이전수와 지금 수가 같다면 원래 수에 *2하고 삭제 4. tmp를 0으로 초기화 (두
	 * 번 합성되는 것을 막음) 5. 빈 자리만큼 0으로 추가하기.
	 * 
	 * 이걸 Array로 할 수 있나? 지금 2차원 배열인데
	 */
	private static int[][] play(int d, int[][] copyArray) {
		// 일단 q를 선언
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();

		switch (d) {
		case 1: // 상
			for (int i = 0; i < N; i++) {
				int tmp = 0;

				for (int j = 0; j < N; j++) {
					if (copyArray[j][i] > 0) {
						// tmp와 copyArray가 같다면
						if (tmp > 0 && tmp == copyArray[j][i]) {
							// q 에서 하나 빼서 2를 곱한 후 다시 삽입
							q.add(q.pollLast() * 2);
							tmp = 0; // tmp 초기화
						} else {
							q.add(copyArray[j][i]);
							tmp = copyArray[j][i];
						}
					}
				}
				// 반복문이 종료되고 q에 모두 쌓였다면

				for (int j = 0; j < N; j++) {
					// q의 size만큼 돌리기
					if (q.size() > 0) {
						copyArray[j][i] = q.pollFirst();
					} else
						// 아니라면 그냥 0 추가
						copyArray[j][i] = 0;
				}

			}
			break;

		case 2: // 하
			for (int i = 0; i < N; i++) {
				int tmp = 0;

				for (int j = 0; j < N; j++) {
					if (copyArray[N - j - 1][i] > 0) {
						// tmp와 copyArray가 같다면
						if (tmp > 0 && tmp == copyArray[N - j - 1][i]) {
							// q 에서 하나 빼서 2를 곱한 후 다시 삽입
							q.add(q.pollLast() * 2);
							tmp = 0; // tmp 초기화
						} else {
							q.add(copyArray[N - j - 1][i]);
							tmp = copyArray[N - j - 1][i];
						}
					}
				}
				// 반복문이 종료되고 q에 모두 쌓였다면

				for (int j = 0; j < N; j++) {
					// q의 size만큼 돌리기
					if (q.size() > 0) {
						copyArray[N - j - 1][i] = q.pollFirst();
					} else
						// 아니라면 그냥 0 추가
						copyArray[N - j - 1][i] = 0;
				}

			}
			break;

		case 3: // 좌
			for (int i = 0; i < N; i++) {

				int tmp = 0;

				for (int j = 0; j < N; j++) {
					if (copyArray[i][j] > 0) {
						// tmp와 copyArray가 같다면
						if (tmp > 0 && tmp == copyArray[i][j]) {
							// q 에서 하나 빼서 2를 곱한 후 다시 삽입
							q.add(q.pollLast() * 2);
							tmp = 0; // tmp 초기화
						} else {
							q.add(copyArray[i][j]);
							tmp = copyArray[i][j];
						}
					}
				}
				// 반복문이 종료되고 q에 모두 쌓였다면

				for (int j = 0; j < N; j++) {
					// q의 size만큼 돌리기
					if (q.size() > 0) {
						copyArray[i][j] = q.pollFirst();
					} else
						// 아니라면 그냥 0 추가
						copyArray[i][j] = 0;
				}

			}
			break;

		case 4: // 우
			for (int i = 0; i < N; i++) {

				int tmp = 0;

				for (int j = 0; j < N; j++) {
					if (copyArray[i][N - j - 1] > 0) {
						// tmp와 copyArray가 같다면
						if (tmp > 0 && tmp == copyArray[i][N - j - 1]) {
							// q 에서 하나 빼서 2를 곱한 후 다시 삽입
							q.add(q.pollLast() * 2);
							tmp = 0; // tmp 초기화
						} else {
							q.add(copyArray[i][N - j - 1]);
							tmp = copyArray[i][N - j - 1];
						}
					}
				}

				// 반복문이 종료되고 q에 모두 쌓였다면
				for (int j = 0; j < N; j++) {
					// q의 size만큼 돌리기
					if (q.size() > 0) {
						copyArray[i][N - j - 1] = q.pollFirst();
					} else
						// 아니라면 그냥 0 추가
						copyArray[i][N - j - 1] = 0;
				}
			}
			break;
		}
		return copyArray;
	}
}