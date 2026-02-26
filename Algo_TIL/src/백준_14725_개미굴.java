import java.util.*;
import java.io.*;

/**
 * 14725 개미굴
 */
public class 백준_14725_개미굴 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 로봇 개미는 개미굴의 각 층의 먹이를 파악한다.
		// 더 이상 내려갈 수 없다면 신호를 보낸다.
		// 주어지는 입력은
		// 띄어쓰기를 기준으로 한 층씩 내려가면서 파악한 정보이다.

		// 개미굴의 구조를 손으로 그려라
		// 한 층을 ' -- ' 이걸로 표현

		// 정보 개수 N
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Trie trie = new Trie(); // 인스턴스 생성

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			trie.insert(t, st); // insert 메서드 호출
		}
		trie.print(trie.rootNode, 0);
	}

	// Node 클래스
	static class Node {
		// 순서를 보장하기 위해 TreeMap 사용
		Map<String, Node> chiledNode = new TreeMap<String, Node>();
		boolean end;
	}

	// Trie 클래스
	static class Trie {
		Node rootNode = new Node();

		// char, String등을 저장해야하기 때문에 StringTokenizer를 이용했음
		// t는 length
		void insert(int t, StringTokenizer st) {
			Node node = this.rootNode;

			for (int i = 0; i < t; i++) {
				// 키 값이 없으면 새로운 노드 생성
				node = node.chiledNode.computeIfAbsent(st.nextToken(), key -> new Node());
			}

			// 단어의 종료
			node.end = true;
		}

		// 재귀를 통한 출력
		void print(Node node, int depth) {

			// StringBuilder를 통해 하는 것 보다 sysout으로 바로바로 출력하는게 구현이 편한거 같음.
			for (String key : node.chiledNode.keySet()) {
				// 깊이만큼 "--" 출력
				for (int i = 0; i < depth; i++) {
					System.out.print("--");
				}
				// Key 출력
				System.out.println(key);

				// 재귀를 통해 다음 깊이의 node 탐색
				print(node.chiledNode.get(key), depth + 1);

			}
		}

	}

}