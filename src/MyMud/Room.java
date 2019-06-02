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
			MessageManagement.showToPlayer(player, "���ѽ�����"+neighbor.get(direction).roomName+"\n");
			MessageManagement.showToPlayer(player, neighbor.get(direction).getDescription()+"\n");
			this.removePlayer(player);
		} catch (NullPointerException e) {
			MessageManagement.showToPlayer(player, "�������û��·\n");
		}
		
	}
	public void removePlayer(Player player){
	//�û��˳�������û����б������ݣ�֪ͨ�������������
		playerList.remove(player.getId());
		String s=player.getName()+"�뿪��"+this.getRoomName()+"\n";
		for (Player p : playerList.values()) {
			MessageManagement.showToPlayer(p, s);
		}
		
	}
	public void addPlayer(Player player){
	//�û����߽��룬�����б�֪ͨ�����������
		String s=player.getName()+"������"+this.getRoomName()+"\n";
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
		// ������
		roomLooking = roomName + "\t";
		// ��������
		// Ӧ����Client�����������������ַ����趨���壬ÿ��������
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

		// �������
		roomLooking += getChuKou() + "\t";
		// ����npc

		// ����player
		roomLooking += listRoomPlayers();
		// ����obj
		return roomLooking;
	}
	private String listRoomPlayers(){
		//�г���������е��������
		String temp = "������";
		for (Player p : playerList.values()) {
			temp=temp+p.getName()+"\t";
		}
		return temp;
	}
	private String getChuKou() {
		/*����ÿ������ĳ���
		 * 
		 * 
		 * */
		String temp = "";
		for (DIRECTION d:neighbor.keySet()) {
			if ((!neighbor.containsKey(d))||neighbor.get(d)==null) {
				continue;
			}
			temp=temp+StaticFunctions.getDirection(d)+"����"+neighbor.get(d).roomName+"\t";
		}
		return temp;
	}
}
