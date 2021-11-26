package controller.extra;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Profile;
import model.service.MailManager;
import model.service.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteMailController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteMailController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // �α��� ���� Ȯ��
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }
        HttpSession session = request.getSession();
        int s_id = (int) session.getAttribute(UserSessionUtils.USER_SESSION_ID);
        MailManager mailManager = MailManager.getInstance();
        int ch_id = Integer.parseInt(request.getParameter("ch_id"));
        int flag = Integer.parseInt(request.getParameter("flag"));

        log.debug("s_id Ȯ��: " + s_id + ", ch_id Ȯ��: " + ch_id + ", flag Ȯ��: " + flag);

        //GET
        mailManager.remove(ch_id);

        if (flag == 0) {
            return "/mail/receive/receiveList.jsp";
        }
        return "/mail/send/sendForm.jsp";
    }
}
