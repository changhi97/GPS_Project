import java.util.HashMap;

class Node {
	HashMap<Character, Node> childs;
	int cnt;
	public Node() {
		this.childs = new HashMap<>();
		this.cnt = 0;
	}
}

class Trie {
	Node front, back;

	public Trie() {
		this.front = new Node();
		this.back = new Node();
	}

	public void insert(String s) {
		insert_back(s);
		insert_front(s);
	}

	public void insert_back(String s) {
		Node thisNode = this.back;
		for (int i = s.length() - 1; i >= 0; i--) {
			thisNode.cnt++;
			thisNode = thisNode.childs.computeIfAbsent(s.charAt(i), key -> new Node());
		}
	}

	public void insert_front(String s) {
		Node thisNode = this.front;
		for (int i = 0; i < s.length(); i++) {
			thisNode.cnt++;
			thisNode = thisNode.childs.computeIfAbsent(s.charAt(i), key -> new Node());
		}
	}

	public int search(String s) {
		if (s.charAt(0) == '?') {
			int answer = search_back(s);
			return (answer == -1) ? 0 : answer;
		} else {
			int answer = search_front(s);
			return (answer == -1) ? 0 : answer;
		}
	}

	public int search_back(String s) {
		Node thisNode = this.back;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c == '?')
				continue;
			if (thisNode.childs.get(c) == null)
				return -1;
			thisNode = thisNode.childs.get(c);
		}
		return thisNode.cnt;
	}

	public int search_front(String s) {
		Node thisNode = this.front;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '?')
				continue;
			if (thisNode.childs.get(c) == null)
				return -1;
			thisNode = thisNode.childs.get(c);
		}
		return thisNode.cnt;
	}
}

class Solution {
	public int[] solution(String[] words, String[] queries) {
		Trie[] tries = new Trie[100001];
		int[] answer = new int[queries.length];
		for (String s : words) {
			int len = s.length();
			if (tries[len] == null)
				tries[len] = new Trie();
			tries[len].insert(s);
		}
		for (int i = 0; i < queries.length; i++) {
			int len = queries[i].length();
			if (tries[len] == null) {
				answer[i] = 0;
			} else {
				answer[i] = tries[len].search(queries[i]);
			}
		}
		return answer;
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?" };
		s.solution(words, queries);
	}
}
