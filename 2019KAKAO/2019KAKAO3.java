//길 찾기 게임
import java.util.Arrays;

class Solution {
	int[][] answer;
	int cnt = 0;

	class Node implements Comparable<Node> {
		int x, y, idx;
		Node right, left;

		public Node(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
			this.right = null;
			this.left = null;
		}

		public int compareTo(Node node) {
			if (this.y == node.y) {
				return this.x - node.x;
			} else {
				return node.y - this.y;
			}
		}
	}

	class Tree {
		Node root;

		public Tree(Node root) {
			this.root = root;
		}

		public void insert(Node parent, Node node) {
			if (parent.x > node.x) {
				if (parent.left == null)
					parent.left = node;
				else
					insert(parent.left, node);
			} else {
				if (parent.right == null)
					parent.right = node;
				else
					insert(parent.right, node);
			}
		}

		public void preorder(Node node) { // VLR
			if (node != null) {
				answer[0][cnt++] = node.idx;
				preorder(node.left);
				preorder(node.right);
			}
		}

		public void postorder(Node node) { // LRV
			if (node != null) {
				postorder(node.left);
				postorder(node.right);
				answer[1][cnt++] = node.idx;
			}
		}
	}

	public int[][] solution(int[][] nodeinfo) {
		Node[] nodes = new Node[nodeinfo.length];
		for (int i = 0; i < nodeinfo.length; i++)
			nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
		Arrays.sort(nodes);
		Tree tree = new Tree(nodes[0]);
		for (int i = 1; i < nodes.length; i++)
			tree.insert(tree.root, nodes[i]);
		answer = new int[2][nodes.length];
		tree.preorder(tree.root);
		cnt = 0;
		tree.postorder(tree.root);
		return answer;
	}
}