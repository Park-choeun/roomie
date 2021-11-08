package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Profile;

public class ListProfileController implements Controller {
	private static final int countProfilePage = 10;	// �� ȭ�鿡 ����� ����� ��

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/loginForm";		// login form ��û���� redirect
        }

    	String currentpagePro = request.getParameter("currentPage");
		int currentPage = 1;
		if (currentpagePro != null && !currentpagePro.equals("")) {
			currentPage = Integer.parseInt(currentpagePro);
		}
    	
		ProfileManager manager = ProfileManager.getInstance();   //return studentManager
		//List<Profile> profileList = manager.findProfileList();
		List<Profile> profileList = manager.findProfileList(currentPage, countProfilePage);

		// profileList ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("profileList", profileList);
		request.setAttribute("s_id",
				UserSessionUtils.getLoginUserId(request.getSession()));		

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/student/main.jsp";
    }
}
