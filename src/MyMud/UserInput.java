package MyMud;
import MyMud.common.CommonContent;
public class UserInput {
	public static void dealInput(Player player, String inputMessage) {
		/*
		 * 可以处理的命令 l,look e,east,w,west,n,north,s,south,
		 */
		if (player==null) {
			return;
		}
		String[] inputs = inputMessage.split(" ");
		if (inputs[0].equals("l") || inputs[0].equals("look")) {
			if (inputs.length == 1) {
				player.look("");
				return;
			}
			player.look(inputs[1]);
		}
		if (inputs[0].equals("quit")) {
			//RoomManagement.cityMap.get(player.getLocation()).removePlayer(player);
			//MessageManagement.removePlayerChannels(player.getId());
			return;
		}
		if (inputs[0].equals("e") || inputs[0].equals("east")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.EAST);
			return;
		}
		if (inputs[0].equals("w") || inputs[0].equals("west")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.WEST);
			return;
		}
		if (inputs[0].equals("s") || inputs[0].equals("south")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.SOUTH);
			return;
		}
		if (inputs[0].equals("n") || inputs[0].equals("north")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.NORTH);
			return;
		}
		if (inputs[0].equals("ne") || inputs[0].equals("northeast")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.NORTHEAST);
			return;
		}
		if (inputs[0].equals("nw") || inputs[0].equals("northwest")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.NORTHWEST);
			return;
		}
		if (inputs[0].equals("se") || inputs[0].equals("southeast")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.SOUTHEAST);
			return;
		}
		if (inputs[0].equals("sw") || inputs[0].equals("southwest")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.SOUTHWEST);
			return;
		}
		if (inputs[0].equals("u") || inputs[0].equals("up")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.UP);
			return;
		}
		if (inputs[0].equals("d") || inputs[0].equals("down")) {
			RoomManagement.cityMap.get(player.getLocation()).enter(player, CommonContent.DIRECTION.DOWN);
			return;
		}
		
		MessageManagement.showToPlayer(player, "什么？\n");
	}
}
