package MyMud;

public class Item {

	private String id;
	private String name;
	private String owner,ownertype;
	private String descripition,effect;
	
	public Item(String id,String name,String descripition,String owner,String ownertype) {
		this.id=id;
		this.name=name;
		this.descripition=descripition;
		this.owner=owner;
		this.ownertype=ownertype;
	}
	public void setEffect(String e) {
		this.effect=e;
	}
	public void putItem() {
		//
	}
	public String getId() {
		return this.id;
	}
	public String getNmae() {
		return this.name;
	}
	public String getDescripition() {
		return this.descripition;
	}
	public String getOwner() {
		return this.owner;
	}
	public String getOwnertype() {
		return this.ownertype;
	}
}
