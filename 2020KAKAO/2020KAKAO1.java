import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Solution {
	public int[] solution(String[] gems) {
		HashMap<String,Integer>map=new HashMap<>();
		Set<String> set = new HashSet<>();
		Queue<String>q=new LinkedList<>();
		int start=0,point=0,end = gems.length;
		for(String s : gems)
			set.add(s);
		for(String s: gems) {
			if(!map.containsKey(s))
				map.put(s, 0);
			map.put(s, map.get(s)+1);
			q.offer(s);
			while(true) {
				String temp=q.peek();
				if(map.get(temp)>1) {
					point++;
					map.put(temp, map.get(temp)-1);
					q.poll();
				}else {
					break;
				}
			}
			if(set.size()==map.size()&&q.size()<end) {
				start=point;
				end=q.size();
			}
		}
		return new int[] {start+1,start+end};
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		s.solution(gems);
	}
}
