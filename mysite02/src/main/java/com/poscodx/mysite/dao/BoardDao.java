package com.poscodx.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.mysite.vo.BoardVo;

import static java.time.LocalTime.now;

public class BoardDao {

    public List<BoardVo> findAll(String limit, String input) {
        List<BoardVo> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "select b.no, b.title, b.hit,  b.contents, a.name, date_format(reg_date, '%Y/%m/%d %H:%i:%s'),"
                    + " b.user_no, b.depth" + " from user a, board b"
                    + " where a.no = b.user_no"
                    + "  and b.title like '%" + input + "%'"
                    + " order by b.group_no desc,  b.order_no asc, b.depth asc"
                    + limit;

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                int hit = rs.getInt(3);
                String content = rs.getString(4);
                String name = rs.getString(5);
                String date = rs.getString(6);
                Long user_no = rs.getLong(7);
                int dep = rs.getInt(8);

                BoardVo vo = new BoardVo();
                vo.setNo(no);
                vo.setUserName(name);
                vo.setTitle(title);
                vo.setContents(content);
                vo.setHit(hit);
                vo.setRegDate(date);
                vo.setUserNo(user_no);
                vo.setDepth(dep);

                list.add(vo);
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

        return list;
    }

    //	public int grou
    public boolean insert(BoardVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = " insert" + " into board" + " values (null, ?, ?, ?, now(), ? , ?, ?,? )";
            pstmt = conn.prepareStatement(sql);
            int group_no = maxGroupno();
            pstmt.setString(1, vo.getTitle()); //title
            pstmt.setString(2, vo.getContents()); // content
            pstmt.setInt(3, 0); // hit
            pstmt.setInt(4, group_no); // group_no
            pstmt.setInt(5, 1); // order no
            pstmt.setInt(6, 1); // depth
            pstmt.setInt(7, Math.toIntExact(vo.getUserNo())); // depth
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

    private int maxGroupno() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int num = 1;
        String no = null;
        try {
            conn = getConnection();

            String sql = "select max(group_no)+1 from board";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next())
                no = rs.getString(1);
            if (no != null) {
                num = Integer.parseInt(no);
            } else {
                num = 1;
            }

            return num;

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

        return 1;
    }

    public boolean delete(BoardVo vo) {
        boolean result = false;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = " delete" + "   from board" + "  where no=? and user_no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, vo.getNo());
            pstmt.setLong(2, vo.getUserNo());

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

    public boolean setHit(Long no) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = " update board set " + "hit = hit+1 " + "where no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
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

    public BoardVo view(Long no) {
        BoardVo vo = new BoardVo();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();

            String sql = "select b.user_no, b.title, b.contents, b.hit, b.group_no, b.order_no, b.depth, a.name"
                    + " from user a, board b" + " where a.no = b.user_no and b.no = ?;";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long user_no = rs.getLong(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                int hit = rs.getInt(4);
                int group_no = rs.getInt(5);
                int order_no = rs.getInt(6);
                int depth = rs.getInt(7);
                String name = rs.getString(8);

                vo.setNo(no);
                vo.setUserNo(user_no);
                vo.setTitle(title);
                vo.setContents(content);
                vo.setHit(hit);
                vo.setGroupNo(group_no);
                vo.setOrderNo(order_no);
                vo.setDepth(depth);
                vo.setUserName(name);
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

        return vo;
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

    public BoardVo updateSerach(Long no) {
        BoardVo result = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select title, contents from board where no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                String tilte = rs.getString(1);
                String contents = rs.getString(2);

                result = new BoardVo();
                result.setTitle(tilte);
                result.setContents(contents);
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

    public boolean update(BoardVo vo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = " update board set " + "title = ?, contents = ? " + "where no = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setLong(3, vo.getNo());

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (

                SQLException e) {
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

    public BoardVo findByNO(Long no) {
        BoardVo result = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select group_no, order_no, depth from board where no=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            if (rs.next()) {

                int group_no = rs.getInt(1);
                int order_no = rs.getInt(2);
                int depth = rs.getInt(3);

                result = new BoardVo();
                result.setGroupNo(group_no);
                result.setOrderNo(order_no);
                result.setDepth(depth);

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

    /**
     * 답글
     *
     * @param vo
     * @return
     */
    public boolean comment(BoardVo vo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        updateONO(vo);
        try {
            conn = getConnection();
            String sql = " insert" + " into board" + " values (null, ?, ?, ?, now(), ? , ?, ?,? )";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getTitle()); //title
            pstmt.setString(2, vo.getContents()); // content
            pstmt.setInt(3, 0); // hit
            pstmt.setInt(4, vo.getGroupNo()); // group_no
            pstmt.setInt(5, vo.getOrderNo() + 1); // order no
            pstmt.setInt(6, vo.getDepth() + 1); // depth
            pstmt.setInt(7, Math.toIntExact(vo.getUserNo())); // depth

            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (

                SQLException e) {
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

    public boolean updateONO(BoardVo vo) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            String sql = " update board set " + "order_no = order_no + 1" + " where order_no > ?" + " and group_no = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getOrderNo());
            pstmt.setInt(2, vo.getGroupNo());
            System.out.println("[update]" + vo.toString());
            int count = pstmt.executeUpdate();
            result = count == 1;

        } catch (

                SQLException e) {
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

    public int CountList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int num = 1;
        String no = null;
        try {
            conn = getConnection();

            String sql = "select count(*) from board";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            if (rs.next())
                no = rs.getString(1);
            if (no != null) {
                num = Integer.parseInt(no);
            } else {
                num = 1;
            }
            return num;

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

        return 1;
    }
}
