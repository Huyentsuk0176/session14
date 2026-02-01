package ex02.service;

import ex01.ra.config.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentService {

    // ====== BÀI 1: ADD ======
    public static void addStudents() {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "{CALL add_students(?, ?)}";
            cs = conn.prepareCall(sql);

            insertOne(cs, "Nguyen Van A", 20);
            insertOne(cs, "Tran Thi B", 21);
            insertOne(cs, "Le Van C", 22);

            conn.commit();
            System.out.println("✅ Thêm sinh viên thành công!");

        } catch (SQLException e) {
            rollback(conn);
            System.out.println("❌ Lỗi khi thêm sinh viên!");
        } finally {
            close(conn, cs);
        }
    }

    // ====== BÀI 2: UPDATE ======
    public static void updateStudent(int id, String newName, int newAge) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // transaction

            String sql = "{CALL update_student(?, ?, ?)}";
            cs = conn.prepareCall(sql);

            cs.setInt(1, id);
            cs.setString(2, newName);
            cs.setInt(3, newAge);

            int rows = cs.executeUpdate();

            if (rows > 0) {
                conn.commit();
                System.out.println("✅ Cập nhật sinh viên thành công!");
            } else {
                conn.rollback();
                System.out.println("⚠️ Không tìm thấy sinh viên để cập nhật!");
            }

        } catch (SQLException e) {
            rollback(conn);
            System.out.println("❌ Lỗi khi cập nhật sinh viên!");
            e.printStackTrace();
        } finally {
            close(conn, cs);
        }
    }

    // ====== HÀM PHỤ ======
    private static void insertOne(CallableStatement cs, String name, int age) throws SQLException {
        cs.setString(1, name);
        cs.setInt(2, age);
        cs.executeUpdate();
    }

    private static void rollback(Connection conn) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ignored) {}
    }

    private static void close(Connection conn, CallableStatement cs) {
        try {
            if (cs != null) cs.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException ignored) {}
    }
}
