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

@WebServlet("/DirectionServlet")
public class DirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DirectionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mod = Integer.parseInt(request.getParameter("mod"));
		if(mod == 1) {
			response.sendRedirect("Upload.jsp");
			return;
		}
		else if(mod == 2) {
			User u = (User)request.getSession().getAttribute("user");
			List<DBFile> ListFile = BO.Instance().GetListFileByIduser(u.getId());
			request.setAttribute("ListFile", ListFile);
			request.getRequestDispatcher("Repository.jsp").forward(request, response);
			return;
		}
		else if(mod == 3) {
			request.getSession().setAttribute("user", null);
			response.sendRedirect("Login.jsp");
			return;
		}
		else if(mod == 4) {
			response.sendRedirect("Index.jsp");
			return;
		}
		else if(mod == 5) {
			response.sendRedirect("Login.jsp");
		}
		else if(mod == 6) {
			response.sendRedirect("Register.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
