//징검다리 건너기
class Solution {
	public int solution(int[] stones, int k) {
		int start = 0, end = Integer.MAX_VALUE, mid = 0, answer = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			int cnt  = 0;
			for (int stone : stones) {
				if(stone -mid < 0) {
					cnt++;
					if(cnt==k)
						break;
				}else {
					cnt = 0;
				}
			}
			if(cnt>=k) {
				end = mid -1;
			}else {
				start = mid +1;
				answer= mid;
				//answer = Math.max(cnt, answer);
			}
		}
		System.out.println(answer);
		return answer;
	}
}
