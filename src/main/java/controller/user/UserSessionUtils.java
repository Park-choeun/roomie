package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";

    /* ���� �α����� ������� ID�� ���� */
    public static Object getLoginUserId(HttpSession session) {
        Object userId = session.getAttribute(USER_SESSION_KEY);

        if (userId.getClass().getSimpleName().equals("String")) {
            userId = (String)userId;
        } else {
            userId = (int)userId;
        }

        return userId;
    }

    /* �α����� ���������� �˻� */
    public static boolean hasLogined(HttpSession session) {
        Object userId = getLoginUserId(session);

        if (userId.getClass().getSimpleName().equals("String")) {
            if (getLoginUserId(session) != null) {
                return true;
            }
        } else {
            if ((int)getLoginUserId(session) != -1) {
                return true;
            }
        }
        return false;
    }

    /* ���� �α����� ������� ID�� userId���� �˻� */
    public static boolean isLoginUser(int userId, HttpSession session) {
        int loginUser = -1;
        loginUser = (int)getLoginUserId(session);
        if (loginUser == -1) {
            return false;
        }

        if (loginUser == userId) {
            return true;
        }
        return false;
    }

    public static boolean isLoginUser(String userId, HttpSession session) {
        String loginUser = (String)getLoginUserId(session);
        if (loginUser == null) {
            return false;
        }

        if (loginUser == userId) {
            return true;
        }
        return false;
    }
}
