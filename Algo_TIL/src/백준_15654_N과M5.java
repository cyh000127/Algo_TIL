import java.util.*;
import java.io.*;

/**
 * 15654 N과 M (5)
 */
public class 백준_15654_N과M5 {
	static int N, M;
	static int[] arr, result;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 자연수 N,M이 주어졌을때 아래 조건을 만족하는 길이가 M인 수열을 모두 구하시오

		// 1. N개의 자연수 중 M개를 고른 수열

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		result = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		findAll(0);
		System.out.println(sb);
	}

	private static void findAll(int cnt) {
		if (cnt == M) {
			for (int x : result) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {

				// 재귀
				visited[i] = true;
				result[cnt] = arr[i];
				findAll(cnt + 1);
				visited[i] = false;
			}
		}

		return;
	}

