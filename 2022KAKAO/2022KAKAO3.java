//주차 요금 계산
import java.util.Arrays;
import java.util.HashMap;

class Solution {
	public int[] solution(int[] fees, String[] records) {
		HashMap<String, String> timeMap = new HashMap<>();
		HashMap<String, Integer> carFee = new HashMap<>();
		for (String s : records) {
			String[] temp = s.split(" ");
			if (!carFee.containsKey(temp[1]))
				carFee.put(temp[1], 0);
			if (temp[2].equals("IN")) {
				timeMap.put(temp[1], temp[0]);
			} else {
				int in = to_min(timeMap.get(temp[1]));
				int out = to_min(temp[0]);
				carFee.put(temp[1], carFee.get(temp[1]) + out - in);
				timeMap.remove(temp[1]);
			}
		}
		for (String s : timeMap.keySet()) {
			int in = to_min(timeMap.get(s));
			int out = to_min("23:59");
			carFee.put(s, carFee.get(s) + out - in);
		}
		Object[] temp = carFee.keySet().toArray();
		Arrays.sort(temp);
		int[] answer = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			answer[i] = calc(carFee.get(temp[i]), fees);
		}
		return answer;
	}

	public int calc(int time, int[] fees) {
		int answer = 0;
		time -= fees[0];
		answer += fees[1];
		if(time<=0) return answer;
		answer += (Math.ceil((double)time/(fees[2]))*fees[3]);
		return answer;
	}

	public int to_min(String time) {
		String[] s = time.split(":");
		return Integer.parseInt(s[0]) * 60 + Integer.parseInt(s[1]);
	}
}
public class Main {
	public static void main(String args[]) {
		int[] fees = { 180, 5000, 10, 600 };
		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
		Solution s = new Solution();
		s.solution(fees, records);
	}
}
