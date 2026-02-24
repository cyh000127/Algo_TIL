import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 14891 톱니바퀴
 */
public class Main {
	static LinkedList<Integer>[] wheel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 8개의 톱니를 가지는 톱니바퀴 4개가 일렬로 있음
		// 톱니는 시계, 반시계 방향으로 돌고 총 K회 돌리려고 함
		// 맞닿은 톱니의 극이 다르면 반시계방향으로 돌아감
		// 연쇄적으로 돌아가긴 하지만 결과적으로 한번의 행동에 한번의 결과가 나옴

		// K번 회전 시킨 후 네 톱니바퀴의 점수 합을 출력한다.

		// 각 톱니의 12시가 N극이면 0점, S극이면 1,2,4,8 점
		// N극은 0 / S극은 1

		wheel = new LinkedList[5];

		// 2. 각 방에 실제 LinkedList 객체를 넣어줘야 함 (초기화)
		for (int i = 0; i < 5; i++) {
			wheel[i] = new LinkedList<Integer>();
		}

		for (int i = 1; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i].add(str.charAt(j) - '0');
			}
		}

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rotate(Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()));
		}

		// S 극이 1이기 때문에 1,2,4,8을 곱합
		System.out.println(wheel[1].get(0) + wheel[2].get(0) * 2 + wheel[3].get(0) * 4 + wheel[4].get(0) * 8);

		// idx 2 = 오른쪽
		// idx 6 = 왼쪽
		// idx 0 = 12시

		// 시계방향 +1
		// 반시계방향 -1
		// arrayDeque에 넣을까?

	}

	private static void rotate(int x) {
		// 회전 전 값을 저장할 배열 arr
		int[][] arr = new int[5][2];

		// 회전전 2,6의 값을 저장해 둠
		for (int i = 1; i < 5; i++) {
			arr[i][0] = wheel[i].get(6);
			arr[i][1] = wheel[i].get(2);
		}

		// x 가 시계방향 회전일 경우
		if (x > 0) {

			// 방향을 저장
			int w = 1;

			for (int i = x; i < 4; i++) {
				// 다른 극이라면 다음 index도 rotate
				if (arr[i][1] != arr[i + 1][0]) {
					w = rotateSimul(w, i, 'R');
				} else if (arr[i][1] == arr[i + 1][0]) {
					// 같다면 break하는 로직도 제대로 추가 해줘야 함
					break;
				}
			}

			w = 1;

			for (int i = x; i > 1; i--) {

				// 다른 극이라면 다음 index도 rotate
				if (arr[i][0] != arr[i - 1][1]) {
					w = rotateSimul(w, i, 'L');
				} else if (arr[i][0] == arr[i - 1][1]) {
					// 같다면 break하는 로직도 제대로 추가 해줘야 함
					break;
				}
			}

			// 본인 회전 ( 시계 )
			wheel[x].addFirst(wheel[x].pollLast());

		} else {
			// x가 음수로 저장되어 있기 때문에 다시 양수로 변환
			x *= -1;

			// 방향을 저장 (반시계: -1)
			int w = -1;

			// x가 반시계 방향으로 회전하는 경우
			for (int i = x; i < 4; i++) {
				// 수정: wheel 대신 미리 저장된 arr[i][1]과 arr[i+1][0]
				if (arr[i][1] != arr[i + 1][0]) {
					w = rotateSimul(w, i, 'R');
				} else {
					// 극이 같다면 연쇄 회전 중단
					break;
				}
			}

			w = -1;
			for (int i = x; i > 1; i--) {
				// 수정: wheel 대신 미리 저장된 arr[i][0]과 arr[i-1][1]
				if (arr[i][0] != arr[i - 1][1]) {
					w = rotateSimul(w, i, 'L');
				} else {
					// 극이 같다면 연쇄 회전 중단
					break;
				}
			}

			// 본인 회전 ( 반시계 )
			wheel[x].addLast(wheel[x].pollFirst());
		}
	}

	// 방향을 반대로 돌리면서 같은게 나올 때 까지 돌리기
	private static int rotateSimul(int dir, int i, char r) {
		if (r == 'R') {
			if (dir == 1) {
				wheel[i + 1].addFirst(wheel[i + 1].pollLast());
				return -1;
			} else {
				wheel[i + 1].addLast(wheel[i + 1].pollFirst());
				return 1;
			}
		} else {
			if (dir == 1) {
				wheel[i - 1].addFirst(wheel[i - 1].pollLast());
				return -1;
			} else {
				wheel[i - 1].addLast(wheel[i - 1].pollFirst());
				return 1;
			}
		}
	}
}
