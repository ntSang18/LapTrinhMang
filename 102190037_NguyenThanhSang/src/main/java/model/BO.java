package model;



import java.util.ArrayList;
import java.util.List;

import model.bean.DBFile;
import model.bean.User;

public class BO {
	private static BO _Instance;
	public static BO Instance() {
		if(_Instance == null) _Instance = new BO();
		return _Instance;
	}
	
	private BO() {}
	
	public User Login(User user) {
		for (User u : DAO.Instance().GetListUser()) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				return u;
			}
		}
		return null;
	}
	
	public User Register(User user) {
		return DAO.Instance().AddUser(user);
	}
	
	public DBFile AddFileDB(DBFile file) {
		return DAO.Instance().AddFileDB(file);
	}
	
	public boolean UpdateFinishFileDB(DBFile file) {
		return DAO.Instance().UpdateFinishFileDB(file);
	}
	
	public boolean UpdateErrorFileDB(DBFile file) {
		return DAO.Instance().UpdateErrorFileDB(file);
	}
	
	public List<DBFile> GetListFile(){
		return DAO.Instance().GetListFile();
	}
	
	public DBFile GetFileByIdFile(int idfile) {
		List<DBFile> l = GetListFile();
		for (DBFile f : l) {
			if(f.getId() == idfile) {
				return f;
			}
		}
		return null;
	}
	
	public List<DBFile> GetListFileByIduser(int iduser){
		List<DBFile> l = GetListFile();
		List<DBFile> result = new ArrayList<DBFile>();
		for (DBFile f : l) {
			if(f.getIduser() == iduser) {
				result.add(f);
			}
		}
		return result;
	}
	
	public boolean DeteleFileDB(int id) {
		return DAO.Instance().DeleteFileDB(id);
	}
}
