import java.sql.*;

//installing jdbc driver
//make sure sql server and sql server browser is active


public class Sql {
    public static void main(String[] args){
        String url = "jdbc:sqlserver://DESKTOP-PV4B9UK\\SQLEXPRESS;databaseName=test;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "776195A";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Connection successful, do something...
            System.out.println("Connected to the database!");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
            // Process the results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

        } catch (SQLException e) {
            // Connection failed, handle the exception...
            e.printStackTrace();
        }
    }
}

