//거리두기 확인하기
import java.util.LinkedList;
import java.util.Queue;

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		for (int i = 0; i < places.length; i++) {
			answer[i] = chk(places[i]);
		}
		return answer;
	}

	public int chk(String[] place) {
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length(); j++) {
				if (place[i].charAt(j) == 'P') {
					if (!bfs(place, i, j))
						return 0;
				}
			}
		}
		return 1;
	}

	public boolean bfs(String[] place, int x1, int y1) {
		//Stack<Node> stack = new Stack<>();
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x1, y1));
		boolean[][] v = new boolean[place.length][place.length];
		v[x1][y1] = true;
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < 4; i++) {
				int x2 = node.x + dx[i];
				int y2 = node.y + dy[i];
				if (x2 < 0 || y2 < 0 || x2 >= place.length || y2 >= place[i].length())
					continue;
				if (Math.abs(x1 - x2) + Math.abs(y1 - y2) > 2 || v[x2][y2])
					continue;
				v[x2][y2] = true;
				char c = place[x2].charAt(y2);
				if (c == 'P')
					return false;
				else if (c == 'X')
					continue;
				else if (c == 'O')
					q.offer(new Node(x2, y2));
			}
		}
		return true;
	}
}
