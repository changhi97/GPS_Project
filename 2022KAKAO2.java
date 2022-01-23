class Solution {
	public int solution(int n, int k) {
		int answer = 0;
		String[] temp = to_k(n, k).split("0");
		for (String s : temp) {
			if (!s.equals("") && chk_p(Long.parseLong(s)))
				answer++;
		}
		return answer;
	}
	public String to_k(int n, int k) {
		String result = "";
		while (n > 0) {
			result = n % k + result;
			n /= k;
		}
		return result;
	}
	public boolean chk_p(long num) {
		if (num < 2) return false;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
}

public class Main {
	public static void main(String args[]) {
		int n = 110011;
		int k = 10;
		Solution s = new Solution();
		s.solution(n, k);
	}
}
