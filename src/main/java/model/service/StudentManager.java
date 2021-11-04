package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Student;
import model.dao.StudentDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class StudentManager {
	private static StudentManager userMan = new StudentManager();
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
		return userMan;
	}
	
	public int create(Student student) throws SQLException, ExistingStudentException {
		if (studentDao.existingUser(student.getStudentId()) == true) {
			throw new ExistingStudentException(student.getStudentId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return studentDao.create(student);
	}

	public int update(Student student) throws SQLException, StudentNotFoundException {
		return studentDao.update(student);
	}	

	public int remove(String studentId) throws SQLException, StudentNotFoundException {

		return studentDao.remove(studentId);
	}

	public Student findStudent(String studentId)
		throws SQLException, StudentNotFoundException {
		Student student = studentDao.findStudent(studentId);
		
		if (student == null) {
			throw new StudentNotFoundException(studentId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return student;
	}


	public List<Student> findStudentList() throws SQLException {
			return studentDao.findStudentList();
	}

	public List<Student> findStudentList(int currentPage, int countPerPage)
		throws SQLException {
		return studentDao.findStudentList(currentPage, countPerPage);
	}


	public boolean login(String studentId, String password)
		throws SQLException, StudentNotFoundException, PasswordMismatchException {
		Student student = findStudent(studentId);

		if (!student.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}



	public StudentDAO getStudentDao() {
		return this.studentDao;
	}
}
