package controller;
import java.io.*;
import java.util.*;

import model.Player;
public class MessageManagement {
	static public HashMap<String,BufferedWriter> playerChannels = new HashMap<String,BufferedWriter>();
	
	public static void showToPlayer(Player player, String message){
		String curId=player.getId();
		BufferedWriter br=playerChannels.get(curId);
		try {
			br.write(message);
			br.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void addPlayerChannels(String playerId,BufferedWriter bw){
		playerChannels.put(playerId, bw);
	}
	public static void removePlayerChannels(String playerId){
		playerChannels.remove(playerId);
	}
}
