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


//API 데이터는 미리 저장해놓고 사용 
public class publicAPI {
	
	//API를 활용해서 받아온 데이터를 DB에 저장해주는 메소드 
	public static void InsertDB(List<Wifidetail> wifiDetails) {
		Database db = new Database();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
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
            	preparedStatement.setString(14, wd.getWork_date());
            	
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
	
	//사용자에게 보여주는 간략한 형태의 wifi_locate DB 생성 
	public static void InsertDB2(List<Wifilocate> wifilocates) {
		Database db = new Database();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
        	connection = db.getDb();
        	
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
	
	//공공데이터 API를 활용해서 데이터를 parsing한 후 JSON array로 return 해주는 함수 
	public static JSONArray getWifiData(String startNum,String endNum) {
    	//key 요청타입 페이징시작번호 페이징끝번호 자치구(선택) 도로명주소(선택) 
        String key = "685a6a76786162733638485257746b";
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
          return array;
          }catch (Exception e) {
              e.printStackTrace();
          }
		return null;
	}
	
	//최초 DB 저장을 위한 Main
    public static void main(String[] args) {
     // 특정 데이터 접근 예제
        JSONArray array = getWifiData("1","1000");	
        List<Wifilocate> wifiList = new ArrayList<>();
        //wifilocate, wifi_detail 2개의 테이블을 만들기 위해 코드를 2번 수정해서 사용했음 
        for(Object ob : (JSONArray)array) {
        	Wifilocate wifilocate = new Wifilocate(
        			(String) ((JSONObject) ob).get("X_SWIFI_MGR_NO"),
        			(String) ((JSONObject) ob).get("X_SWIFI_MAIN_NM"),
        			(String) ((JSONObject) ob).get("LAT"),
        			(String) ((JSONObject) ob).get("LNT")
        			);
        	
        	wifiList.add(wifilocate);
        	System.out.println(wifilocate);
      
        }
        InsertDB2(wifiList);
    } 
}

