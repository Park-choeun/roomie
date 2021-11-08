package model.dao;

import model.College;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegeDAO {
    private  JDBCUtil jdbcUtil = null;

    public CollegeDAO() {
        jdbcUtil = new JDBCUtil();
    }

    public List<College> findCollegeList() throws SQLException {
        String sql = "SELECT c_id, c_name "
                + "FROM college "
                + "ORDER BY c_name";
        jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����

        try {
            ResultSet rs = jdbcUtil.executeQuery();			// query ����
            List<College> collegeList = new ArrayList<College>();	// User���� ����Ʈ ����
            while (rs.next()) {
                College college = new College(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
                        rs.getInt("c_id"),
                        rs.getString("c_name"));
                collegeList.add(college);				// List�� User ��ü ����
            }
            return collegeList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();		// resource ��ȯ
        }
        return null;
    }
}
