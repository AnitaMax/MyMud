package MyMud;

import java.sql.*;

import MyMud.exceptions.NoSuchPlayerException;
//���ڣ������ݿ⣩��ʼ����Ϸ�ڷ��� ��� npc 
public class Game {
	RoomManagement rm;
	DateBase db;

	public Game() {
		rm = new RoomManagement();
		rm.creatRooms();
		db=new DateBase();
		db.link();
	}

	public Player login(String id, String password) throws NoSuchPlayerException {
		String sqlString = "SELECT id,username,password FROM users WHERE id=\"" + id
				+ "\" and password=\"" + password + "\";";
		ResultSet rs = db.find(sqlString);
		try {
			if (rs.next()) {
				String playersqlString = "SELECT * FROM players WHERE id=\""  +id + "\"";
				ResultSet rs2 = db.find(playersqlString);
				if (rs2.next()) {
					return new Player(rs2.getInt("experience"), rs2.getInt("con"), rs2.getInt("dex"), rs2.getInt("str"), rs2.getInt("wis"), rs2.getInt("hp"), rs2.getInt("max_hp"), rs2.getInt("nl"), rs2.getInt("max_nl"), rs2.getInt("jl"), rs2.getInt("max_jl"), rs2.getString("id"),  rs2.getString("username"),  rs2.getString("party"),  rs2.getString("location"));
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
	}
}