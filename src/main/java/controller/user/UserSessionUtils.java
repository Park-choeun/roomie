package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";

    /* ���� �α����� ������� ID�� ���� */
    public static int getLoginUserId(HttpSession session) {
        int userId = (int)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != -1) {
            return true;
        }
        return false;
    }

    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginUser(int userId, HttpSession session) {
        int loginUser = -1;
        loginUser = getLoginUserId(session);
        if (loginUser == -1) {
            return false;
        }

        if (loginUser == userId) {
            return true;
        }
        return false;
    }
}
