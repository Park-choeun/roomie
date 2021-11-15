package controller.user;

import controller.Controller;
import model.Profile;
import model.Scrap;
import model.service.ProfileManager;
import model.service.ScrapManager;
import model.service.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewProfileController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ViewProfileController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		int s_id = (int) session.getAttribute(UserSessionUtils.USER_SESSION_ID);

		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

		ProfileManager manager = ProfileManager.getInstance();
		ScrapManager scrapManager = ScrapManager.getInstance();

		int userId = Integer.parseInt(request.getParameter("s_id"));
		log.debug("detail user_idȮ��: " + userId);

    	Profile profile = null;
		boolean scrap;
    	try {
			profile = manager.findProfile(userId);	// ����� ���� �˻�

			scrap = scrapManager.isScraped(s_id, userId);

			log.debug("scrap ���� Ȯ��: " + scrap);
			
			request.setAttribute("profile", profile);		// ����� ���� ����
			request.setAttribute("scrap", scrap);		// ��ũ�� ���� ����
			return "/student/main/detail.jsp";				// ����� ���� ȭ������ �̵�
		} catch (StudentNotFoundException e) {
	        return "redirect:/student/main";
		}
    }
}
