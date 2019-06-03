package MyMud;

import java.sql.*;

import MyMud.exceptions.NoSuchPlayerException;

//用于（用数据库）初始化游戏内房间 玩家 npc 
public class Game {
	static RoomManagement rm;
	static DateBase db;

	public Game() {
		//init database
		db = new DateBase();
		db.link();
		//init rooms
		rm = new RoomManagement();
		//RoomManagement.creatRooms();
		String sqlString = "SELECT * FROM rooms;";
		ResultSet rs = db.find(sqlString);
		RoomManagement.creatRoomsFromDatabase(rs);
	}
	
	public static void update(String table,String id,String col,String data) {
		String sqlString = "UPDATE "+table+" SET "+col+" ='"+data+"' WHERE id='" + id + "';";
		int n=db.Update(sqlString);
		if (n!=1) {
			System.err.println("更新数据库错误！"+table+" "+id+" "+col+" "+data+"\n");
		}
	}

	public Player login(String id, String password) throws NoSuchPlayerException {
		String sqlString = "SELECT id,username,password FROM users WHERE id=\"" + id + "\";";
		ResultSet rs = db.find(sqlString);
		try {
			if (rs.next()&&rs.getString("password").equals(password)) {
				//查找player信息
				String playersqlString = "SELECT * FROM players WHERE id=\"" + id + "\"";
				ResultSet rs2 = db.find(playersqlString);
				if (rs2.next()) {
					return new Player(rs2.getInt("experience"), rs2.getInt("con"), rs2.getInt("dex"), rs2.getInt("str"),
							rs2.getInt("wis"), rs2.getInt("hp"), rs2.getInt("max_hp"), rs2.getInt("nl"),
							rs2.getInt("max_nl"), rs2.getInt("jl"), rs2.getInt("max_jl"), rs2.getString("id"),
							rs2.getString("username"), rs2.getString("party"), rs2.getString("location"));
				}
				throw new NoSuchPlayerException();
			} else {
				throw new NoSuchPlayerException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int signin(String id, String password, String username, String cmd) {
		String sqlString = "SELECT id,username,password FROM users WHERE id=\"" + id + "\";";
		ResultSet rs = db.find(sqlString);
		try {
			if (rs.next()) {
				return -1;//id已存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 插入user表
		String sqlString1 = "INSERT INTO users VALUES ('" + id + "','" + username + "','" + password + "')";
		int line = db.Update(sqlString1);
		if (line != 1) {
			System.out.println("注册出错！01");
			return 0;
		}
		// 插入player表
		// cmd表示创建人物的不同要求，比如说力量高而体力低
		if (cmd.equals("")) {
			sqlString1 = "INSERT INTO players VALUES (100,100,100,100,100,100,100,100,100,100,100,'" + id + "','"
					+ username + "','无','yangzhou_guangchang')";

		} else {
			System.out.println("出错了");
			return 0;
			//todo
		}
		line = db.Update(sqlString1);
		if (line != 1) {
			System.out.println("注册出错！01");
			return 0;
		}


		return 1;
	}

	class DateBase {
		Connection conn;
		Statement state;
		boolean connected = false;

		public void link() {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/game?characterEncoding=utf8&serverTimezone=UTC", "root",
						"12345687");
				state = conn.createStatement();
				connected = true;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void test_print() {
			if (!connected) {
				System.out.println("not connected!!");
				return;
			}
			try {
				ResultSet rs = state.executeQuery("SELECT * FROM users ");
				System.out.println("userid\t\t username\t\t password");
				while (rs.next()) {
					StringBuilder cur = new StringBuilder(rs.getString(1));
					cur.append("\t\t ");
					cur.append(rs.getString(2));
					cur.append("\t\t ");
					cur.append(rs.getString(3));
					System.out.println(cur);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public ResultSet find(String sqlString) {
			if (!connected) {
				System.out.println("not connected!!");
				return null;
			}
			try {
				ResultSet rs = state.executeQuery(sqlString);
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public int Update(String sqlString) {
			if (!connected) {
				System.out.println("not connected!!");
				return 0;
			}
			try {
				int line = state.executeUpdate(sqlString);
				return line;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

	}
}