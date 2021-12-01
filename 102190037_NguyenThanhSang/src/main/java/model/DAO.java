package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.DBFile;
import model.bean.User;

public class DAO {
	
	private static DAO _Instance;
	private Connection conn;
	
	public static DAO Instance() {
		if(_Instance == null) _Instance = new DAO();
		return _Instance;
	}
	
	private DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/file";
			String user = "root";
			String pass = "";
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection Success!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> GetListUser(){
		String sql = "SELECT * FROM `user`";
		List<User> l = new ArrayList<User>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
				l.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public User AddUser(User user) {
		String sql = "INSERT INTO `user`(`username`, `password`) VALUES (?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			User u = new User();
			u.setId(id);
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DBFile> GetListFile(){
		String sql = "SELECT * FROM `dbfile`";
		List<DBFile> l = new ArrayList<DBFile>();	
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DBFile file = new DBFile();
				file.setId(rs.getInt("id"));
				file.setVideoname(rs.getString("videoname"));
				file.setVideopath(rs.getString("videopath"));
				file.setAudioname(rs.getString("audioname"));
				file.setAudiopath(rs.getString("audiopath"));
				file.setIduser(rs.getInt("iduser"));
				file.setStatus(rs.getInt("status"));
				
				l.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public DBFile AddFileDB(DBFile file) {
		String sql = "INSERT INTO `dbfile`(`videoname`, `videopath`, `audioname`, `audiopath`, `iduser`, `status`) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, file.getVideoname());
			ps.setString(2, file.getVideopath());
			ps.setString(3, file.getAudioname());
			ps.setString(4, file.getAudiopath());
			ps.setInt(5, file.getIduser());
			ps.setBoolean(6, false);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			
			DBFile f = new DBFile();
			f.setId(id);
			f.setVideoname(file.getVideoname());
			f.setVideopath(file.getVideopath());
			f.setAudioname(file.getAudioname());
			f.setAudiopath(file.getAudiopath());
			f.setIduser(file.getIduser());
			f.setStatus(0);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean UpdateFinishFileDB(DBFile file) {
		String sql = "UPDATE `dbfile` SET `status`=? WHERE `id`=?";
		try {
			PreparedStatement ps  = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, file.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean UpdateErrorFileDB(DBFile file) {
		String sql = "UPDATE `dbfile` SET `status`=? WHERE `id`=?";
		try {
			PreparedStatement ps  = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			ps.setInt(2, file.getId());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean DeleteFileDB(int id) {
		String sql = "DELETE FROM `dbfile` WHERE id =?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
