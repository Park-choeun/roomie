package model.dao;

import model.MyRoomie;
import model.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyRoomieDAO {

    private JDBCUtil jdbcUtil = null;

    public MyRoomieDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public int create(MyRoomie roomie) throws SQLException {
        String sql = "INSERT INTO myroomie VALUES(?, MYROOMIESEQ.nextval, ?, 0)";
        Object[] param = new Object[] {roomie.getS_id(), roomie.getRoomie_id()};
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }

    public int update(MyRoomie roomie) throws SQLException {
        String sql1 = "UPDATE myroomie SET roomie_check=1 WHERE s_id=? AND roomie_id=?";
        jdbcUtil.setSqlAndParameters(sql1, new Object[] {roomie.getRoomie_id(), roomie.getS_id()});

        try {
            int result = jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        String sql2 = "INSERT INTO myroomie VALUES(?, MYROOMIESEQ.nextval, ?, 1)";
        Object[] param2 = new Object[] {roomie.getS_id(), roomie.getRoomie_id()};
        jdbcUtil.setSqlAndParameters(sql2, param2);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

    public int remove(MyRoomie roomie) throws SQLException {
        String sql = "DELETE FROM myroomie WHERE s_id=? AND roomie_id=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {roomie.getS_id(), roomie.getRoomie_id()});

        try {
            int result = jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        String sql1 = "DELETE FROM myroomie WHERE s_id=? AND roomie_id=?";
        jdbcUtil.setSqlAndParameters(sql1, new Object[] {roomie.getRoomie_id(), roomie.getS_id()});

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

    public boolean isPicked(int s_id, int roomie_id) {
        String sql = "SELECT count(*) FROM myroomie WHERE s_id=? AND roomie_id=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {s_id, roomie_id});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    public int isChecked(int s_id, int roomie_id) throws SQLException {
        String sql = "SELECT roomie_check "
                + "FROM myroomie WHERE s_id=? AND roomie_id=?";

        jdbcUtil.setSqlAndParameters(sql, new Object[] {s_id, roomie_id});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int check = rs.getInt(1);
                System.out.println("flag 확인 ::" + check);

                return check;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return 0;
    }

    // 매칭된 루미 리스트 찾기
    public List<Profile> findMyRoomieList(int s_id) throws SQLException {
        String sql = "SELECT roomie_id, activation, name, pr_img, age, sleep_habit, lifestyle, smoking, grade, major, mbti, cleaning, indoor_eating, sharing, habitude "
                    + "FROM myroomie r JOIN profile p ON r.roomie_id=p.s_id "
                    + "WHERE r.roomie_check=1 AND r.s_id=? "
                    + "ORDER BY r_id DESC";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {s_id});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Profile> myRoomieList = new ArrayList<Profile>();
            while (rs.next()) {
                Profile roomie = new Profile(
                        rs.getInt("roomie_id"),
                        rs.getInt("activation"),
                        rs.getString("name"),
                        rs.getInt("pr_img"),
                        rs.getInt("age"),
                        rs.getInt("sleep_habit"),
                        rs.getInt("lifestyle"),
                        rs.getInt("smoking"),
                        rs.getInt("grade"),
                        rs.getString("major"),
                        rs.getInt("mbti"),
                        rs.getInt("cleaning"),
                        rs.getInt("indoor_eating"),
                        rs.getInt("sharing"),
                        rs.getInt("habitude"));
                myRoomieList.add(roomie);
            }
            return myRoomieList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    // 매칭 신청 대기 루미 리스트 찾기
    public List<Profile> findWaitRoomieList(int s_id) throws SQLException {
        String sql = "SELECT roomie_id, activation, name, pr_img, age, sleep_habit, lifestyle, smoking, grade, major, mbti, cleaning, indoor_eating, sharing, habitude "
                + "FROM myroomie r JOIN profile p ON r.roomie_id=p.s_id "
                + "WHERE r.roomie_check=0 AND r.s_id=? "
                + "ORDER BY r_id DESC";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {s_id});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Profile> myRoomieList = new ArrayList<Profile>();
            while (rs.next()) {
                Profile roomie = new Profile(
                        rs.getInt("roomie_id"),
                        rs.getInt("activation"),
                        rs.getString("name"),
                        rs.getInt("pr_img"),
                        rs.getInt("age"),
                        rs.getInt("sleep_habit"),
                        rs.getInt("lifestyle"),
                        rs.getInt("smoking"),
                        rs.getInt("grade"),
                        rs.getString("major"),
                        rs.getInt("mbti"),
                        rs.getInt("cleaning"),
                        rs.getInt("indoor_eating"),
                        rs.getInt("sharing"),
                        rs.getInt("habitude"));
                myRoomieList.add(roomie);
            }
            return myRoomieList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    // 매칭 신청 온 목록 리스트 찾기
    public List<Profile> findRequestRoomieList(int s_id) throws SQLException {
        String sql = "SELECT r.s_id, activation, name, pr_img, age, sleep_habit, lifestyle, smoking, grade, major, mbti, cleaning, indoor_eating, sharing, habitude "
                + "FROM myroomie r JOIN profile p ON r.s_id=p.s_id "
                + "WHERE r.roomie_check=0 AND r.roomie_id=? "
                + "ORDER BY r_id DESC";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {s_id});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Profile> requestRoomieList = new ArrayList<Profile>();
            while (rs.next()) {
                Profile roomie = new Profile(
                        rs.getInt("s_id"),
                        rs.getInt("activation"),
                        rs.getString("name"),
                        rs.getInt("pr_img"),
                        rs.getInt("age"),
                        rs.getInt("sleep_habit"),
                        rs.getInt("lifestyle"),
                        rs.getInt("smoking"),
                        rs.getInt("grade"),
                        rs.getString("major"),
                        rs.getInt("mbti"),
                        rs.getInt("cleaning"),
                        rs.getInt("indoor_eating"),
                        rs.getInt("sharing"),
                        rs.getInt("habitude"));
                requestRoomieList.add(roomie);
            }
            return requestRoomieList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
