package com.kh.mini_sample.dao;


import com.kh.mini_sample.common.Common;
import com.kh.mini_sample.vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    // 회원 가입 여부 확인
    public boolean regMemberCheck(String USER_ID) {
        boolean isNotReg = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS WHERE USER_ID = " + "'" + USER_ID + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) isNotReg = false;
            else isNotReg = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
    }


    // 로그인 체크
    public boolean loginCheck(String id, String pwd) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM USERS WHERE USER_ID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) { // 읽은 데이타가 있으면 true
                String sqlId = rs.getString("USER_ID"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("USER_PWD");

                if (id.equals(sqlId) && pwd.equals(sqlPwd)) {
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                    return true;
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // 회원정보 조회
    public List<MemberVO> memberSelect() {
        List<MemberVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USERS";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String USER_ID = rs.getString("USER_ID");
                String USER_PWD = rs.getString("USER_PWD");
                String USER_NAME = rs.getString("USER_NAME");
                String setUSER_EMAIL = rs.getString("setUSER_EMAIL");
                Date JOIN_DATE = rs.getDate("JOIN_DATE");
                MemberVO vo = new MemberVO();
                vo.setUSER_ID(USER_ID);
                vo.setUSER_PWD(USER_PWD);
                vo.setUSER_NAME(USER_NAME);
                vo.setUSER_EMAIL(setUSER_EMAIL);
                vo.setJOIN_DATE(JOIN_DATE);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    // 회원가입

    public boolean memberRegister(String USER_ID,  String USER_PWD, String USER_PWDCH, String USER_NAME,
                                  String USER_EMAIL, String USER_PHONE, String USER_ADDR, String RRN) {
        System.out.println("들어왔음      USER_ID: " + USER_ID + " USER_PWD : " + USER_PWD + " USER_PWD : " + USER_PWDCH +
                " USER_NAME : " + USER_NAME + " USER_EMAIL : " + USER_EMAIL + " RRN : " + RRN + " USER_PHONE : " + USER_PHONE + " USER_ADDR : " + USER_ADDR);

        int result = 0;
        String sql = "INSERT INTO USERS(USER_ID,  USER_PWD, USER_PWDCH, USER_NAME, USER_EMAIL, USER_PHONE, RRN, USER_ADDR, JOIN_DATE) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, USER_ID);
            pstmt.setString(2, USER_PWD);
            pstmt.setString(3, USER_PWDCH);
            pstmt.setString(4, USER_NAME);
            pstmt.setString(5, USER_EMAIL);
            pstmt.setString(6, USER_PHONE);
            pstmt.setString(7, RRN);
            pstmt.setString(8, USER_ADDR);

            System.out.println("Dao" + USER_ADDR);

            result = pstmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }



    // 회원 탈퇴
    public boolean secCheck(String id, String pwd) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM USERS WHERE USER_ID = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);
            System.out.println("ID 탈퇴: " + id);
            System.out.println("PWD 탈퇴: " + pwd);

            while (rs.next()) { // 읽은 데이타가 있으면 true
                String sqlId = rs.getString("USER_ID"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("USER_PWD");
                System.out.println("ID : " + sqlId);
                System.out.println("PWD : " + sqlPwd);
                if (id.equals(sqlId) && pwd.equals(sqlPwd)) {
                    System.out.println("로그인 성공");
                    String sqlSec = "DELETE FROM USERS WHERE USER_ID=" + "'" + id + "'";
                    rs = stmt.executeQuery(sqlSec);
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                    return true;
                }
            }
            System.out.println("로그인 성공했냐");
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}