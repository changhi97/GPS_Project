import java.util.ArrayList;
import java.util.HashMap;

class Info {
	ArrayList<Integer> list;
	int cnt;

	public Info() {
		list = new ArrayList<>();
		cnt = 0;
	}
}

class Solution {
	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		HashMap<Integer, Info> reportInfo = new HashMap<>();
		HashMap<String, Integer> nameMap = new HashMap<>();
		for(int i=0; i<id_list.length; i++)
			nameMap.put(id_list[i], i);
		for (String s : id_list) 
			reportInfo.put(nameMap.get(s), new Info());
		for(String s : report) {
			String[] temp = s.split(" ");
			if(!reportInfo.get(nameMap.get(temp[1])).list.contains(nameMap.get(temp[0]))) {
				reportInfo.get(nameMap.get(temp[1])).list.add(nameMap.get(temp[0]));
				reportInfo.get(nameMap.get(temp[1])).cnt++;
			}
		}
		
		for(int i : reportInfo.keySet()) {
			if(reportInfo.get(i).cnt >= k) {
				for(int j : reportInfo.get(i).list)
					answer[j]++;
			}
		}
		return answer;
	}
}

public class Main {
	public static void main(String args[]) {
		String[] id_list = { "muzi", "frodo", "apeach", "neo" };
		String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
		int k = 2;
		Solution s = new Solution();
		s.solution(id_list, report, k);
	}
}
