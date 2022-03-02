//오픈채팅방
import java.util.HashMap;

class Solution {
	public String[] solution(String[] record) {
		HashMap<String,String> map = new HashMap<>();
		int cnt=0,i=0;
		for(String s : record) {
			String[] info  = s.split(" ");
			if(info[0].equals("Enter")) {
				map.put(info[1], info[2]);
				cnt++;
			}else if(info[0].equals("Leave")) {
				cnt++;
			}else if(info[0].equals(("Change"))){
				map.put(info[1], info[2]);
			}
		}
		String[] answer = new String[cnt];
		for(String s : record) {
			String[] info  = s.split(" ");
			if(info[0].equals("Enter")) {
				answer[i++] = map.get(info[1])+"님이 들어왔습니다.";
			}else if(info[0].equals("Leave")) {
				answer[i++] = map.get(info[1])+"님이 나갔습니다.";
			}
		}
		return answer;
	}
}
