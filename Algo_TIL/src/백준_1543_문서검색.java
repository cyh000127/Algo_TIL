import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1543 백준 문서 검색
 */
public class 백준_1543_문서검색 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 특정 단어가 몇 번 들어가 있는지 계산하는 로직
		// 중복으로 세지는 말 것

		String doc = br.readLine();
		String word = br.readLine();

		int ans = 0, startIndex = 0;

		// word가 처음 나오는 위치를 찾아서 index를 옮김
		while ((startIndex = doc.indexOf(word, startIndex)) != -1) {
			ans++;
			
			// 단어의 길이만큼 index 추가
			startIndex += word.length();
		}
		System.out.println(ans);
	}
}