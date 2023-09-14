package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poscodx.mysite.vo.UserVo;

public class UserDao {

    public boolean insert(UserVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = "  insert" + "    into user" + " values (null, ?, ?, ?, ?, now())";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getGender());

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public UserVo findByEmailAndPassword(String email, String password) {
        UserVo result = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select no, name from user where email=? and password=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);

                result = new UserVo();
                result.setNo(no);
                result.setName(name);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public UserVo fidByNo(Long no) {
        UserVo result = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select name, email, password, gender, join_date from user where no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                String name = rs.getString(1);
                String email = rs.getString(2);
                String passwd = rs.getString(3);
                String gender = rs.getString(4);
                String date = rs.getString(5);

                result = new UserVo();
                result.setNo(no);
                result.setName(name);
                result.setEmail(email);
                result.setGender(gender);
                result.setJoinDate(date);
                result.setPassword(passwd);
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, "root", "qpqpqp0614");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패:" + e);
        }

        return conn;
    }

    public boolean updateByNo(UserVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = "";
            System.out.println(vo.getPassword());
            if (vo.getPassword().equals("")) {
                sql = " update user set "
                        + "name = ?, gender = ?, email = ? "
                        + "where no = ?";

                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getGender());
                pstmt.setString(3, vo.getEmail());
                pstmt.setLong(4, vo.getNo());

            } else {
                sql = "update user set "
                        + "name = ?, gender = ?, password = ?, email = ? "
                        + "where no =?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getGender());
                pstmt.setString(3, vo.getPassword());
                pstmt.setString(4, vo.getEmail());
                pstmt.setLong(5, vo.getNo());

            }

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
