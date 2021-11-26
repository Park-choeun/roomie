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
        if (request.getMethod().equals("GET")) {
            Profile profile = ProfileManager.getInstance().findProfile(receiver);

            log.debug("receiver�� name Ȯ�� :: " + profile.getName());
            request.setAttribute("receiver", receiver);
            request.setAttribute("receiver_name", profile.getName());

            return "/mail/sendForm.jsp";
        }

        //POST
        try {
            //���� ��ư ������ ��
            String message = request.getParameter("message");

            //��¥, �ð� ���ϱ�
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now); // 2021-06-17T06:43:21.419878100
            String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm�� ss��"));

            Mail m = new Mail(s_id, receiver, message, formatedNow, 0);
            Scrap s = new Scrap(s_id, receiver);

            mailManager.create(m);

            return "/mail/sendList.jsp";

        } catch (Exception e) {
            return "/student/main.jsp";
        }
    }
}
