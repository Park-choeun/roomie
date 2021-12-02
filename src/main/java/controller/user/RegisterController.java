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
		List<College> colList = CollegeManager.getInstance().findCollegeList();
		if (request.getMethod().equals("GET")) {
			// GET request: ȸ������ ��� form ��û
			log.debug("RegisterForm Request");

			request.setAttribute("colList", colList);

			return "/student/registerForm.jsp";   // �˻��� ����� ������ update form���� ����
		}

		// POST request (ȸ�������� parameter�� ���۵�)
		Student student = new Student (
				request.getParameter("name"),
				request.getParameter("email"),
				request.getParameter("password"),
				Integer.parseInt(request.getParameter("gender")),
				Integer.parseInt(request.getParameter("college")));

		log.debug("Create Student : {}", student);
		String email = request.getParameter("email");
		try {
			StudentManager manager = StudentManager.getInstance();
			manager.create(student);
//			return "redirect:/student/loginForm.jsp";
			request.setAttribute("email", email);
			return "/profile/myPage/createForm.jsp";

		} catch (ExistingStudentException e) {	// ���� �߻� �� ȸ������ form���� forwarding
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("student", student);
			request.setAttribute("colList", colList);
			return "/student/registerForm.jsp";
		}
	}
}
