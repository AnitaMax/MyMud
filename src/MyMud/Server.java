package MyMud;
import java.awt.List;
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
					curPlayer.quit();
					MessageManagement.removePlayerChannels(curPlayer.getId());
					curPlayer=null;
					socket.close();
					writer.close();
					reader.close();
				} catch (IOException e) {
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
				//�����¼�¼�
				if (((inputs[0].equals("u") && inputs[2].equals("p"))||(inputs[0].equals("userid") && inputs[2].equals("password") ))&& inputs.length >= 4) {
					login(inputs[1], inputs[3]);
				}
				else if(inputs[0].equals("login")){
					String id,password;
					if (inputs.length>=3) {
						id=inputs[1];
						password=inputs[2];
					}
					else {
						writer.write("��¼��\t������id\n");
						writer.flush();
						id=reader.readLine();
						writer.write("����������\n");
						writer.flush();
						password=reader.readLine();
					}
					login(id, password);
				}
				//����ע���¼�
				else if (inputs[0].equals("signin")) {
					String id,password,username,extra="";
					if (inputs.length>=4) {
						id=inputs[1];
						password=inputs[2];
						username=inputs[3];
						if (inputs.length>=5) {
							extra=inputs[4];
						}
					}
					else {
						writer.write("ע�᣺\t������id\n");
						writer.flush();
						id=reader.readLine();
						writer.write("����������\n");
						writer.flush();
						password=reader.readLine();
						writer.write("�������û���\n");
						writer.flush();
						username=reader.readLine();

					}
					int result=game.signin(id, password, username, extra);
					if (result==0) {
						writer.write("ע����������ԣ�\n");
						writer.flush();
					}
					else if (result==1) {
						writer.write("ע��ɹ���\n");
						writer.flush();
						login(id, password);
					}
					else if (result==-1) {
						writer.write("id�Ѵ��ڣ�\n");
						writer.flush();
					}
					else {
						writer.write("δ֪����\n");
						writer.flush();
					}
	
				}
				//�����˳�
				else if(cmd.equals("quit")||cmd.equals("q")){
					writer.write("you have quited\n");
					quit = true;
				}
				//�����û�����
				else {
					UserInput.dealInput(curPlayer, cmd);
				}
				writer.flush();
			}
		}
		private void login(String id,String password) throws IOException {
			if (curPlayer != null) {
				writer.write("���ѵ�¼,�����ظ���¼��\n");
			} else {
				try {
					curPlayer = game.login(id, password);
					if (MessageManagement.playerChannels.containsKey(curPlayer.getId())) {
						writer.write("���û��ѵ�½��\n");
						curPlayer=null;
						return;
					}
					writer.write("���ѳɹ���¼\t");
					MessageManagement.addPlayerChannels(curPlayer.getId(), writer);
					RoomManagement.cityMap.get(curPlayer.getLocation()).addPlayer(curPlayer);
					writer.write(RoomManagement.cityMap.get(curPlayer.getLocation()).getRoomName()+"\t");
					writer.write(RoomManagement.cityMap.get(curPlayer.getLocation()).getDescription()+"\n");
				} catch (NoSuchPlayerException e) {
					writer.write("�˺Ż��������\n");
				}
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
