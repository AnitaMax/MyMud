package MyMud;
import java.io.*;
import java.net.*;
import java.security.AlgorithmConstraints;

import javax.swing.text.DefaultStyledDocument;

import org.omg.CosNaming.NamingContextExtOperations;

import MyMud.exceptions.NoSuchPlayerException;

public class Server {

	static class ServerThread extends Thread {
		public ServerThread(Socket socket) {
			this.socket=socket;
		}

		Socket socket;
		BufferedWriter writer;
		BufferedReader reader;
		Player curPlayer;
		@Override
		public void run() {
			try {
				System.out.println("һ���ͻ���¼");
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				writer.write("��ӭ��¼�������磡\n");
				writer.flush();
				
				execute();
			} catch (SocketException e) {
				System.out.println("һ���ͻ�����");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					socket.close();
					writer.close();
					reader.close();
			 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		private void execute() throws Exception {
			boolean quit=false;
			String cmd;
			while(!quit) {
				quit=false;
				cmd=reader.readLine();
				String[] inputs = cmd.split(" ");
				if (inputs[0].equals("u")&&inputs[2].equals("p")&&inputs.length>=4) {
					try {
						curPlayer=game.login(inputs[1], inputs[3]);
						writer.write("���ѳɹ���¼\n");
						MessageManagement.addPlayerChannels(curPlayer.getId(), writer);
						RoomManagement.cityMap.get(curPlayer.getLocation()).addPlayer(curPlayer);
					} catch (NoSuchPlayerException e) {
						writer.write("�˺Ż��������\n");
					}
				}
				else {
					UserInput.dealInput(curPlayer, cmd);
				}
				if(cmd.equals("quit")){
					writer.write("you have quited\n");
					quit = true;
				}
				if(cmd.equals("q")){
					writer.write("\n");
					quit = true;
				}
				writer.flush();
			}
		}



	}

	public static int PORT_NUM = 1888;
	public static Game game;
	static public void main(String[] args) throws IOException {
		game=new Game();
		ServerSocket serverSocket = new ServerSocket(PORT_NUM);
		for (;;) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();

		}
	}//end main

}//end Server class
