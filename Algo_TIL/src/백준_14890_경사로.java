import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14890 경사로
 */
public class 백준_14890_경사로 {
	static int[][] map;
	static int N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N * N 지도가 존재함.
		// 길을 지나가려면 높이가 같은 길이거나
		// 높이 1, 길이 L 짜리 경사로를 설치한다.

		// 경사로는 낮은칸에 놓는다. L개의 연속 칸 경사로 바닥이 모두 접해야함
		// 낮은 칸 - 높은칸의 높이차이는 1이어야함
		// 경사로를 놓을 낮은 칸의 높이는 모두 같아야 함
		// L개의 칸이 연속되어 있어야함

		// == 아래는 불가능 ==
		// 1. 경사로에 또 경사로 놓기
		// 2. 놓을 바닥이 평평하지 않은경우
		// 3. 경사로 범위를 벗어나는 경우

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N * 2][N]; // 행에 통일하기 위한 2 * N 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 모든 줄의 기준을 행으로 통일하기 위한 작업
		for (int i = N; i < 2 * N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = map[j][i - N];
			}
		}

		int ans = 0;
		for (int i = 0; i < N * 2; i++) {
			if (slopeCalc(map[i])) {
				ans++;
			}
		}

		System.out.println(ans);
	}

	// 다음 경사로의 높이가 1차이날 때 L 길이만큼의 size가 있는지 확인
	// 경사로가 가능하다면 ans++
	// N 제한이 100이기 때문에 런타임 날 일은 없을것 같음
	private static boolean slopeCalc(int[] arr) {
	    boolean[] slope = new boolean[N]; // 경사로 설치 여부

	    for (int i = 0; i < N - 1; i++) {
	        int diff = arr[i] - arr[i + 1];

	        if (Math.abs(diff) > 1) return false; // 높이 차 1 초과

	        if (diff == -1) { // 오르막 설치 i부터 L칸 전 까지
	            for (int j = 0; j < L; j++) {
	                // 범위 밖이거나, 이미 경사로가 있거나, 높이가 일정하지 않으면 실패
	                if (i - j < 0 || slope[i - j] || arr[i] != arr[i - j]) return false;
	                slope[i - j] = true;
	            }
	        } 
	        else if (diff == 1) { // 내리막 설치 i+1 부터 L칸 뒤까지
	            for (int j = 1; j <= L; j++) {
	                // 범위 밖이거나, 이미 경사로가 있거나, 높이가 일정하지 않으면 실패
	                if (i + j >= N || slope[i + j] || arr[i + 1] != arr[i + j]) return false;
	                slope[i + j] = true;
	            }
	        }
	    }
	    return true;
	}
}