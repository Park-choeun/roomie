package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Profile;
import model.service.ProfileManager;

public class ListProfileController implements Controller {
	private static final int countProfilePage = 10;	// �� ȭ�鿡 ����� ����� ��

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

    	String currentpagePro = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentpagePro != null && !currentpagePro.equals("")) {
			currentPage = Integer.parseInt(currentpagePro);
		}

		int s_id = (int)UserSessionUtils.getLoginUserId(request.getSession());
		ProfileManager manager = ProfileManager.getInstance();
		List<Profile> profileList = (List<Profile>) manager.findProfile(s_id);
//		List<Profile> profileList = manager.findProfileList(currentPage, countProfilePage);


		// profileList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("profileList", profileList);
		request.setAttribute("s_id", s_id);

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/student/main.jsp";
    }
}
