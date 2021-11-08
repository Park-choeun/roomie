package model.service;

import java.sql.SQLException;

import model.Student;
import model.dao.StudentDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * StudentDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������
 * ������ �� �� �ִ�.
 */
public class StudentManager {
    private static StudentManager studentMan = new StudentManager();
    private StudentDAO studentDao;
    private StudentAnalysis userAanlysis;

    private StudentManager() {
        try {
            studentDao = new StudentDAO();
            /*userAanlysis = new UserAnalysis(studentDao);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StudentManager getInstance() {
        return studentMan;
    }

    public int create(Student student) throws SQLException, ExistingStudentException {
        if (studentDao.existingStudent(student.getEmail()) == true) {
            throw new ExistingStudentException(student.getEmail() + "�� �����ϴ� ���̵��Դϴ�.");
        }
        return studentDao.create(student);
    }

    public int remove(String email) throws SQLException, StudentNotFoundException {
        return studentDao.remove(email);
    }

    public Student findStudent(String email)
            throws SQLException, StudentNotFoundException {
        Student student = studentDao.findStudent(email);

        if (student == null) {
            throw new StudentNotFoundException(email + "�� �������� �ʴ� ���̵��Դϴ�.");
        }
        return student;
    }

    public boolean login(String email, String password)
            throws SQLException, StudentNotFoundException, PasswordMismatchException {
        Student student = findStudent(email);

        if (!student.matchPassword(password)) {
            throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
        }
        return true;
    }

    public StudentDAO getStudentDao() {
        return this.studentDao;
    }
}