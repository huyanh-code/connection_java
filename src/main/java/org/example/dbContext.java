package org.example;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class dbContext {
    private static String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static String USER_NAME = "testuser";
    private static String PASSWORD = "testpass";

    public static void main(String[] args) {

//        conected();
//        insert();
//        update();
        delete();
    }

    private static void conected() {
        try {
            // connnect to database 'testdb'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from students");
            // show data
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void insert(){
        String sql = "INSERT INTO students VALUES(NULL, ?, ?)";

        try {
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Huy Anh");
            stmt.setInt(2, 18);
            stmt.execute();
            System.out.println("Them thanh cong student!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void update(){
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try{
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Dinh Quoc Trung");
            stmt.setInt(2, 20);
            stmt.setInt(3,3);
            stmt.execute();
            System.out.println("Thay doi thanh cong!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    static void delete(){
        String sql = "DELETE FROM students WHERE id = ?";

        try{
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 2);
            stmt.execute();
            System.out.println("Xoa thanh cong!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
