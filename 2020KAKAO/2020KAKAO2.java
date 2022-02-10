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
	int[] dx = { -1, 0, 1, 0 };// ���ΰ��� -1, �Ʒ��� ���� 1
	int[] dy = { 0, 1, 0, -1 };// ��ΰ��� 1, �·ΰ��� -1

	public int solution(int[][] board) {
		//boolean[][] v = new boolean[board.length][board.length];
		//int[][] temp = board.clone();
		//BFS(temp, v);//�湮�� 2���� �迭�� �ϸ� ���� �����Ǵ� ��Ȳ�� �߻��Ҽ��� �־� 3���� �迭�� ���⺰�� �Ǵ��ؾ��Ѵ� 
		boolean[][][] v2 = new boolean[board.length][board.length][4];
		int[][] temp2 = board.clone();
		BFS2(temp2, v2);
		return answer;
	}

	public void BFS2(int[][] board, boolean[][][] v) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, -1)); // �������� -1
		v[0][0][0] = v[0][0][1] = v[0][0][2] = v[0][0][3] = true;
		int n = board.length;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n - 1 && node.y == n - 1) {
				answer = Math.min(answer, node.cost);
			}
			// 0�� 1�� 2�� 3��
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];// x�� ��,�� �����δ� i�� 0 2�϶��� ���� ��ȭ�� �ִ�.
				int ny = node.y + dy[i];// y�� ��,�� �����δ� i�� 1 3�϶��� ���� ��ȭ�� �ִ�.
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
					int sumCost = 0;
					if (node.dir == -1 || node.dir == i) { // ����(�������)
						sumCost = node.cost + 100;
					} else { // �ڳ�
						sumCost = node.cost + 600;
					}

					// ó�� �湮�ϰų� ������ �湮���� ���� cost���� �۰ų� ���� ����̸�
					if (v[nx][ny][i] == false || board[nx][ny] >= sumCost) {
						v[nx][ny][i] = true;
						board[nx][ny] = sumCost; // ���� �������ְ�
						q.add(new Node(nx, ny, sumCost, i)); // �ش� �������� �̵��Ѵ�.
					}
				}
			}
		}
	}
	
	public void BFS(int[][] board, boolean[][] v) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, -1)); // �������� -1
		v[0][0] = true;
		int n = board.length;
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.x == n - 1 && node.y == n - 1) {
				answer = Math.min(answer, node.cost);
			}
			// 0�� 1�� 2�� 3��
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];// x�� ��,�� �����δ� i�� 0 2�϶��� ���� ��ȭ�� �ִ�.
				int ny = node.y + dy[i];// y�� ��,�� �����δ� i�� 1 3�϶��� ���� ��ȭ�� �ִ�.
				if (nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1) {
					int sumCost = 0;
					if (node.dir == -1 || node.dir == i) { // ����(�������)
						sumCost = node.cost + 100;
					} else { // �ڳ�
						sumCost = node.cost + 600;
					}

					// ó�� �湮�ϰų� ������ �湮���� ���� cost���� �۰ų� ���� ����̸�
					if (v[nx][ny] == false || board[nx][ny] >= sumCost) {
						v[nx][ny] = true;
						board[nx][ny] = sumCost; // ���� �������ְ�
						q.add(new Node(nx, ny, sumCost, i)); // �ش� �������� �̵��Ѵ�.
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
