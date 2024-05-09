package db;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Database {
	public Connection getDb() {
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
        try {
			connection = DriverManager.getConnection(dburl,dbUserId,dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return connection;
	}

}
