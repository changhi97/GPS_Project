//압축
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	public int[] solution(String msg) {
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<Integer> num = new ArrayList<>();
		for (int i = 0; i < 26; i++)
			map.put((char) (i + 65) + "", i + 1);
		int i=0,j=1;
		String prev = msg.substring(i, j);
		while(j<msg.length()) {
			String temp = msg.substring(i, j+1);
			if (!map.containsKey(temp)) {
				num.add(map.get(prev));
				map.put(temp, map.size() + 1);
				i+=prev.length();
			} else {
				prev = temp;
				j++;
			}
		}
		num.add(map.get(prev));
		int[] answer  = new int[num.size()];
		for(int n= 0; n<answer.length; n++)
			answer[n] = num.get(n);
		return answer;
	}
}
