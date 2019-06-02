package MyMud;
import java.util.*;

import MyMud.common.CommonContent;
import MyMud.common.CommonContent.DIRECTION;
import MyMud.common.StaticFunctions;

public class Room {

	private HashMap<CommonContent.DIRECTION, Room> neighbor = new HashMap<CommonContent.DIRECTION, Room>();
	private HashMap<String, Player> playerList = new HashMap<String, Player>();
	
	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
		// assert r.getRoom(d) == this;
	}

	public Room getRoom(CommonContent.DIRECTION d) {
		if (neighbor.containsKey(d)) {
			return neighbor.get(d);
		}
		return null;
	}

	private String roomDescription;
	private String roomLooking;
	private String roomId;
	private String roomName;


	public void exist(Player player, CommonContent.DIRECTION direction) {
		

	}

	public void enter(Player player, CommonContent.DIRECTION direction) {
		try {
			neighbor.get(direction).addPlayer(player);
			player.setLocation(neighbor.get(direction).roomId);
			MessageManagement.showToPlayer(player, "你已进入了"+neighbor.get(direction).roomName+"\n");
			MessageManagement.showToPlayer(player, neighbor.get(direction).getDescription()+"\n");
			this.removePlayer(player);
		} catch (NullPointerException e) {
			MessageManagement.showToPlayer(player, "这个方向没有路\n");
		}
		
	}
	public void removePlayer(Player player){
	//用户退出后，清除用户在列表中内容，通知房间内其他玩家
		playerList.remove(player.getId());
		String s=player.getName()+"离开了"+this.getRoomName()+"\n";
		for (Player p : playerList.values()) {
			MessageManagement.showToPlayer(p, s);
		}
		
	}
	public void addPlayer(Player player){
	//用户连线进入，加入列表，通知房间其他玩家
		String s=player.getName()+"进入了"+this.getRoomName()+"\n";
		for (Player p : playerList.values()) {
			MessageManagement.showToPlayer(p, s);
		}
		playerList.put(player.getId(), player);
	}

	public void setDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getDescription() {
		return roomDescription;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void SetRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}


	public String getRoomLooking() {
		// 房间名
		roomLooking = roomName + "\t";
		// 房间描述
		// 应该由Client负责解析传输过来的字符（设定字体，每行字数）
		int roomDescriptionLength = roomDescription.length();
		if (roomDescriptionLength <= CommonContent.CHARS_PER_LINE)
			roomLooking += roomDescription + "\t";
		else {
			int i;
			for (i = 0; i <= roomDescriptionLength
					- CommonContent.CHARS_PER_LINE; i = i
					+ CommonContent.CHARS_PER_LINE) {
				roomLooking += roomDescription.substring(i, i
						+ CommonContent.CHARS_PER_LINE)
						+ "\t";
			}
			roomLooking += roomDescription.substring(i, roomDescriptionLength)
					+ "\t";
		}

		// 房间出口
		roomLooking += getChuKou() + "\t";
		// 房间npc

		// 房间player
		roomLooking += listRoomPlayers();
		// 房间obj
		return roomLooking;
	}
	private String listRoomPlayers(){
		//列出这个房间中的所有玩家
		String temp = "这里有";
		for (Player p : playerList.values()) {
			temp=temp+p.getName()+"\t";
		}
		return temp;
	}
	private String getChuKou() {
		/*描述每个房间的出口
		 * 
		 * 
		 * */
		String temp = "";
		for (DIRECTION d:neighbor.keySet()) {
			if ((!neighbor.containsKey(d))||neighbor.get(d)==null) {
				continue;
			}
			temp=temp+StaticFunctions.getDirection(d)+"面是"+neighbor.get(d).roomName+"\t";
		}
		return temp;
	}
}
