import java.util.*;
import java.io.*;

/**
 * 1893 시저 암호
 */
public class 백준_1893_시저암호 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 시저 암호 ( 특정 idx 만큼 + 된 알파벳 )
		// ex) 3만큼 시프트: X, Y, Z -> A, B, C
		// 알파벳 순서 A , 원문 W , 시저암호 문자열 S가 주어짐
		// 시프트 값의 범위 : 0 ~ A.length-1
		// 결국 원문이 " 단 한번 " 만 나오면 됨

		// 알파벳 A는 소문자, 대문자, 숫자만을 가짐을 보장한다.

		// 추측 가능한 시프트 값을 모두 찾아라

		// 시프트 값 존재 X 시 "no solution" 출력
		// 오직 하나만 만족 시 "unique: n" 출력
		// 여러개라면 "ambiguous: (오름차순)" 출력

		int N = Integer.parseInt(br.readLine()); // 테케를 나타내는 N

		StringBuilder sb = new StringBuilder();

		while (N-- > 0) {

			char[] A = br.readLine().toCharArray(); // 알파벳 순서 A
			char[] W = br.readLine().toCharArray(); // 원문 W
			char[] S = br.readLine().toCharArray(); // 암호 문자열 S

			// 고민 : W가 그저 회전 가능하다고 생각하기에는 A와 W가 다른 순간 큰일 나기 때문에 메서드 하나 빼서 사용하자

			// 1. A를 해시를 통해 저장 해두자
			// 2. W를 for문을 통해 변환 + kmp 반복
			// 3. 답이 나올 때마다 cnt++, Queue에 저장
			// 4. Queue Size에 따라 출력 바뀌게 만들면 될듯?

			encrypt = new HashMap<>();

			int len0 = A.length;

			for (int i = 0; i < len0 - 1; i++) {
				encrypt.put(A[i], A[i + 1]);
			}

			// 마지막으로 n -> 0 구현
			encrypt.put(A[len0 - 1], A[0]);

			len1 = W.length;
			len2 = S.length;

			// 암호화를 반복해도 Pi가 변하지 않기 때문에 미리 계산함.
			pi = findPi(W);

			q = new ArrayDeque<>();

			for (int i = 0; i < len0; i++) {
				// 단 하나만 존재하는지 체크
				if (kmpSearch(W, S)) {
					q.add(i);
				}
				// 암호화
				encrypt(W);

			}

			// 출력
			if (q.size() > 1) {
				sb.append("ambiguous: ");
				while (!q.isEmpty()) {
					sb.append(q.poll()).append(' ');
				}
			} else if (q.size() == 1) {
				sb.append("unique: ").append(q.poll());
			} else
				sb.append("no solution");

			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	static int len1, len2;
	static int[] pi;
	static Queue<Integer> q;
	static HashMap<Character, Character> encrypt;

	private static int[] findPi(char[] w) {
		int[] Pi = new int[len1];

		for (int i = 1, j = 0; i < len1; i++) {
			while (j > 0 && w[i] != w[j]) {
				j = Pi[j - 1];
			}

			if (w[i] == w[j]) {
				Pi[i] = ++j;
			}
		}

		return Pi;
	}

	// 재귀를 통해 kmpSearch를 구하려고 함.
	// -> 차라리 그냥 for문으로 encrypt 돌리면서 그걸 통해 kmpSearch하는게 나을듯
	// 기억해야 할 건 암호화를 반복해도 Pi가 변하지 않는다는 것.
	private static boolean kmpSearch(char[] w, char[] s) {
		int cnt = 0;

		for (int i = 0, j = 0; i < len2; i++) {
			while (j > 0 && w[j] != s[i]) {
				j = pi[j - 1];
			}

			if (w[j] == s[i]) {
				j += 1;
			}

			if (j == len1) {

				// 이미 하나 이상 찾은 상태라면 return
				if (cnt == 1) {
					return false;
				}
				cnt++;

				j = pi[j - 1];
			}
		}

		if (cnt == 1) {
			return true;
		}

		return false;
	}

	// 암호화 로직
	private static char[] encrypt(char[] arr) {
		for (int i = 0; i < len1; i++) {
			arr[i] = encrypt.get(arr[i]);
		}
		return arr;
	}
}