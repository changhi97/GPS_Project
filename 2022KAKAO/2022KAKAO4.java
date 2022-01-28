//양과 늑대
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	int answer = 0; // 늑대가 같거나 많아지면
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	public int solution(int[] info, int[][] edges) {
		for (int[] i : edges) {
			if (!map.containsKey(i[0]))
				map.put(i[0], new ArrayList<>());
			map.get(i[0]).add(i[1]);
		}
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(0);	//root
		DFS(info, temp, 0, 0, 0);
		System.out.println(answer);
		return answer;
	}

	public void DFS(int[] info, ArrayList<Integer> list, int next, int sheep, int wolf) {
		if (info[next] == 0) {
			sheep++;
		} else {
			wolf++;
		}
		if (sheep > wolf) {
			answer = Math.max(answer, sheep);
		}else {
			return;
		}
		ArrayList<Integer> temp = new ArrayList<>();
		temp.addAll(list);
		if (map.containsKey(next))
			temp.addAll(map.get(next));
		temp.remove(Integer.valueOf(next));
		for (int i : temp) {
			DFS(info, temp, i, sheep, wolf);
		}
	}
}

public class Main {
	public static void main(String args[]) {
		int[] info = { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 };
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 },
				{ 4, 6 }, { 8, 9 } };
		Solution s = new Solution();
		s.solution(info, edges);
	}
}
