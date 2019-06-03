package MyMud;
import java.util.*;

import MyMud.common.CommonContent;
import MyMud.common.CommonContent.DIRECTION;
import MyMud.common.StaticFunctions;

public class Room {

	private HashMap<CommonContent.DIRECTION, Room> neighbor = new HashMap<CommonContent.DIRECTION, Room>();
	private HashMap<String, Player> playerList = new HashMap<String, Player>();
	
	private String roomDescription;
	private String roomId;
	private String roomName;
	

	/*以下为初始化代码*/
	public Room() {
		// TODO Auto-generated constructor stub
	}
	public Room(String id,String name,String des) {
		roomId=id;
		roomName=name;
		roomDescription=des;
	}
	
	//neighborid为初始化用的中间变量
	private HashMap<CommonContent.DIRECTION, String> neighborid = new HashMap<CommonContent.DIRECTION, String>();
	public void setRoom(CommonContent.DIRECTION d, String id) {
		neighborid.put(d, id);
	}
	//将neighbor的id转换为Room并储存到neighbor中，并设置反向路径
	public void changeIdToRoom() {
		for (CommonContent.DIRECTION d : neighborid.keySet()) {
			String idString=neighborid.get(d);
			Room r=RoomManagement.cityMap.get(idString);
			if (r!=null) {
				neighbor.put(d, r);
				//设置反向的路径
				r.setRoom(StaticFunctions.getRDirection(d), this);
			}
			else {
				System.err.println(roomName+"读取"+idString+"房间信息出错！");
			}
		}
	}
	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
	}
	
	
	/*以下为get set方法*/
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

	
	public Room getRoom(CommonContent.DIRECTION d) {
		if (neighbor.containsKey(d)) {
			return neighbor.get(d);
		}
		return null;
	}

	/*以下为一些被游戏中调用的方法*/
	public void enter(Player player, CommonContent.DIRECTION direction) {
		try {
			Room des=neighbor.get(direction);
			des.addPlayer(player);
			player.setLocation(des.roomId);
			MessageManagement.showToPlayer(player, "你已进入了"+des.roomName+"\n");
			MessageManagement.showToPlayer(player, des.getDescription()+"\n");
			this.removePlayer(player);
			Game.update("players", player.getId(), "location", des.roomId);
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



	public String getRoomLooking() {
		// 房间名
		String roomLooking = roomName + "\t";
		// 房间描述
		// 应该由Client负责解析传输过来的字符（设定字体，每行字数）
		roomLooking += roomDescription + "\t";

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
