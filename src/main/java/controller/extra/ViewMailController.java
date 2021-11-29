package controller.extra;

import controller.Controller;
import controller.user.UserSessionUtils;
import controller.user.ViewProfileController;
import model.Mail;
import model.Profile;
import model.service.MailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ProfileManager;
import model.service.StudentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewMailController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ViewMailController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // �α��� ���� Ȯ��
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

        MailManager mailManager = MailManager.getInstance();
        ProfileManager profiletManager = ProfileManager.getInstance();
        Mail mail = null;

        try {
            //�޼��� ������
            int ch_id = Integer.parseInt(request.getParameter("ch_id"));

            log.debug("ch_id :: " + request.getParameter("ch_id"));

            mail = mailManager.findMail(ch_id);
            Profile receiver_pro = profiletManager.findProfile(mail.getReceiver());
            Profile sender_pro = profiletManager.findProfile(mail.getSender());
            String receiver = receiver_pro.getName();
            String sender = sender_pro.getName();

            request.setAttribute("mail", mail);        // ���� ���� ����
            request.setAttribute("receiver", receiver);
            request.setAttribute("sender", sender);

            log.debug("Message :: " + mail.getMessage());

            //���� �޼���
            if (Integer.parseInt(request.getParameter("flag")) == 0) {
                return "/mail/receive/detail.jsp";
            }
            // ���� �޼���
            else {
                return "/mail/send/detail.jsp";
            }

        } catch (Exception e) {
            return "/mail/receive/receiveList.jsp";
        }
    }



}
