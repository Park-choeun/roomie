package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Profile;
import model.service.ProfileManager;
import model.service.StudentNotFoundException;
import model.Student;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

		ProfileManager manager = ProfileManager.getInstance();
		int userId = Integer.parseInt(request.getParameter("userId"));

    	Profile profile = null;
    	try {
			profile = manager.findProfile(userId);	// ����� ���� �˻�
		} catch (StudentNotFoundException e) {
	        return "redirect:/student/main";
		}	
		
    	request.setAttribute("profile", profile);		// ����� ���� ����
		return "/student/main/detail.jsp";				// ����� ���� ȭ������ �̵�
    }
}
