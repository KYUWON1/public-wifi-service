package db;
import db.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WifiService {
	
	public List<Wifilocate> gettop20(String lat,String lnt) {
		Database db = new Database();
		
		List<Wifilocate> result = new ArrayList<>();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	
        	//
        	String sql = "SELECT *,\r\n"
        			+ "       (6371 * acos(\r\n"
        			+ "           cos(radians( ? )) * cos(radians(LAT)) * cos(radians(LNT) - radians( ? )) +\r\n"
        			+ "           sin(radians( ? )) * sin(radians(LAT))\r\n"
        			+ "       )) AS distance\r\n"
        			+ "FROM wifi_locate\r\n"
        			+ "ORDER BY distance\r\n"
        			+ "LIMIT 20;";
        	
        	preparedStatement = connection.prepareStatement(sql);
        
            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, lat);
            
            rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		Wifilocate wc = new Wifilocate();
        		wc.setId(rs.getString("id"));
        		wc.setName(rs.getString("name"));
        		wc.setLat(rs.getString("lat"));
        		wc.setLnt(rs.getString("lnt"));
        		result.add(wc);
        	}
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }	
		
		return result;
	}
 
	public List<Wifilocate> gethistory(){
		Database db = new Database();
		
		List<Wifilocate> result = new ArrayList<>();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	String sql = "select * from wifi_history";      	
        	preparedStatement = connection.prepareStatement(sql);
            
            rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		Wifilocate wc = new Wifilocate();
        		wc.setId(rs.getString("id"));
        		wc.setName(rs.getString("name"));
        		wc.setLat(rs.getString("lat"));
        		wc.setLnt(rs.getString("lnt"));
        		result.add(wc);
        	}
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }

		return result;
	}

	public List<Bookmark> getBmList(){
		Database db = new Database();
		List<Bookmark> list = new ArrayList<>();
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	String sql = "select * from bookmark_list order by sequence;";		
        	
        	preparedStatement = connection.prepareStatement(sql);
        	
        	rs = preparedStatement.executeQuery();
        	while(rs.next()) {
        		Bookmark bm = new Bookmark();
        		bm.setId(rs.getInt("id"));
        		bm.setName(rs.getString("name"));
        		bm.setSequence(rs.getInt("sequence"));
        		bm.setReg_date(rs.getDate("registration_date"));
        		bm.setMod_date(rs.getDate("modification_date"));
        		list.add(bm);
        	}
        	if(list.size() != 0) {
        		return list;
        	}
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
		return null;
	}
	
	public void insertBm(String name, int seq) {
		Database db = new Database();
		List<Bookmark> list = new ArrayList<>();
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	
        	String sql = " insert into bookmark_list (name,sequence) values ( ?, ? )";		
        	
        	preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1,name);
        	preparedStatement.setInt(2, seq);
        	
        	int affectedrow = preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
	}
	
	public Wifidetail getDetail(String id) {
		Database db = new Database();
		Wifidetail wd = new Wifidetail();
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	
        	String sql = "select * from wifi_detail where id = ? ;";		
        	
        	preparedStatement = connection.prepareStatement(sql);
        	
        	preparedStatement.setString(1, id);
        	
        	rs = preparedStatement.executeQuery();
        	if(rs.next()) {
        		wd.setId(rs.getString("id"));
        		wd.setCity(rs.getString("city"));
        		wd.setName(rs.getString("name"));
        		wd.setStreet(rs.getString("street"));
        		wd.setAddress(rs.getString("address"));
        		wd.setFloor(rs.getString("floor"));
        		wd.setBulid_type(rs.getString("build_type"));
        		wd.setBulider(rs.getString("builder"));
        		wd.setService_type(rs.getString("service_type"));
        		wd.setWifi_type(rs.getString("wifi_type"));
        		wd.setSet_date(rs.getString("set_date"));
        		wd.setInout_door(rs.getString("inout_door"));
        		wd.setWifi_environ(rs.getString("wifi_environ"));
        		wd.setWork_date(rs.getString("work_date"));
        	}
        	
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
		return wd;
	}

	public List<BmWifi> getWifitoBm(){
		Database db = new Database();
		List<BmWifi> result = new ArrayList<>();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String selectSql = "select * from bookmark_wifi;";
			
			 preparedStatement = connection.prepareStatement(selectSql);
			
			 rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 BmWifi bm = new BmWifi();
		            bm.setId(rs.getInt("id"));
		            bm.setBmName(rs.getString("bmName"));
		            bm.setWifiName(rs.getString("wifiName"));
		            bm.setWifiId(rs.getString("wifiId"));
		            bm.setReg_date(rs.getDate("registration_date"));
		            result.add(bm);
		        }
			 if(result.size() != 0) {
				 return result;
			 }
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
        return null;
	}
	
	public void updateBmName(int id,String name) {
		Database db = new Database();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String updateSql = "update bookmark_list set name = ?,modification_date = CURRENT_TIMESTAMP where id = ? ";
			
        	System.out.println(name);
			 preparedStatement = connection.prepareStatement(updateSql);
			 preparedStatement.setString(1, name);
			 preparedStatement.setInt(2, id);
			 int affectedrow = preparedStatement.executeUpdate();
			 
			 String updateSql2 = "update bookmark_wifi set bmName = ? where id = ? ";
			 preparedStatement = connection.prepareStatement(updateSql2);
			 preparedStatement.setString(1, name);
			 preparedStatement.setInt(2, id);
			 int affectedrow2 = preparedStatement.executeUpdate();
			 
			 System.out.println("update complete:"+affectedrow+affectedrow2);
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
	}
	
	public void insertWifitoBm(String id,String name) {
		Database db = new Database();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        System.out.println(id+name);
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String updateSql = "insert into bookmark_wifi (id, wifiId, bmName, wifiName) \n"
        			+ "SELECT b.id,w.id, b.name, w.name \n"
        			+ "FROM bookmark_list b \n"
        			+ "CROSS JOIN wifi_detail w \n"
        			+ "WHERE b.name = ? \n"
        			+ "  AND w.id = ?  ";
			
			 preparedStatement = connection.prepareStatement(updateSql);
			 preparedStatement.setString(1, id);
			 preparedStatement.setString(2, name);
			 
			 int affectedrow=preparedStatement.executeUpdate();
			 System.out.println("update into wifi_bm successfull:"+affectedrow);
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
	}
	
	public void deleteBm(String id) {
		Database db = new Database();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String Sql = "delete from bookmark_wifi where wifiId = ? ;";

			preparedStatement = connection.prepareStatement(Sql);
			
			preparedStatement.setString(1,id);
			
			int affectedRows = preparedStatement.executeUpdate(); // 변경된 행의 수를 반환
	        System.out.println(affectedRows + " 행이 삭제되었습니다.");
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
	}
	
	public void deleteBm(int id) {
		Database db = new Database();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	// 먼저 bookmark_wifi에서 관련 데이터를 삭제
            String sql2 = "delete from bookmark_wifi where id = ? ";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, id);
            int affectedRow2 = preparedStatement.executeUpdate();
            System.out.println("Deleted from bookmark_wifi: " + affectedRow2);
            
            // 다음으로 bookmark_list에서 데이터 삭제
            String sql = "delete from bookmark_list where id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int affectedRow1 = preparedStatement.executeUpdate();
        	int affectedrow = preparedStatement.executeUpdate();
        	System.out.println(affectedrow);
        	System.out.println(affectedRow1);
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }	
	}
	
	private void closeResources(AutoCloseable... resources) {
	    for (AutoCloseable resource : resources) {
	        if (resource != null) {
	            try {
	                resource.close();
	            } catch (Exception e) {
	                System.out.println("Failed to close resource: " + e.getMessage());
	            }
	        }
	    }
	}

	

	
}
