import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1316 백준 그룹 단어 체커
 */
public class 백준_1316_그룹단어체커 {
	public static void main(String[] args) throws IOException {
		// 한 단어가 연속으로 나오는 것 OK
		// 다른 단어로 바뀌어도 OK
		// 하지만 한 번 나왔던 단어가 또 나오는 순간 그룹 단어가 아님
		// N개의 단어가 주어질 때 그룹 단어를 찾아라

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int answer = 0;

		while (N-- > 0) {
			// 알파벳 개수 A-Z
			boolean[] alphabet = new boolean[26];
			String tempString = br.readLine();

			char prev = tempString.charAt(0);
			alphabet[prev - 'a'] = true;
			// 길이가 1이라면
			if (tempString.length() == 1) {
				answer++;
				continue;
			}

			for (int i = 1; i < tempString.length(); i++) {
				char now = tempString.charAt(i);

				// 이미 나왔었다면 그룹 단어 아님
				if (now != prev && alphabet[now - 'a']) {
					break;
				}

				prev = now; // prev 초기화

				alphabet[now - 'a'] = true; // boolean 배열에 추가

				if (i == tempString.length() - 1) {
					answer++;
				} // 끝날 때 까지 반복문을 탈출하지 않았다면 answer++
			}
		}
		System.out.println(answer);
	}
}