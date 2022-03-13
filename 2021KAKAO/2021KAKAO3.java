//합승 택시 요금
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node {
	int idx, cost;

	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}

class Solution {
	int INF = Integer.MAX_VALUE;
	ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	public int solution(int n, int s, int a, int b, int[][] fares) {
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] i : fares) {
			graph.get(i[0]).add(new Node(i[1], i[2]));
			graph.get(i[1]).add(new Node(i[0], i[2]));
		}
		int[] dA= new int[n+1],dB= new int[n+1],d= new int[n+1];
		dijkstra(a, dA);
		dijkstra(b, dB);
		dijkstra(s, d);
		int answer = INF;
		for(int i=1; i<n+1; i++)
			answer = Math.min(answer,dA[i]+dB[i]+d[i]);
		return answer;
	}
	public void dijkstra(int start, int[] d) {
		PriorityQueue<Node> q = new PriorityQueue<Node>((o1,o2)->Integer.compare(o1.cost, o2.cost));
		Arrays.fill(d,INF);
		d[start] = 0;
		q.offer(new Node(start,0));
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(d[node.idx] < node.cost)
				continue;
			for(int i=0; i<graph.get(node.idx).size(); i++) {
				Node thisNode = graph.get(node.idx).get(i);
				if(d[thisNode.idx]> d[node.idx] + thisNode.cost) {
					d[thisNode.idx]= d[node.idx] + thisNode.cost;
					q.offer(new Node(thisNode.idx,d[thisNode.idx]));
				}
			}
		}
	}
}