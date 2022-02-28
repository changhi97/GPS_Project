//프렌즈4블록
import java.util.ArrayList;

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	public int solution(int m, int n, String[] board) {
		ArrayList<Point> pointList = new ArrayList<>();
		String[] newBoard = new String[n];
		for(int i=0; i<n; i++) {
			newBoard[i]="";
			for(int j=0; j<m; j++)
				newBoard[i] = board[j].charAt(i)+newBoard[i]; 
		}
		while (true) {
			findPoint(newBoard, pointList);
			if (pointList.size() == 0)
				break;
			removePoint(newBoard,pointList);
			pointList.clear();
		}
		int answer= m*n;
		for(int i=0; i<newBoard.length; i++)
			answer -=newBoard[i].length();
		return answer;
	}

	public void findPoint(String[] board, ArrayList<Point> pointList) {
		for (int i = 0; i < board.length-1; i++) {
			if(board[i].length()<2 || board[i+1].length()<2)
				continue;
			int len = Math.min(board[i].length(), board[i+1].length());
			for (int j = 0; j < len-1; j++) {
				char a=board[i].charAt(j),b=board[i].charAt(j+1),c=board[i+1].charAt(j),d=board[i+1].charAt(j+1);
				if(a==b&&a==c&&a==d) {
					pointList.add(new Point(i,j));
					pointList.add(new Point(i,j+1));
					pointList.add(new Point(i+1,j));
					pointList.add(new Point(i+1,j+1));
				}
			}
		}
	}

	public void removePoint(String[] board, ArrayList<Point> pointList) {
		for(Point p : pointList) {
			board[p.x] =board[p.x].substring(0, p.y) + " "+board[p.x].substring(p.y+1, board[p.x].length());
		}
		for(int i=0; i <board.length; i++)
			board[i] = board[i].replaceAll(" ","");
	}
}

