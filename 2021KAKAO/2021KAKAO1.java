//광고삽입
class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		int len = changeToSec(play_time);
		int[] stamp = new int[len];	//재생시간을 초로 변환하여 배열생성
		for(String log : logs) {
			String[] time = log.split("-");
			int start = changeToSec(time[0]);
			int end = changeToSec(time[1]);
			for(int i = start; i<end; i++)
				stamp[i]++;	//사용자의 플레이 시간을 초로 변환하여 배열에 저장하여 초 단위로 플레이정보 저장  
		}
		int start = 0,answer = start;
		int end = changeToSec(adv_time);
		long sum = 0,max=0;
		for(int i  = start; i<end; i++)
			sum+=stamp[i];	//광고 시간만큼 몇명이 시청하였는지 저장
		max = sum;
		while(len>end) {	//재생시간 끝까지 반복하며 최댓값을 찾는다
			sum-=stamp[start];
			sum+=stamp[end];
			if(max<sum) {
				max = sum;
				answer = start+1;
			}
			start++;
			end++;
		}
		return changeToTime(answer);
	}

	public int changeToSec(String time) {	//--:--:-- 형식을 초 단위로 변환
		String[] arr = time.split(":");
		return Integer.parseInt(arr[0])*3600 + Integer.parseInt(arr[1])*60+Integer.parseInt(arr[2]);
	}

	public String changeToTime(int time) {	//초 단위의 시간을 --:--:--형식으로 변환
		String h = Integer.toString(time/3600);
		time %= 3600;
		String m = Integer.toString(time/60);
		String s= Integer.toString(time %60);
		if(h.length()<2) h="0"+h;
		if(m.length()<2) m="0"+m;
		if(s.length()<2) s="0"+s;
		return h+":"+m+":"+s;
	}
}