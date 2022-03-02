//무지의 먹방 라이브
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class food {
	int time, idx;
	public food(int time, int idx) {
		this.time = time;
		this.idx = idx;
	}
}

class Solution {
	public int solution(int[] food_times, long k) {
		ArrayList<food> foods = new ArrayList<>();
		for (int i = 0; i < food_times.length; i++)
			foods.add(new food(food_times[i], i+1));
		Collections.sort(foods, new Comparator<food>() {
			@Override
			public int compare(food o1, food o2) {
				// TODO Auto-generated method stub
				return o1.time - o2.time;
			}
		});
		long prev = 0;
		long len = food_times.length;
		for (food f : foods) {
			long eat = (f.time - prev) * len;
			if (k >= eat) {
				len--;
				prev = f.time;
				k-=eat;
			} else {
				List<food> temp = foods.subList(food_times.length-(int)len, food_times.length); 
				Collections.sort(temp, new Comparator<food>() {
					@Override
					public int compare(food o1, food o2) {
						// TODO Auto-generated method stub
						return o1.idx-o2.idx;
					}
				});
				return temp.get((int) (k % len)).idx;
			}
		}
		return -1;
	}
}