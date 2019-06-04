package MyMud;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import MyMud.Game.DateBase;
import MyMud.common.CommonContent;

public class Player {

	private int experience;// ����
	private int con;// �w��
	private int dex;// ����
	private int str;// ����
	private int wis;// ����
	private int hp, max_hp;// Ѫ��
	private int nl, max_nl;// ����
	private int jl, max_jl;// ŭ��
	private String id;// id
	private String username;// �û���
	private String party;// ����
	private String location;// �����ص�
	private Map<String, Item> itemlist = new HashMap<String, Item>();

	public Player() {
		this.experience = 100;
		this.con = 100;
		this.dex = 100;
		this.str = 100;
		this.wis = 100;
		this.hp = 100;
		this.max_hp = 100;
		this.nl = 100;
		this.max_nl = 100;
		this.jl = 100;
		this.max_jl = 100;
		this.id = "default";
		this.username = "default";
		this.party = "��";
		this.location = "yangzhou_guangchang";

	}

	public Player(int experience, int con, int dex, int str, int wis, int hp, int max_hp, int nl, int max_nl, int jl,
			int max_jl, String id, String username, String party, String location) {
		this.experience = experience;
		this.con = con;
		this.dex = dex;
		this.str = str;
		this.wis = wis;
		this.hp = hp;
		this.max_hp = max_hp;
		this.nl = nl;
		this.max_nl = max_nl;
		this.jl = jl;
		this.max_jl = jl;
		this.id = id;
		this.username = username;
		this.party = party;
		this.location = location;
		Game.initItems(this);
	}

	public void putItem(Item i) {
		itemlist.put(i.getId(), i);
		// to add
	}

	public void getIterm(Item i) {
		Game.update("items", i.getId(), "ownertype", "player");
		Game.update("items", i.getId(), "owner", this.id);
		putItem(i);
		MessageManagement.showToPlayer(this, "----�����Ʒ��"+i.getName()+"-----\n");
	}
	public boolean dropIterm(String itemid) {
		if (!itemlist.containsKey(itemid)) {
			return false;
		}
		Item i=this.itemlist.get(itemid);
		RoomManagement.cityMap.get(this.location).itemBeDropped(i);
		itemlist.remove(itemid);
		MessageManagement.showToPlayer(this, "----������Ʒ��"+i.getName()+"�ɹ�-----\n");
		return true;
		
	}
	public void move(CommonContent.DIRECTION direction) {
		Room nextRoom = RoomManagement.cityMap.get(location).getRoom(direction);

	}

	public void look(String something) {
		if (something.equals(""))
			MessageManagement.showToPlayer(this, RoomManagement.cityMap.get(location).getRoomLooking() + "\n");// �鿴��ǰ����;
		else
			;// �鿴������Ʒ
	}

	public void quit() {
		// ���߷����˳��ˣ��ͷ���Դ
		RoomManagement.cityMap.get(location).removePlayer(this);

		// save���������
	}

	public String getState() {
		String temp="@"+username+"\t";
		temp+="   ����:  "+party+"\t";	
		temp+="   ����:  "+experience+"\t";
		temp+="   �w��:  "+con+"\t";
		temp+="   ����:  "+dex+"\t";
		temp+="   ����:  "+str+"\t";
		temp+="   ����:  "+wis+"\t";	
		temp+="   Ѫ��:  "+hp+"\t";	
		temp+="   ���Ѫ��:  "+max_hp+"\t";	
		temp+="   ����:  "+nl+"\t";	
		temp+="   �����:  "+max_nl+"\t";	
		temp+="   ŭ��:  "+jl+"\t";	
		temp+="   ���ŭ��:  "+max_jl+"\t";		
		temp+="   �����ص�:  "+RoomManagement.cityMap.get(location).getRoomName()+"\t";
		if (!itemlist.isEmpty()) {
			temp+= "  ������Ʒ:  ";
			for (Item i:itemlist.values()) {
				temp=temp+i.getName()+"("+i.getId()+")  ";
			}
		}

		temp+="\n";
		return temp;
	}

	public Room getRoom() {
		return RoomManagement.cityMap.get(location);
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return this.location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.username;
	}

	public void setName(String username) {
		this.username = username;
	}
}
