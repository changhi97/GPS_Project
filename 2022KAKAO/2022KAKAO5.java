//파괴되지 않은 건물
class Solution {
	public int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int[][] dp = new int[board.length + 1][board[0].length + 1];
		for (int i = 0; i < skill.length; i++) {
			int mode = skill[i][0], r1 = skill[i][1], c1 = skill[i][2], r2 = skill[i][3], c2 = skill[i][4],
					change = skill[i][5];
			if (mode == 1)
				change = -change;
			dp[r1][c1] += change;
			dp[r1][c2 + 1] -= change;
			dp[r2 + 1][c1] -= change;
			dp[r2 + 1][c2 + 1] += change;
		}
		for (int i = 0; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] += dp[i][j - 1];
			}
		}
		for (int i = 0; i < dp[0].length; i++) {
			for (int j = 1; j < dp.length; j++) {
				dp[j][i] += dp[j - 1][i];
			}
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] + dp[i][j] > 0)
					answer++;
			}
		}
		return answer;
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		int[][] board = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] skill = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };
		s.solution(board, skill);
	}
}
