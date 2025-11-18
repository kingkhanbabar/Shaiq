package daily2;

import java.sql.*;

public class TestOracle {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String user = "SYSTEM";
        String pass = "student";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");
            while(rs.next()){
                System.out.println(rs.getInt("ID") + " " +
                        rs.getString("NAME") + " " +
                        rs.getString("COURSE") + " " +
                        rs.getInt("MARKS"));
            }
            con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
