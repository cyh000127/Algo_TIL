import java.util.*;
import java.io.*;

/**
 * 15652 N과 M (4)
 */
public class 백준_15652_N과M4 {
	static int N, M;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 자연수 N,M이 주어졌을때 아래 조건을 만족하는 길이가 M인 수열을 모두 구하시오

		// 1. 1~N 까지 자연수 중 중복가능하게 M개
		// 2. 고른 수열은 자신을 포함한 오름차순인것

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		result = new int[M]; // 결과를 담을 배열

		findAll(1, 0);

		System.out.print(sb.toString());
	}

	private static void findAll(int start, int depth) {
		// M개를 모두 골랐다면 탈출
		if (depth == M) {
			for (int x : result) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
			return;
		}

		// 숫자(start)부터 N까지 반복
		for (int i = start; i <= N; i++) {
			result[depth] = i; // 현재 깊이에 숫자 저장
			findAll(i, depth + 1);
		}
	}
}