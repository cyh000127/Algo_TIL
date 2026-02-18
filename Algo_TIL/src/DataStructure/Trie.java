package DataStructure;

import java.util.HashMap;
import java.util.Map;

/*
 * Trie 구현
 * 문자열 전체를 하나의 노드에 저장하는 게 아니라, 한 단어씩 노드에 저장하는 트리
 */
public class Trie {

	// 노드 생성
	// 노드에는 하나의 단어, 자식 노드 그리고 단어가 끝났는지 판단하는 boolean 변수가 존재
	static class Node {
		// 자식 노드
		Map<Character, Node> chiledNode = new HashMap<Character, Node>();
		// 단어의 끝인지 체크
		boolean endOfword;
	}

	static class trie {
		// Trie 자료구조에서 rootNode는 기본 생성
		Node rootNode = new Node();

		// Trie에 문자열 저장
		void insert(String str) {
			// Trie는 항상 루트 노드에서 시작
			Node node = this.rootNode;

			// 문자열의 각 단어가 자식노드 중에 있는지 체크
			// 없으면 새로 생성
			for (int i = 0; i < str.length(); i++) {
				node = node.chiledNode.computeIfAbsent(str.charAt(i), key -> new Node());
			}

			// 저장 할 문자열의 마지막 단어라면 노드의 끝임을 명시
			node.endOfword = true;
		}

		// Trie에서 문자열 탐색
		boolean search(String str) {
			Node node = this.rootNode;

			// 문자열의 각 단어마다 노드가 존재하는지 체크
			for (int i = 0; i < str.length(); i++) {
				// get하는건 아무 의미 없이 존재하지 않을때 null 가져오는게 중요
				node = node.chiledNode.getOrDefault(str.charAt(i), null);
				// null이라면 return false
				if (node == null) {
					return false;
				}
			}

			// return true를 사용하지 않는 이유.
			// 문자열의 끝까지 탐색했다고 해서 그것이 단어를 찾았다는 말이 아님.
			// 예를 들어 card와 car이 있을 때
			// card를 저장한 후 car을 검색했을때 문자열의 끝까지 탐색은 하지만 단어를 찾은것은 아닌것처럼
			return node.endOfword;
		}
	}
}
