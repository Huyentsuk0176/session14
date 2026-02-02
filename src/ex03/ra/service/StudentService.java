package ex03.ra.service;

import ex03.ra.config.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentService {

    /* =========================
       ADD STUDENTS
       ========================= */
    public static void addStudents() {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "{CALL add_students(?, ?)}";
            cs = conn.prepareCall(sql);

            insert(cs, "Nguyen Van A", 19);
            insert(cs, "Tran Thi B", 20);
            insert(cs, "Le Van C", 22);
            insert(cs, "Pham Thi Huyen", 18);

            conn.commit();
            System.out.println(" Đã thêm sinh viên!");

        } catch (SQLException e) {
            rollback(conn);
            System.out.println("Lỗi khi thêm sinh viên!");
            e.printStackTrace();
        } finally {
            close(conn, cs);
        }
    }

    private static void insert(CallableStatement cs, String name, int age) throws SQLException {
        cs.setString(1, name);
        cs.setInt(2, age);
        cs.executeUpdate();
    }

    /* =========================
       UPDATE STUDENT BY ID
       ========================= */
    public static void updateStudent(int id, String newName, int newAge) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "{CALL update_student(?, ?, ?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);
            cs.setString(2, newName);
            cs.setInt(3, newAge);

            int rows = cs.executeUpdate();
            if (rows > 0) {
                conn.commit();
                System.out.println(" Cập nhật sinh viên thành công!");
            } else {
                conn.rollback();
                System.out.println(" Không tìm thấy sinh viên để cập nhật!");
            }

        } catch (SQLException e) {
            rollback(conn);
            System.out.println(" Lỗi khi cập nhật sinh viên!");
            e.printStackTrace();
        } finally {
            close(conn, cs);
        }
    }

    /* =========================
       DELETE STUDENTS BY AGE
       ========================= */
    public static void deleteStudentsByAge(int age) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "{CALL delete_students_by_age(?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, age);

            int deleted = cs.executeUpdate();
            conn.commit();

            System.out.println(" Đã xoá " + deleted + " sinh viên có tuổi < " + age);

        } catch (SQLException e) {
            rollback(conn);
            System.out.println(" Lỗi khi xoá sinh viên theo tuổi!");
            e.printStackTrace();
        } finally {
            close(conn, cs);
        }
    }

    /* =========================
       COMMON UTILS
       ========================= */
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
