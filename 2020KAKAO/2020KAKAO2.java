import java.util.LinkedList;
import java.util.Queue;

class Node {
	int x;
	int y;
	int cost;
	int dir;

	public Node(int x, int y, int cost, int dir) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.dir = dir;
	}
}

class Solution {
	int answer = Integer.MAX_VALUE;
	int[] dx = { -1, 0, 1, 0 };// 위로가면 -1, 아래로 가면 1
	int[] dy = { 0, 1, 0, -1 };// 우로가면 1, 좌로가면 -1

	public int solution(int[][] board) {
		//boolean[][] v = new boolean[board.length][board.length];
		//int[][] temp = board.clone();
		//BFS(temp, v);//방문을 2차원 배열로 하면 값이 역전되는 상황이 발생할수도 있어 3차원 배열로 방향별로 판단해야한다 
		boolean[][][] v2 = new boolean[board.length][board.length][4];
		int[][] temp2 = board.clone();
		BFS2(temp2, v2);
		return answer;
	}

	public void BFS2(int[][] board, boolean[][][] v) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, -1)); // 시작점을 -1
		v[0][0][0] = v[0][0][1] = v[0][0][2] = v[0][0][3] = true;
		int n = board.length;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n - 1 && node.y == n - 1) {
				answer = Math.min(answer, node.cost);
			}
			// 0상 1우 2하 3좌
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];// x는 상,하 움직인다 i가 0 2일때만 값의 변화가 있다.
				int ny = node.y + dy[i];// y는 좌,우 움직인다 i가 1 3일때만 값의 변화가 있다.
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
					int sumCost = 0;
					if (node.dir == -1 || node.dir == i) { // 직선(출발포함)
						sumCost = node.cost + 100;
					} else { // 코너
						sumCost = node.cost + 600;
					}

					// 처음 방문하거나 이전에 방문했을 때의 cost보다 작거나 같은 비용이면
					if (v[nx][ny][i] == false || board[nx][ny] >= sumCost) {
						v[nx][ny][i] = true;
						board[nx][ny] = sumCost; // 값을 갱신해주고
						q.add(new Node(nx, ny, sumCost, i)); // 해당 지점으로 이동한다.
					}
				}
			}
		}
	}
	
	public void BFS(int[][] board, boolean[][] v) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, -1)); // 시작점을 -1
		v[0][0] = true;
		int n = board.length;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n - 1 && node.y == n - 1) {
				answer = Math.min(answer, node.cost);
			}
			// 0상 1우 2하 3좌
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];// x는 상,하 움직인다 i가 0 2일때만 값의 변화가 있다.
				int ny = node.y + dy[i];// y는 좌,우 움직인다 i가 1 3일때만 값의 변화가 있다.
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
					int sumCost = 0;
					if (node.dir == -1 || node.dir == i) { // 직선(출발포함)
						sumCost = node.cost + 100;
					} else { // 코너
						sumCost = node.cost + 600;
					}

					// 처음 방문하거나 이전에 방문했을 때의 cost보다 작거나 같은 비용이면
					if (v[nx][ny] == false || board[nx][ny] >= sumCost) {
						v[nx][ny] = true;
						board[nx][ny] = sumCost; // 값을 갱신해주고
						q.add(new Node(nx, ny, sumCost, i)); // 해당 지점으로 이동한다.
					}
				}
			}
		}
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 1, 1, 1, 1, 1, 0 }, { 1, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 1, 0, 0, 0, 1, 1, 1 }, { 1, 1, 1, 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1, 0 } };
		s.solution(board);
	}
}
