package controler;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import model.BO;
import model.bean.DBFile;

public class XuLy extends Thread{
	
	//Part part;
	InputStream in;
	String videopath;
	String videoname;
	String audiopath;
	String audioname;
	DBFile file;
	
	public XuLy(InputStream in, String videopath, String videoname, String audiopath, String audioname, DBFile file) {
		this.in = in;
		this.videopath = videopath;
		this.videoname = videoname;
		this.audiopath = audiopath;
		this.audioname = audioname;
		this.file = file;
	}
	
	public void Convert(File Video, File Audio) {
		System.out.println("Convert is running...");
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(128000);
		audio.setChannels(2);
		audio.setSamplingRate(44100);
		
		EncodingAttributes en = new EncodingAttributes();
		en.setFormat("mp3");
		en.setAudioAttributes(audio);
		
		Encoder encoder = new Encoder();
		try {
			encoder.encode(Video, Audio, en);
			BO.Instance().UpdateFinishFileDB(file);
			System.out.println("Convert finish!");
		} catch (Exception e) {
			BO.Instance().UpdateErrorFileDB(file);
			e.printStackTrace();
		}
	}
	
	public void WriteFile() {
		File direct = new File(videopath);
		//String vname = videoname.replace(".mp4", "");
		File filevideo = new File(direct, videoname);
		try {
			//File filevideo = File.createTempFile(vname, ".mp4", direct);
			//Files.copy(in, filevideo.toPath(), StandardCopyOption.REPLACE_EXISTING); //Phuong thuc nay khong ghi de file
			Files.copy(in, filevideo.toPath());
		} catch (Exception e) {
			BO.Instance().UpdateErrorFileDB(file);
			//e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			System.out.println("Xu ly is running");
			//part.write(videopath+"/"+videoname);
			WriteFile();
			System.out.println("Write video success!!!");
			File video = new File(videopath+"/"+videoname);
			File audio = new File(audiopath+"/"+audioname);
			Convert(video, audio);
		} catch (Exception e) {
			BO.Instance().UpdateErrorFileDB(file);
			e.printStackTrace();
		}
	}
}
