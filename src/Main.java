import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * 백준 1333
 * 부재중 전
 */

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 앨범의 노래 N 곡
		// 노래의 길이 L 초
		// 전화벨은 D초에 1번 씩

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		ArrayList<Boolean> sec = new ArrayList<Boolean>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < L; j++) {
				sec.add(true);
			}
			if (i != N - 1) {
				for (int x = 0; x < 5; x++) {
					sec.add(false);
				}
			}
		}

		int bell = 0;
		while (true) {
			if (bell < sec.size()) {
				if (!sec.get(bell)) {
					System.out.println(bell);
					break;
				}
			} else {
				System.out.println(bell);
				break;
			}
			bell += D;
		}
	}
}
