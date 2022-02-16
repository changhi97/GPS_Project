//切弘取人 伸取
class Solution {
	public boolean solution(int[][] key, int[][] block) {
		int len = block.length + (key.length * 2);
		for (int x = 0; x < 4; x++) {
			key = rotate(key);
			for (int y = 0; y < len - key.length; y++) {
				for (int z = 0; z < len - key.length; z++) {
					int[][] temp = copy(block,len,key.length);
					for (int i = 0; i < key.length; i++) {
						for (int j = 0; j < key.length; j++) {
							if (temp[y + i][z + j] == 1 && key[i][j] == 1) {
								break;
							} else if (temp[y + i][z + j] == 0 && key[i][j] == 1) {
								temp[y + i][z + j] = key[i][j];
							}
						}
					}
					if (chk(temp, block.length, key.length))
						return true;
				}
			}
		}
		return false;
	}

	public boolean chk(int[][] arr, int blockLen, int keyLen) {
		for (int i = 0; i < blockLen; i++) {
			for (int j = 0; j < blockLen; j++) {
				if (arr[i + keyLen][j + keyLen] == 0)
					return false;
			}
		}
		return true;
	}

	public int[][] rotate(int[][] arr) {
		int[][] temp = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				temp[i][j] = arr[arr.length - j - 1][i];
			}
		}
		return temp;
	}
	public int[][] copy(int[][] block, int len, int keyLen) {
		int[][] temp = new int[len][len];
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block.length; j++) {
				temp[i + keyLen][j + keyLen] = block[i][j];
			}
		}
		return temp;
	}
}

public class Main {
	public static void main(String args[]) {
		Solution s = new Solution();
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] block = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		s.solution(key, block);
	}
}
