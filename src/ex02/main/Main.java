package ex02.main;

import ex02.service.StudentService;

public class Main {
    public static void main(String[] args) {

        // Bài 1: thêm sinh viên
        // StudentService.addStudents();

        // Bài 2: cập nhật sinh viên
        StudentService.updateStudent(1, "Nguyen Van A (Updated)", 25);
    }
}

