package ex03.ra.main;

import ex03.ra.service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService.addStudents();
        StudentService.updateStudent(1,"nguyen van a(updated)",25);
        StudentService.deleteStudentsByAge(20);
    }
}




