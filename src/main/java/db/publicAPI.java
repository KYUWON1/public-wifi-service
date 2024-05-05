package db;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import db.model.Wifilocate;
import db.model.Wifidetail;

public class publicAPI {
	public static void InsertDB(List<Wifidetail> wifiDetails) {
		//db 정보 가져오기 
        String dburl = "jdbc:mariadb://localhost:3306/public_wifi";
        String dbUserId = "testuser";
        String dbPassword = "tlarb3011";
        
      //드라이버가져오기 
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("연결성공");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = DriverManager.getConnection(dburl,dbUserId,dbPassword);
        	
        	String sql = " insert into wifi_detail (id, city, name, street, address, floor, build_type, builder, service_type, wifi_type, set_date, "
        			+ " inout_door, wifi_environ, work_date) "
        			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ); ";
        	preparedStatement = connection.prepareStatement(sql);
        	for(Wifidetail wd : wifiDetails) {
        		preparedStatement.setString(1, wd.getId());
            	preparedStatement.setString(2, wd.getCity());
            	preparedStatement.setString(3, wd.getName());
            	preparedStatement.setString(4, wd.getStreet());
            	preparedStatement.setString(5, wd.getAddress());
            	preparedStatement.setString(6, wd.getFloor());
            	preparedStatement.setString(7, wd.getBulid_type());
            	preparedStatement.setString(8, wd.getBulider());
            	preparedStatement.setString(9, wd.getService_type());
            	preparedStatement.setString(10, wd.getWifi_type());
            	preparedStatement.setString(11, wd.getSet_date());
            	preparedStatement.setString(12, wd.getInout_door());
            	preparedStatement.setString(13, wd.getWifi_environ());
            	preparedStatement.setString(14, wd.getWord_date());
            	
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
	public static void InsertDB2(List<Wifilocate> wifilocates) {
		//db 정보 가져오기 
        String dburl = "jdbc:mariadb://localhost:3306/public_wifi";
        String dbUserId = "testuser";
        String dbPassword = "tlarb3011";
        
      //드라이버가져오기 
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("연결성공");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = DriverManager.getConnection(dburl,dbUserId,dbPassword);
        	
        	String sql = " insert into wifi_locate (id, name, lat, lnt) "
        			+ "VALUES ( ?, ?, ?, ? ); ";
        	preparedStatement = connection.prepareStatement(sql);
        	for(Wifilocate wc : wifilocates) {
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
	
    public static void main(String[] args) {
    	//API요청 부분 
    	//key 요청타입 페이징시작번호 페이징끝번호 자치구(선택) 도로명주소(선택) 
        String key = "685a6a76786162733638485257746b";
        String startNum = "1";
        String endNum = "1000";
        String url = "http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/"+startNum+"/"+endNum+"/";      

        try {	
            URL obj = new URL(url); // 연결하고자 하는 URL 설정
            HttpURLConnection con = (HttpURLConnection) obj.openConnection(); //url과의 Http 연결 설정 
            con.setRequestMethod("GET"); // GET Method 선택
            // 응답 코드 가져오기
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            // 응답 내용 읽기, 응답객체의 inputStream으로 부터 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            // 버퍼로부터 들여온 모든 데이터를 response 버퍼에 저장 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            JSONObject jsonResponse = new JSONObject(response.toString());
            
            JSONObject data = (JSONObject) jsonResponse.get("TbPublicWifiInfo");

         // 특정 데이터 접근 예제
            JSONArray array = (JSONArray) data.get("row");	
            //List<Wifidetail> wifiList = new ArrayList<>();
            List<Wifilocate> wifiList = new ArrayList<>();
            for(Object ob : (JSONArray)array) {
            	Wifilocate wifilocate = new Wifilocate(
//            			(String) ((JSONObject) ob).get("X_SWIFI_MGR_NO"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_WRDOFC"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_MAIN_NM"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_ADRES1"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_ADRES2"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_INSTL_FLOOR"), //없는것도있음 
//            			(String) ((JSONObject) ob).get("X_SWIFI_INSTL_TY"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_INSTL_MBY"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_SVC_SE"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_CMCWR"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_CNSTC_YEAR"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_INOUT_DOOR"),
//            			(String) ((JSONObject) ob).get("X_SWIFI_REMARS3"), //없는것도있음
//            			(String) ((JSONObject) ob).get("WORK_DTTM")
            			(String) ((JSONObject) ob).get("X_SWIFI_MGR_NO"),
            			(String) ((JSONObject) ob).get("X_SWIFI_MAIN_NM"),
            			(String) ((JSONObject) ob).get("LAT"),
            			(String) ((JSONObject) ob).get("LNT")
            			);
            	
            	wifiList.add(wifilocate);
            	System.out.println(wifilocate);
          
            }
            //InsertDB(wifiList);
            InsertDB2(wifiList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
