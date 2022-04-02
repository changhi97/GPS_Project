//호텔 방 배정
import java.util.HashMap;

class Solution {
	HashMap<Long,Long> map = new HashMap<>();
	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		for(int i =0; i<room_number.length; i++){
			answer[i] = checkIn(room_number[i]);
		}
		return answer;
	}
	public long checkIn(long room) {
		if(!map.containsKey(room)) {
			map.put(room, room+1);
			return room;
		}else {
			long nextRoom = map.get(room);
			long emptyRoom = checkIn(nextRoom);
			map.put(room, emptyRoom);
			return emptyRoom;
		}
	}
}