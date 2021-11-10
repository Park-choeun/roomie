package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Profile;
import model.service.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

public class UpdateProfileController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateProfileController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
 
    	if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ���� form ��û	
    		// ������ UpdateUserFormController�� ó���ϴ� �۾��� ���⼭ ����
    		int s_id = Integer.parseInt(request.getParameter("s_id"));

    		log.debug("UpdateForm Request : {}", s_id);
    		
    		ProfileManager manager = ProfileManager.getInstance();
			Profile profile = manager.findProfile(s_id);	// �����Ϸ��� ����� ���� �˻�
			request.setAttribute("profile", profile);

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(s_id, session) ||
				UserSessionUtils.isLoginUser(0, session)) {  //�������� s_id = 0

				return "/profile/updateForm.jsp";   // �˻��� ����� ������ update form���� ����
			}    
			
			// else (���� �Ұ����� ���) ����� ���� ȭ������ ���� �޼����� ����
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("Ÿ���� ������ ������ �� �����ϴ�."));            
			return "/student/loginForm.jsp";	// ����� ���� ȭ������ �̵� (forwarding)
	    }	
    	
    	// POST request (ȸ�������� parameter�� ���۵�)
    	Profile updateStudent = new Profile(
				Integer.parseInt(request.getParameter("s_id")),
				Boolean.parseBoolean(request.getParameter("activation")),
				request.getParameter("name"),
				Integer.parseInt(request.getParameter("pr_img")),
				Integer.parseInt(request.getParameter("age")),
				Integer.parseInt(request.getParameter("sleep_habit")),
				Integer.parseInt(request.getParameter("lifestyle")),
				Integer.parseInt(request.getParameter("smoking")),
				Integer.parseInt(request.getParameter("grade")),
				request.getParameter("major"),
				Integer.parseInt(request.getParameter("cleaning")),
				Integer.parseInt(request.getParameter("indoor_eating")),
				Integer.parseInt(request.getParameter("mbti")),
				Integer.parseInt(request.getParameter("sharing")),
				Integer.parseInt(request.getParameter("habitude"))
		);

    	log.debug("Update User : {}", updateStudent);

		ProfileManager manager = ProfileManager.getInstance();
		manager.update(updateStudent);
        return "redirect:/profile/view";
    }
}
