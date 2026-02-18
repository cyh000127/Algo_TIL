package Algo;

import java.util.ArrayList;
import java.util.List;

/*
 * KMP (Knuth-Morris-Pratt Algorithm)
 * 문자열 패턴 매칭 알고리즘
 * 시간 복잡도: Θ(n+m)
 */

public class KMP {
	public static void main(String[] args) {
		String text = "ABABABACABA";
		String pattern = "ABABAC";

		List<Integer> matches = kmpSearch(text, pattern);
		System.out.println(matches);
	}

	private static List<Integer> kmpSearch(String str, String target) {

		List<Integer> res = new ArrayList<Integer>();

		int strLength = str.length();
		int targetLength = target.length();
		int[] pi = calcPi(target);

		int j = 0;

		for (int i = 0; i < strLength; i++) {

			// 실패함수 계산때와 동일한 이유를 통한 동일한 로직
			while (j > 0 && str.charAt(i) != target.charAt(j)) {
				j = pi[j - 1];
			}

			// 문자열이 일치한다면 j를 늘림
			if (str.charAt(i) == target.charAt(j)) {
				j++;
			}

			// j가 끝까지 매칭이 되었다면
			if (j == targetLength) {
				// 시작지점 idx에 대한 정보를 res에 담음 (이 부분이 커스텀 가능 영역)
				res.add(i - targetLength + 1);
				
				// 이 부분에 대해 이해가 잘 안되었었지만 최대한 이해하려고 노력했음
				// 완성된 문장을 찾아서 j == targetLength되는 것과 targetLength-1에서 찾기에 실패했을 때가 동일한 동작이기 때문이다.
				// 손으로 따라 그리면서 이해하면 쉬울것이다.
				j = pi[j - 1];
			}
		}
		return res;
	}

	private static int[] calcPi(String x) {

		int len = x.length();
		int[] pi = new int[len];

		// 접두사 / 접미사 매칭
		int j = 0;

		for (int i = 1; i < len; i++) {
			// j >1 -> 앞에서 하나 이상 매칭 됐다.
			// i != j -> 현재 j길이 만큼 맞아있던 접두/접미 매칭이 깨졌다.
			while (j > 0 && x.charAt(i) != x.charAt(j)) {
				// 그렇기에 j는 pi[j-1]로 회귀
				j = pi[j - 1];
			}

			// 지금 위 while 문을 통해 j가 몇이든 0까지 떨어질 가능성이 있음

			// i와 j가 같다면 pi[i] = ++j;
			if (x.charAt(i) == x.charAt(j)) {
				pi[i] = ++j;
			}

			// 일치하지 않으면 j= 0 유지
		}

		return pi;
	}
}
