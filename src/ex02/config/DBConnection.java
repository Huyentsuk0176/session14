package ex02.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 🔹 ĐỔI nếu máy em khác
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "student_management";
    private static final String USER = "root";
    private static final String PASSWORD = "0311"; // đổi đúng password MySQL của em

    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
                    + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

