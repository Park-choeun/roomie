package controller.extra;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Mail;
import model.service.MailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewMailController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // �α��� ���� Ȯ��
        if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/student/login";		// login form ��û���� redirect
        }

        /*int s_id = (int)UserSessionUtils.getLoginUserId(request.getSession());
        request.setAttribute("s_id", s_id);
        return "/chat/sendList.jsp";*/

        MailManager mailManager = MailManager.getInstance();
        Mail mail = null;

        try {
            //���� �޼��� ������
            int ch_id = Integer.parseInt(request.getParameter("ch_id"));
            //log.debug("s_idȮ��: " + s_id + " ch_idȮ��: " + ch_id);

            mail = mailManager.findMail(ch_id);

            request.setAttribute("mail", mail);		// ����� ���� ����

            return "/mail/receiveList/detail.jsp";

        } catch (Exception e) {
            return "/mail/receiveList.jsp";
        }
    }



}
