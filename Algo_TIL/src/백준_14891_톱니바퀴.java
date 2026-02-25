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
public class 백준_14891_톱니바퀴 {
	static LinkedList<Integer>[] wheel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 총 8개의 톱니를 가지는 톱니바퀴 4개가 일렬로 있음
		// 톱니는 시계, 반시계 방향으로 돌고 총 K회 돌리려고 함
		// 맞닿은 톱니의 극이 다르면 반시계방향으로 돌아감
		// 연쇄적으로 돌아가긴 하지만 결과적으로 한번의 행동에 한번의 결과가 나옴
		
		// K번 회전 시킨 후 네 톱니바퀴의 점수 합을 출력한다.
		
		// 각 톱니의 12시가 N극이면 0점, S극이면 1,2,4,8
		// N극은 0 / S극은 1

		wheel = new LinkedList[5];

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
			rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		// S 극이 1이기 때문에 1,2,4,8을 곱함
		System.out.println(wheel[1].get(0) + wheel[2].get(0) * 2 + wheel[3].get(0) * 4 + wheel[4].get(0) * 8);
	}

	private static void rotate(int idx, int dir) {
		// 각 톱니가 회전할 방향을 저장할 배열
		// 0: 정지, 1: 시계, -1: 반시계
		int[] dirs = new int[5];
		dirs[idx] = dir;

		// 회전 여부 결정
		// 오른쪽
		for (int i = idx; i < 4; i++) {
			if (wheel[i].get(2) != wheel[i + 1].get(6)) {
				dirs[i + 1] = -dirs[i];
			} else {
				break;
			}
		}

		// 왼쪽
		for (int i = idx; i > 1; i--) {
			if (wheel[i].get(6) != wheel[i - 1].get(2)) {
				dirs[i - 1] = -dirs[i];
			} else {
				break;
			}
		}

		// 실제 회전
		for (int i = 1; i <= 4; i++) {
			if (dirs[i] == 1) {
				// 시계 방향
				wheel[i].addFirst(wheel[i].pollLast());
			} else if (dirs[i] == -1) {
				// 반시계 방향
				wheel[i].addLast(wheel[i].pollFirst());
			}
		}
	}
}