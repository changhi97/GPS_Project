import java.util.ArrayList;

class Solution {
	public int solution(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		ArrayList<String> A = new ArrayList<>();
		ArrayList<String> B = new ArrayList<>();
		ArrayList<String> U = new ArrayList<>();
		ArrayList<String> R = new ArrayList<>();
		for (int i = 0; i < str1.length() - 1; i++) {
			char a = str1.charAt(i), b = str1.charAt(i + 1);
			if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z')
				A.add(a + "" + b);
		}
		for (int i = 0; i < str2.length() - 1; i++) {
			char a = str2.charAt(i), b = str2.charAt(i + 1);
			if (a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z')
				B.add(a + "" + b);
		}
		for (String s : A) {
			if (B.contains(s)) {
				B.remove(s);
				R.add(s);
			}
			U.add(s);
		}
		U.addAll(B);
		if (U.size() == 0)
			return 65536;
		return (int)(((float) R.size() / (float) U.size()) * 65536);
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
//		String str1 = "FRANCE";
//		String str2 = "french";
		s.solution(str1, str2);
	}
}
