import java.util.ArrayList;

//기둥과 보 설치
class Solution {
	public int[][] solution(int n, int[][] build_frame) {
		boolean[][] height = new boolean[n + 1][n + 1], width = new boolean[n + 1][n + 1];
		for (int[] info : build_frame) {
			int x = info[0], y = info[1], struct = info[2], action = info[3];
			if (struct == 0) { // height
				if (action == 0) { // delete
					height[x][y] = false;
					if (!all_chk(height, width, n))
						height[x][y] = true;
				} else { // add
					if (height_chk(height, width, x, y))
						height[x][y] = true;
				}
			} else { // width
				if (action == 0) { // delete
					width[x][y] = false;
					if (!all_chk(height, width, n))
						width[x][y] = true;
				} else { // add
					if (width_chk(height, width, x, y))
						width[x][y] = true;
				}
			}
		}

		// make answer
		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				if (height[i][j])
					list.add(new int[] { i, j, 0 });
				if (width[i][j])
					list.add(new int[] { i, j, 1 });
			}
		}
		int[][] answer = new int[list.size()][3];
		for (int i = 0; i < answer.length; i++) {
			answer[i][0] = list.get(i)[0];
			answer[i][1] = list.get(i)[1];
			answer[i][2] = list.get(i)[2];
		}
		return answer;
	}

	public boolean height_chk(boolean[][] height, boolean[][] width, int x, int y) {
		if (y > 0 && height[x][y - 1])
			return true;
		else if (y==0 || (x > 0 && width[x - 1][y] || width[x][y]))
			return true;
		else return false;
	}

	public boolean width_chk(boolean[][] height, boolean[][] width, int x, int y) {
		if (y > 0 && (height[x][y - 1] || height[x + 1][y - 1]))
			return true;
		else if (x > 0 && (width[x - 1][y] && width[x + 1][y]))
			return true;
		else
			return false;
	}

	public boolean all_chk(boolean[][] height, boolean[][] width, int n) {
		for (int x = 0; x < n + 1; x++) {
			for (int y = 0; y < n + 1; y++) {
				if (height[x][y] && !height_chk(height, width, x, y))
					return false;
				else if (width[x][y] && !width_chk(height, width, x, y))
					return false;
			}
		}
		return true;
	}
}
