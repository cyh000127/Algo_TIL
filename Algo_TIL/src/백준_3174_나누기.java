import java.util.*;
import java.io.*;

/**
 * 3174 나누기
 */
public class 백준_3174_나누기 {

	static final int MOD = 1337377;
	static char[] mainString;
	static int L;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 긴 단어를 작은 단어로 나누려고 함.
		// 작은 단어는 입력으로 주어지는 집합 S에 포함되어야 함
		// 1337377로 나눈 나머지를 구하셈 (MOD)

		// 첫 째줄 300_000글자의 단어
		mainString = br.readLine().toCharArray();
		L = mainString.length;

		int N = Integer.parseInt(br.readLine());

		Trie tr = new Trie();
		for (int i = 0; i < N; i++) {
			tr.insert(br.readLine());
		}

		// 최대 제한 300,000
		dp = new int[L + 1];
		dp[L] = 1; // 문자열 끝에 가면 1가지 경우로 인정

		for (int i = L - 1; i >= 0; i--) {
			Node node = tr.rootNode;

			for (int j = i; j < L; j++) {
				int charIdx = mainString[j] - 'a';
				node = node.childNode[charIdx];

				if (node == null) {
					break;
				}
			}
		}

		// 뒤에서부터 경우의 수 찾기
		for (int i = L - 1; i >= 0; i--) {
			tr.findMatches(i);
		}

		System.out.println(dp[0]);

	}

	/*
	 * Node
	 */
	static class Node {
		// 소문자 알파벳만 나오기 떄문에 알파벳 개수+1
		Node[] childNode = new Node[26];
		boolean end; // 끝을 확인

	}

	/*
	 * Trie
	 */
	static class Trie {
		Node rootNode = new Node();

		// 단어 삽입
		void insert(String str) {
			Node node = this.rootNode;

			for (int i = 0; i < str.length(); i++) {
				int idx = str.charAt(i) - 'a';
				if (node.childNode[idx] == null) {
					node.childNode[idx] = new Node();
				}
				node = node.childNode[idx];
			}

			node.end = true;
		}

		
		// 찾기
		void findMatches(int start) {
			Node node = this.rootNode;

			for (int i = start; i < L; i++) {
				// 배열의 값을 통해 노드를 전달하게
				int idx = mainString[i] - 'a';

				// 트라이에 해당 문자가 없으면 더 긴 단어는 존재할 수 없음
				if (node.childNode[idx] == null) {
					break;
				}

				node = node.childNode[idx];

				// 단어 집합에 포함된 단어를 찾은 경우
				if (node.end) {
					// 현재 위치 i까지 단어를 만들었으므로,
					// i+1 위치의 dp 값을 현재 시작 위치인 start의 dp에 더함
					dp[start] = (dp[start] + dp[i + 1]) % MOD;
				}
			}
		}
	}
}