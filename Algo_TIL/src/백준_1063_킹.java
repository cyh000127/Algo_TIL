import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1063 백준
 * 킹
 */

public class 백준_1063_킹 {
	private enum Dir {
		R(0, 1), // R 오른쪽
		L(0, -1), // L 왼쪽
		B(-1, 0), // B 아래
		T(1, 0), // T 위
		RT(1, 1), // RT 우상
		LT(1, -1), // LT 좌상
		RB(-1, 1), // RB 우하
		LB(-1, -1); // LB 좌하

		private final int toCol;
		private final int toRow;

		Dir(int toRow, int toCol) {
			this.toCol = toCol;
			this.toRow = toRow;
		}

	}

	public static void main(String[] args) throws IOException {
		// 8 * 8 체스판
		// 킹의 현재 위치를 줌
		// 알파벳 = 열 / 숫자 = 행
		// A to H / 1 to 8

		// 킹이 돌 위치로 이동할 때에는 돌도 같은방향으로 한 칸 밈.
		// 킹, 돌이 체스판 밖으로 나갈 상황이라면 그 이동은 건너 뜀
		// 킹, 돌의 마지막 위치를 구하셈

		// 첫줄에 킹, 돌의 위치, 이동 횟수 N이 주어짐
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String kingPos = st.nextToken();
		String stonePos = st.nextToken();
		int N = Integer.parseInt(st.nextToken());

		// String -> int 배열로 변환 
		int[] king = { kingPos.charAt(1) - '1', kingPos.charAt(0) - 'A' };
		int[] stone = { stonePos.charAt(1) - '1', stonePos.charAt(0) - 'A' };

		while (N-- > 0) {
			String order = br.readLine();
			Dir dir = Dir.valueOf(order); // 입력받은 문자열을 Enum 상수로 변환

			// 킹이 이동할 다음 위치 계산
			int nextKingRow = king[0] + dir.toRow;
			int nextKingCol = king[1] + dir.toCol;

			// 체스판 밖으로 나가는지 검사
			if (nextKingRow < 0 || nextKingRow >= 8 || nextKingCol < 0 || nextKingCol >= 8) {
				continue; // 범위를 벗어나면 다음 명령 수행
			}

			// 킹의 다음 위치에 돌이 있는지 확인
			if (nextKingRow == stone[0] && nextKingCol == stone[1]) {
				// 돌이 있다면 돌도 같은 방향으로 한 칸 밀기
				int nextStoneRow = stone[0] + dir.toRow;
				int nextStoneCol = stone[1] + dir.toCol;

				// 돌이 체스판 밖으로 나가는지 확인
				if (nextStoneRow < 0 || nextStoneRow >= 8 || nextStoneCol < 0 || nextStoneCol >= 8) {
					continue; // 돌이 나가면 킹도 이동 불가
				}

				// 돌 이동
				stone[0] = nextStoneRow;
				stone[1] = nextStoneCol;
			}

			// 킹 이동
			king[0] = nextKingRow;
			king[1] = nextKingCol;
		}

		System.out.println(toChessPos(king));
		System.out.println(toChessPos(stone));
	}

	// int 배열 -> 체스 좌표로 변환
	private static String toChessPos(int[] pos) {
		char col = (char) (pos[1] + 'A');
		int row = pos[0] + 1;
		return String.valueOf(col) + row;
	}
}