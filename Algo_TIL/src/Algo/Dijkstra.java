package Algo;

import java.util.*;
import java.io.*;

public class Dijkstra {
	static final int INF = 1_000_000_000;

	static class Edge implements Comparable<Edge> {
		int from; // 시작
		int to; // 종료
		int cost; // 가중치

		public Edge(int to, int cost) {
			this(-1, to, cost);
		}

		// 생성자
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		// 오름 차순
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) {
		// 예시
		String input = "7 11\r\n" + "0 1 32\r\n" + "0 2 31\r\n" + "0 5 60\r\n" + "0 6 51\r\n" + "1 2 21\r\n"
				+ "2 4 46\r\n" + "2 6 25\r\n" + "3 4 34\r\n" + "3 5 18\r\n" + "4 5 40\r\n" + "4 6 51";
		Scanner sc = new Scanner(input);

		String[] tmp = sc.nextLine().split(" ");

		int V = Integer.parseInt(tmp[0]);
		int E = Integer.parseInt(tmp[1]);

		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}

		// 그래프에 간선 정보를 인접 리스트에 추가
		for (int i = 0; i < E; i++) {
			String[] tmp2 = sc.nextLine().split(" ");

			int from = Integer.parseInt(tmp2[0]);
			int to = Integer.parseInt(tmp2[1]);
			int cost = Integer.parseInt(tmp2[2]);

			// 무방향 그래프라면
			// from -> to : 가중치 w
			graph.get(from).add(new Edge(from, to, cost));

			// to -> from : 가중치 w
			graph.get(to).add(new Edge(to, from, cost));

			// 양방향 그래프라면
			// graph.get(from).add(new Edge(from, to, cost));

			// 다익스트라 알고리즘을 통해
			// 시작 정점 0에서 모든 정점에 대한 최소비용경로를 계산

		}
		int[] dist = dijkstra(graph, V, E, 0);
		System.out.println(Arrays.toString(dist));

	}

	private static int[] dijkstra(List<List<Edge>> graph, int v, int e, int start) {
		// 모든 정점을 그리디하게 방문하기 때문에 방문 체크
		boolean[] visited = new boolean[v];

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.offer(new Edge(start, 0)); // 시작정점으로 탐색을 시작

		int[] dist = new int[v]; // 최소 비용 저장
		Arrays.fill(dist, INF); // 최소 비용 탐색이기 때문에 최대값 넣어두기
		dist[start] = 0; // 시작은 비용 0 시작

		// 현재까지의 비용이 가장 적은 경로의 정점 x를 뽑으면서
		// 다음 최소 비용을 갱신하는 과정을 반복
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int current = edge.to;
			int cost = edge.cost;

			if (visited[current]) {
				continue;
			}

			visited[current] = true;

			// 해당 정점으로부터 연결되어있는 간선 정보를 pq에 추가
			for (Edge next : graph.get(current)) {

				// 최소 비용 거리 계산
				// s -> current : cost 경로최소비용
				// current -> to : next.cost 간선 하나의 가중치
				// s -------> to : cost + next.cost
				// 기존의 s-> to계산 : dist[nxt.to]
				if (dist[next.to] > cost + next.cost) {
					dist[next.to] = cost + next.cost;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}

		return dist;
	}

}
