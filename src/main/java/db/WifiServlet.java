package db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.io.IOException;

@WebServlet("/CRUDHistory")
public class WifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        try {
            int count = publicAPI.StoreWifidateToDB();
            request.setAttribute("count", count);
            request.getRequestDispatcher("/getApi.jsp").forward(request, response);
        } catch (Exception e) {
            response.getWriter().println("<p>데이터 처리 중 오류 발생: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		WifiService ws = new WifiService();
		
		if(action.equals("addNew")){
			String name = request.getParameter("bookmarkName");
			String seq = request.getParameter("bookmarkSequence");
			int sequnece = Integer.parseInt(seq);
			
			ws.insertBm(name,sequnece);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf8");
			response.getWriter().write("Bookmark Inserted Id: "+sequnece);
		}else if(action.equals("addWifi")){
			String bookmarkId  = request.getParameter("bookmarkId");
			String wifiId  = request.getParameter("wifiId");
			
			System.out.println(bookmarkId+wifiId);
			
			ws.insertWifitoBm(bookmarkId,wifiId);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf8");
			response.getWriter().write("Bookmark Inserted Id: "+wifiId);
		}else if(action.equals("update")){
			String fixname = request.getParameter("name");
			String id = request.getParameter("id");
			int intId = Integer.parseInt(id);
			
			ws.updateBmName(intId,fixname);
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf8");
			response.getWriter().write("Bookmark update name: "+fixname);
		}	
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		WifiService ws = new WifiService();
		String action = req.getParameter("action");
		if(action.equals("deleteList")) {
			String seq = req.getParameter("id");
			int sequnece = Integer.parseInt(seq);
			
			ws.deleteBm(sequnece);
			res.setContentType("text/plain");
			res.setCharacterEncoding("utf8");
			res.getWriter().write("Bookmark Deleted Id: "+sequnece);
		}else if(action.equals("deleteOne")) {
			String id = req.getParameter("id");

		    if (id == null) {
		        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        res.getWriter().write("ID parameter is missing");
		        return;
		    }
		    
			ws.deleteBm(id);
			res.setContentType("text/plain");
			res.setCharacterEncoding("utf8");
			res.getWriter().write("Bookmark Deleted Id: "+id);
		}
	}
	
	
}
