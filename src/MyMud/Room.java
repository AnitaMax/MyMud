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
	

	/*����Ϊ��ʼ������*/
	public Room() {
		// TODO Auto-generated constructor stub
	}
	public Room(String id,String name,String des) {
		roomId=id;
		roomName=name;
		roomDescription=des;
	}
	
	//neighboridΪ��ʼ���õ��м����
	private HashMap<CommonContent.DIRECTION, String> neighborid = new HashMap<CommonContent.DIRECTION, String>();
	public void setRoom(CommonContent.DIRECTION d, String id) {
		neighborid.put(d, id);
	}
	//��neighbor��idת��ΪRoom�����浽neighbor�У������÷���·��
	public void changeIdToRoom() {
		for (CommonContent.DIRECTION d : neighborid.keySet()) {
			String idString=neighborid.get(d);
			Room r=RoomManagement.cityMap.get(idString);
			if (r!=null) {
				neighbor.put(d, r);
				//���÷����·��
				r.setRoom(StaticFunctions.getRDirection(d), this);
			}
			else {
				System.err.println(roomName+"��ȡ"+idString+"������Ϣ����");
			}
		}
	}
	void setRoom(CommonContent.DIRECTION d, Room r) {
		neighbor.put(d, r);
	}
	
	
	/*����Ϊget set����*/
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

	/*����ΪһЩ����Ϸ�е��õķ���*/
	public void enter(Player player, CommonContent.DIRECTION direction) {
		try {
			Room des=neighbor.get(direction);
			des.addPlayer(player);
			player.setLocation(des.roomId);
			MessageManagement.showToPlayer(player, "���ѽ�����"+des.roomName+"\n");
			MessageManagement.showToPlayer(player, des.getDescription()+"\n");
			this.removePlayer(player);
			Game.update("players", player.getId(), "location", des.roomId);
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



	public String getRoomLooking() {
		// ������
		String roomLooking = roomName + "\t";
		// ��������
		// Ӧ����Client�����������������ַ����趨���壬ÿ��������
		roomLooking += roomDescription + "\t";

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
