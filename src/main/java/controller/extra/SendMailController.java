package controller.extra;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Mail;
import model.Profile;
import model.Scrap;
import model.service.MailManager;
import model.service.ProfileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SendMailController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(SendMailController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // �α��� ���� Ȯ��
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }
        HttpSession session = request.getSession();
        int s_id = (int) session.getAttribute(UserSessionUtils.USER_SESSION_ID);
        MailManager mailManager = MailManager.getInstance();
        int receiver = Integer.parseInt(request.getParameter("receiver"));

        log.debug("s_id Ȯ��: " + s_id + " receiver_id Ȯ��: " + receiver);

        //GET
        Profile profile = ProfileManager.getInstance().findProfile(receiver);

        log.debug("receiver�� name Ȯ�� :: " + profile.getName());
        request.setAttribute("receiver", receiver);
        request.setAttribute("receiver_name", profile.getName());

        return "/mail/send/sendForm.jsp";
    }
}
