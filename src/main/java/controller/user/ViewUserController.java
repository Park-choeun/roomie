package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Profile;
import model.service.StudentManager;
import model.service.StudentNotFoundException;
import model.Student;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

		Profile profile = null;

		ProfileManager manager = ProfileManager.getInstance();
		String studentId = request.getParameter("studentId");

    	try {
			profile = manager.findStudent(studentId);	// ����� ���� �˻�
		} catch (StudentNotFoundException e) {
	        return "redirect:/student/main";
		}	
		
    	request.setAttribute("student", student);		// ����� ���� ����
		return "/profile/view.jsp";				// ����� ���� ȭ������ �̵�
    }
}
