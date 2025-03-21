package model.dao;

import model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    private  JDBCUtil jdbcUtil = null;

    public StudentDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public int remove(String studentId) throws SQLException {
        String sql = "DELETE FROM Student WHERE email=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {studentId});

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


    public int create(Student student) throws SQLException {
        String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?, STUDENTSEQ.nextval)";
        Object[] param = new Object[] {student.getName(), student.getEmail(), student.getPassword(), student.getGender(), student.getC_id()};
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

    public Student findStudent(String email) throws SQLException {
        String sql = "SELECT s_id, s_name, email, password, c_id, gender "
                + "FROM Student s "
                + "WHERE email=? ";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {email});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                Student student = new Student (
                        rs.getInt("s_id"),
                        email,
                        rs.getString("password"),
                        rs.getString("s_name"),
                        rs.getInt("c_id"),
                        rs.getInt("gender"));
                    return student;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    public boolean existingStudent(Object studentId) throws SQLException {
        String sql = "SELECT count(*) FROM Student WHERE email=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {studentId});

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
}
