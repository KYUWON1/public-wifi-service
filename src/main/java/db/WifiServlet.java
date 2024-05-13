package db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import java.io.IOException;

@WebServlet("/CRUDHistory")
public class WifiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
		String action = request.getParameter("action");
		WifiService ws = new WifiService();
		
		if(action.equals("addNew")){
			String name = request.getParameter("bookmarkName");
			String seq = request.getParameter("bookmarkSequence");
			int sequnece = Integer.parseInt(seq);
			
			ws.insertBm(name,sequnece);
			response.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
			response.getWriter().write("Bookmark Inserted Id: "+sequnece);
		}else if(action.equals("addWifi")){
			String bookmarkId  = request.getParameter("bookmarkId");
			String wifiId  = request.getParameter("wifiId");
			
			System.out.println(bookmarkId+wifiId);
			
			ws.insertWifitoBm(bookmarkId,wifiId);
			response.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
			response.getWriter().write("Bookmark Inserted Id: "+wifiId);
		}else if(action.equals("update")){
			String fixname = request.getParameter("name");
			System.out.println(fixname);
			String id = request.getParameter("id");
			int intId = Integer.parseInt(id);
			
			ws.updateBmName(intId,fixname);
			response.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
			response.getWriter().write("Bookmark update name: "+fixname);
		}	
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
		WifiService ws = new WifiService();
		String action = req.getParameter("action");
		if(action.equals("deleteList")) {
			String seq = req.getParameter("id");
			int sequnece = Integer.parseInt(seq);
			
			ws.deleteBm(sequnece);
			res.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
			res.getWriter().write("Bookmark Deleted Id: "+sequnece);
		}else if(action.equals("deleteOne")) {
			String id = req.getParameter("id");

		    if (id == null) {
		        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		        res.getWriter().write("ID parameter is missing");
		        return;
		    }
		    
			ws.deleteBm(id);
			res.setCharacterEncoding("UTF-8");  // 요청 인코딩 설정
			res.getWriter().write("Bookmark Deleted Id: "+id);
		}
	}
	
	
}
