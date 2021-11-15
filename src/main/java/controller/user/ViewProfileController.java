package controller.user;

import controller.Controller;
import model.Profile;
import model.service.ProfileManager;
import model.service.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ViewProfileController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ViewProfileController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

		ProfileManager manager = ProfileManager.getInstance();

		int userId = Integer.parseInt(request.getParameter("s_id"));
		log.debug("s_idȮ��::" + userId);

    	Profile profile = null;
    	try {
			profile = manager.findProfile(userId);	// ����� ���� �˻�
			request.setAttribute("profile", profile);		// ����� ���� ����
			return "/student/main/detail.jsp";				// ����� ���� ȭ������ �̵�
		} catch (StudentNotFoundException e) {
	        return "redirect:/student/main";
		}
    }
}
