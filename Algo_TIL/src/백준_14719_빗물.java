import java.util.*;
import java.io.*;

/**
 * 14719 빗물
 */
public class 백준_14719_빗물 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 빗물이 고이는 총량을 구하여라.

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 문제에서 H는 필요 없음
		st.nextToken();

		int W = Integer.parseInt(st.nextToken());

		int[] arr = new int[W];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;

		// 최저 높이
		int minimumHeight = 0;

		// 앞에서 부터
		int i = 0;
		// 뒤에서부터
		int j = W - 1;
		while (i < j) {
			int min = Math.min(arr[i], arr[j]);

			// 부족한 만큼 채우기
			for (int x = i; x < j; x++) {
				if (arr[x] < min) {
					int tmp = min - arr[x];
					cnt += tmp;
					arr[x] = min;

					minimumHeight = min;
				}
			}

			// arr[j]가 min보다 커질때까지 j를 --
			while (min == arr[j]) {
				j--;
			}

			while (min == arr[i]) {
				i++;
			}
		}
		System.out.println(cnt);
	}
}