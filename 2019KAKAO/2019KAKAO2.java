//무지의 먹방 라이브
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Food {
	int time, idx;
	public Food(int time, int idx) {
		this.time = time;
		this.idx = idx;
	}
}

class Solution {
	public int solution(int[] food_times, long k) {
		ArrayList<Food> foods = new ArrayList<>();
		for (int i = 0; i < food_times.length; i++)
			foods.add(new Food(food_times[i], i + 1));
		foods.sort(SortTime);
		long prev = 0;
		long len = food_times.length;
		for (Food f : foods) {
			long eat = (f.time - prev) * len;
			if (k >= eat) {
				k -= eat;
				prev = f.time;
				len--;
			} else {
				List<Food> temp = foods.subList(food_times.length - (int) len, food_times.length);
				temp.sort(SortIdx);
				return temp.get((int) (k % len)).idx;
			}
		}
		return -1;
	}

	Comparator<Food> SortTime = new Comparator<Food>() {
		public int compare(Food a, Food b) {
			return a.time - b.time;
		}
	};

	Comparator<Food> SortIdx = new Comparator<Food>() {
		public int compare(Food a, Food b) {
			return a.idx - b.idx;
		}
	};
}