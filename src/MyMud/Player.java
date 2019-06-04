package MyMud;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import MyMud.Game.DateBase;
import MyMud.common.CommonContent;

public class Player {

	private int experience;// 经验
	private int con;// 體力
	private int dex;// 敏捷
	private int str;// 力量
	private int wis;// 精神
	private int hp, max_hp;// 血量
	private int nl, max_nl;// 法力
	private int jl, max_jl;// 怒气
	private String id;// id
	private String username;// 用户名
	private String party;// 党派
	private String location;// 所处地点
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
		this.party = "无";
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
		MessageManagement.showToPlayer(this, "----获得物品："+i.getName()+"-----\n");
	}
	public boolean dropIterm(String itemid) {
		if (!itemlist.containsKey(itemid)) {
			return false;
		}
		Item i=this.itemlist.get(itemid);
		RoomManagement.cityMap.get(this.location).itemBeDropped(i);
		itemlist.remove(itemid);
		MessageManagement.showToPlayer(this, "----丢弃物品："+i.getName()+"成功-----\n");
		return true;
		
	}
	public void move(CommonContent.DIRECTION direction) {
		Room nextRoom = RoomManagement.cityMap.get(location).getRoom(direction);

	}

	public void look(String something) {
		if (something.equals(""))
			MessageManagement.showToPlayer(this, RoomManagement.cityMap.get(location).getRoomLooking() + "\n");// 查看当前房间;
		else
			;// 查看其它物品
	}

	public void quit() {
		// 告诉房间退出了，释放资源
		RoomManagement.cityMap.get(location).removePlayer(this);

		// save添加在这里
	}

	public String getState() {
		String temp="@"+username+"\t";
		temp+="   党派:  "+party+"\t";	
		temp+="   经验:  "+experience+"\t";
		temp+="   體力:  "+con+"\t";
		temp+="   敏捷:  "+dex+"\t";
		temp+="   力量:  "+str+"\t";
		temp+="   精神:  "+wis+"\t";	
		temp+="   血量:  "+hp+"\t";	
		temp+="   最大血量:  "+max_hp+"\t";	
		temp+="   法力:  "+nl+"\t";	
		temp+="   最大法力:  "+max_nl+"\t";	
		temp+="   怒气:  "+jl+"\t";	
		temp+="   最大怒气:  "+max_jl+"\t";		
		temp+="   所处地点:  "+RoomManagement.cityMap.get(location).getRoomName()+"\t";
		if (!itemlist.isEmpty()) {
			temp+= "  身上物品:  ";
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
