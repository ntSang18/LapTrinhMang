package controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import model.BO; 
import model.bean.DBFile;
import model.bean.User;

@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("user");
		try {
			Part part = request.getPart("file");
			InputStream filecontent = part.getInputStream();
			if(!part.getSubmittedFileName().endsWith(".mp4")) {
				String error = "File không phải là video/mp4";
				request.setAttribute("error", error);
				request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
				return;
			}
			
			String videopath = request.getServletContext().getRealPath("/video");
			String videoname = Path.of(part.getSubmittedFileName()).getFileName().toString();
			String audiopath = request.getServletContext().getRealPath("/audio");
			String audioname = videoname.replace("mp4", "mp3");
			
			DBFile file = new DBFile();
			file.setVideoname(videoname);
			file.setVideopath(videopath+"/"+videoname);
			file.setAudioname(audioname);
			file.setAudiopath(audiopath);
			file.setIduser(u.getId());
			file.setStatus(0);
			
			DBFile f = BO.Instance().AddFileDB(file);
			
			System.out.println("Video path: "+videopath);
			System.out.println("Audio path: "+audiopath);
			
			if(!Files.exists(Path.of(videopath))){
				Files.createDirectory(Path.of(videopath));
			}
			if(!Files.exists(Path.of(audiopath))){
				Files.createDirectory(Path.of(audiopath));
			}
			//part.write(videopath+"/"+videoname);
			//File video = new File(videopath+"/"+videoname);
			//File audio = new File(audiopath+"/"+audioname);
			//XuLy file using thread
			new XuLy(filecontent, videopath, videoname, audiopath, audioname, f).start();
			
			//BO.Instance().UpdateFileDB(f);
			List<DBFile> ListFile = BO.Instance().GetListFileByIduser(u.getId());
			
			request.setAttribute("ListFile", ListFile);
			request.getRequestDispatcher("Repository.jsp").forward(request, response);
		} catch (Exception e) {
			String error = "Có lỗi xảy ra";
			request.setAttribute("error", error);
			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
