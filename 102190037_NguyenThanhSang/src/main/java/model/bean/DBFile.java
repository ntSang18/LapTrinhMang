package model.bean;

public class DBFile {
	
	private int id;
	private String videoname;
	private String videopath;
	private String audioname;
	private String audiopath;
	private int iduser;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVideoname() {
		return videoname;
	}
	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}
	public String getVideopath() {
		return videopath;
	}
	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}
	public String getAudioname() {
		return audioname;
	}
	public void setAudioname(String audioname) {
		this.audioname = audioname;
	}
	public String getAudiopath() {
		return audiopath;
	}
	public void setAudiopath(String audiopath) {
		this.audiopath = audiopath;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
