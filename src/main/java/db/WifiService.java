package db;
import db.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WifiService {
	//여기도 성공여부 알려주어여함 
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

	//나중에 저장 성공 실패 알려주어여함 
	public void savehistory(List<Wifilocate> list) {
		Database db = new Database();
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	
        	//
        	String sql = "insert into wifi_history (id, name, lat, lnt) VALUES  ( ?, ?, ?, ? ) " +
        				" ON DUPLICATE KEY UPDATE id=id; "
        			;
        	
        	preparedStatement = connection.prepareStatement(sql);
        	for(Wifilocate wc :list) {
                preparedStatement.setString(1, wc.getId());
                preparedStatement.setString(2, wc.getName());
                preparedStatement.setString(3, wc.getLat());
                preparedStatement.setString(4, wc.getLnt());
                
                preparedStatement.addBatch();
        	}
        	//배치 단위로 처리 
        	int[] updateCounts = preparedStatement.executeBatch();
        	connection.commit();
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            try {
                if(rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }  
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

	public void InsertBm(String id) {
		Database db = new Database();
		Wifibm bm = new Wifibm();
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String selectSql = "SELECT d.id, d.name, d.city, d.street, d.address, l.lat, l.lnt " +
                    "FROM wifi_locate l " +
                    "LEFT JOIN wifi_detail d ON l.id = d.id " +
                    "WHERE l.id = ? ";
        	
        	//가져온 정보를 북마크에 넣을 쿼리 
			 String insertSql = "INSERT INTO wifi_bm (id, city, name, street, address, lat, lnt) " +
			                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			 preparedStatement = connection.prepareStatement(selectSql);
			
			 preparedStatement.setString(1, id);
			 rs = preparedStatement.executeQuery();
			 
			 preparedStatement = connection.prepareStatement(insertSql);
			 if (rs.next()) {
			     preparedStatement.setString(1, rs.getString("id"));
			     preparedStatement.setString(2, rs.getString("city"));
			     preparedStatement.setString(3, rs.getString("name"));
			     preparedStatement.setString(4, rs.getString("street"));
			     preparedStatement.setString(5, rs.getString("address"));
			     preparedStatement.setString(6, rs.getString("lat"));
			     preparedStatement.setString(7, rs.getString("lnt"));
			
			     // Execute the insert statement
			     int rowsAffected = preparedStatement.executeUpdate();
			     
			     if (rowsAffected > 0) {
			         // Insert successful
			         System.out.println("Inserted into wifi_bm successfully.");
			     } else {
			         // Insert failed
			         System.out.println("Failed to insert into wifi_detail.");
			     }
			 } else {
			     // No records found with the given id
			     System.out.println("No records found with the id: " + id);
			 }
        	
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
	}
	
	public List<Wifibm> getBm(){
		Database db = new Database();
		List<Wifibm> result = new ArrayList<>();
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
        	//정보를 가져올 쿼리 
        	String selectSql = "select * from wifi_bm;";
			
			 preparedStatement = connection.prepareStatement(selectSql);
			
			 rs = preparedStatement.executeQuery();
			 while (rs.next()) {
		            Wifibm bm = new Wifibm();
		            bm.setId(rs.getString("id"));
		            bm.setName(rs.getString("name"));
		            bm.setCity(rs.getString("city"));
		            bm.setStreet(rs.getString("street"));
		            bm.setAddress(rs.getString("address"));
		            bm.setLat(rs.getString("lat"));
		            bm.setLnt(rs.getString("lnt"));
		            result.add(bm);
		        }
        }catch (SQLException e) {
            System.out.println("에러발생");
            throw new RuntimeException(e);
        } finally {
            closeResources(rs, preparedStatement, connection);
        }
        return result;
	}
	
	public void updateBm(String id) {
		
	}
	public void deleteBm(String id) {
		
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
