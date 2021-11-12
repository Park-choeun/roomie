package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.College;
import model.Student;
import model.service.CollegeManager;
import model.service.ExistingStudentException;
import model.service.StudentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

public class RegisterController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equals("GET")) {
			// GET request: ȸ������ ��� form ��û
			log.debug("RegisterForm Request");

			List<College> colList = CollegeManager.getInstance().findCollegeList();	// Ŀ�´�Ƽ ����Ʈ �˻�
			request.setAttribute("commList", colList);

			return "/student/registerForm.jsp";   // �˻��� ����� ������ update form���� ����
		}

		// POST request (ȸ�������� parameter�� ���۵�)
		Student student = new Student (
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password"),
				Integer.parseInt(request.getParameter("gender")),
				request.getParameter("college"));

		log.debug("Create Student : {}", student);

		try {
			StudentManager manager = StudentManager.getInstance();
			manager.create(student);
			return "redirect:/student/loginForm.jsp";

		} catch (ExistingStudentException e) {	// ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("student", student);
			return "/student/registerForm.jsp";
		}
	}
}
