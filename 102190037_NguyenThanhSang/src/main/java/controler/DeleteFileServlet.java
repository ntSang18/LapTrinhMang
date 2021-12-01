package controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO;
import model.bean.DBFile;
import model.bean.User;

@WebServlet("/DeleteFileServlet")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteFileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean result = BO.Instance().DeteleFileDB(id);
		if(result) {
			User u = (User)request.getSession().getAttribute("user");
			List<DBFile> ListFile = BO.Instance().GetListFileByIduser(u.getId());
			request.setAttribute("ListFile", ListFile);
			request.getRequestDispatcher("Repository.jsp").forward(request, response);
			return;
		}
		else {
			String error = "Lỗi khi xóa file";
			request.setAttribute("error", error);
			request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
