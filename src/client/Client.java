package client;
import java.io.*;
import javax.swing.*;

import MyMud.Game;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Client extends JFrame {
	private JTextArea screen;
	private JTextField input;
	private JButton connection;

	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;

	private String ipaddress = "127.0.0.1";
	private int port = 1888;
	private boolean connected = false;
	
	class MonitorThread extends Thread {
		public MonitorThread(BufferedReader br) {
			this.br=br;
		}

		BufferedReader br;

		@Override
		public void run() {
			String str;
			try {
				while (true) {
					str = in.readLine();
					String lines[]=str.split("\t");
					for (String s : lines) {
						setText(screen, s);
					}
				}
			} catch (NullPointerException e) {
				try {
					in.close();
					out.close();
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setText(screen, "您已掉线，请重新登录");
			}
			catch (SocketException e) {
				setText(screen, "网络出现故障，请重新登录");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}

	public Client() {
		super("Mud Client");
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		container.add(BorderLayout.CENTER, leftPanel);
		container.add(BorderLayout.EAST, rightPanel);
		leftPanel.setLayout(new BorderLayout());
		screen = new JTextArea();
		screen.setEditable(false);
		screen.setAutoscrolls(true);
		JScrollPane jsp = new  JScrollPane(screen);
		input = new JTextField();
		connection = new JButton("conncet");
		leftPanel.add(BorderLayout.CENTER, jsp);
		leftPanel.add(BorderLayout.SOUTH, input);
		// rightPanel.setLayout(new FlowLayout());
		rightPanel.add(connection);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				char c=arg0.getKeyChar();
				if (c=='\n') {
					try {
						String str=input.getText()+"\n";
						setText(screen, str);
						out.write(str);
						out.flush();
					} catch(NullPointerException e) {
						setText(screen,"尚未连接到服务器，请先连接");
					}
					catch(SocketException e) {
						setText(screen,"网络出现故障，请尝试重新连接");
					}
					catch (IOException e) {
						setText(screen,"尚未连接到服务器，请先连接");
					}
					finally {
						input.setText("");
					}
				}
			}
		});
		connection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					socket = new Socket(ipaddress,port);
					connected = true;
					setText(screen, "登陆成功");
					out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					MonitorThread mt=new MonitorThread(in);
					mt.start();
				} catch (ConnectException e) {					
					screen.setText(screen.getText() + "链接服务器失败！请重试\n");
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		});
	}

	public void setDefaultCloseOperation(int arg0) {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (connected) {
			try {
				connected = false;
				socket.close();
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.exit(1);
	}

	public void setText(JTextArea screen, String Message) {
		String[] temp = Message.split("\n");
		for (int i = 0; i < temp.length; i++) {
			screen.setText(screen.getText() + temp[i] + "\n");
			// System.out.print(temp[i]+"\n");
		}
		screen.setCaretPosition(screen.getDocument().getLength());
	}

	public static void main(String[] args) {
		new Client();
	}
}
