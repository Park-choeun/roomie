package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.StudentManager;
import model.service.StudentNotFoundException;
import model.Student;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		StudentManager manager = StudentManager.getInstance();
		String userId = request.getParameter("userId");

    	Student student = null;
    	try {
			student = manager.findStudent(userId);	// ����� ���� �˻�
		} catch (StudentNotFoundException e) {
	        return "redirect:/user/list";
		}	
		
    	request.setAttribute("user", student);		// ����� ���� ����
		return "/user/view.jsp";				// ����� ���� ȭ������ �̵�
    }
}
