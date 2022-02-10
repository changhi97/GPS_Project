import java.util.HashMap;

class Point{
	int x,y;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
}

class Solution {
	public String solution(int[] numbers, String hand) {
		String answer = "";
		HashMap<Integer, Point> pointMap = new HashMap<>();
		pointMap.put(10, new Point(0, 0));// 10==*
		pointMap.put(0, new Point(1, 0));
		pointMap.put(11, new Point(2, 0));// 11==#
		pointMap.put(7, new Point(0, 1));
		pointMap.put(8, new Point(1, 1));
		pointMap.put(9, new Point(2, 1));
		pointMap.put(4, new Point(0, 2));
		pointMap.put(5, new Point(1, 2));
		pointMap.put(6, new Point(2, 2));
		pointMap.put(1, new Point(0, 3));
		pointMap.put(2, new Point(1, 3));
		pointMap.put(3, new Point(2, 3));
		int L = 10, R = 11;
		for (int i : numbers) {
			if (i == 1 || i == 4 || i == 7) {// L
				answer += "L";
				L = i;
			} else if (i == 3 || i == 6 || i == 9) {// R
				answer += "R";
				R = i;
			} else {
				int chk = distance(pointMap.get(L), pointMap.get(R), pointMap.get(i));
				if (chk < 0) {// L
					answer += "L";
					L = i;
				} else if (chk > 0) {// R
					answer += "R";
					R = i;
				} else {
					if (hand.equals("left")) {
						answer += "L";
						L = i;
					} else {
						answer += "R";
						R = i;
					}
				}
			}
		}
		return answer;
	}

	public int distance(Point L, Point R, Point dst) {
		int a = Math.abs(dst.x - L.x) + Math.abs(dst.y - L.y);
		int b = Math.abs(dst.x - R.x) + Math.abs(dst.y - R.y);
		return a - b;
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		s.solution(numbers,hand);
	}
}
