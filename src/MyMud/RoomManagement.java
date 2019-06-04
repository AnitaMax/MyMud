package MyMud;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import MyMud.common.CommonContent;

public class RoomManagement {
	static Map<String, Room> cityMap = new HashMap<String, Room>();

	public static void creatRoomsFromDatabase(ResultSet rs) {
		try {
			while (rs.next()) {
				Room room = new Room(rs.getString("roomid"),rs.getString("roomname"),rs.getString("roomdescripition"));
				String id=rs.getString("north");
				if (id!=null) {
					room.setRoom(CommonContent.DIRECTION.NORTH, id);
				}
				id=rs.getString("east");
				if (id!=null) {
					room.setRoom(CommonContent.DIRECTION.EAST, id);
				}
				id=rs.getString("northeast");
				if (id!=null) {
					room.setRoom(CommonContent.DIRECTION.NORTHEAST, id);
				}
				id=rs.getString("northwest");
				if (id!=null) {
					room.setRoom(CommonContent.DIRECTION.NORTHWEST, id);
				}
				cityMap.put(room.getRoomId(), room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Room r : cityMap.values()) {
			r.changeIdToRoom();
			Game.initItems(r);
		}
	}
	
}
