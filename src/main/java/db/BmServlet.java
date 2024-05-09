package db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import db.model.Wifibm;

import java.io.IOException;

@WebServlet("/insertBookmark")
public class BmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BmServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WifiService ws = new WifiService();
		List<Wifibm> list = ws.getBm();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("id");
		WifiService ws = new WifiService();
		ws.InsertBm(id);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf8");
		response.getWriter().write("Bookmark Added Id: "+id);
	}

}
