//자동완성
import java.util.HashMap;

class Node{
	HashMap<Character, Node> childNode;
	int cnt;
	public Node() {
		this.childNode = new HashMap<>();
		this.cnt = 0;
	}
}

class Trie{
	Node root;
	public Trie() {
		root = new Node();
	}
	
	public void insert(String s) {
		Node thisNode = this.root;
		for(char c : s.toCharArray()) {
			thisNode = thisNode.childNode.computeIfAbsent(c, key->new Node());
			thisNode.cnt++;
		}
	}
	
	public int wordCnt(String s) {
		int result = 0;
		Node thisNode = this.root;
		for(char c : s.toCharArray()) {
			result++;
			if(thisNode.childNode.get(c).cnt == 1)
				break;
			thisNode = thisNode.childNode.get(c);
		}
		return result;
	}
}
class Solution {
	public int solution(String[] words) {
		Trie tries = new Trie();
		int answer=0;
		for(String s : words) {
			tries.insert(s);
		}
		for(String s : words) {
			answer += tries.wordCnt(s);
		}
		System.out.println(answer);
		return answer;
	}
}
