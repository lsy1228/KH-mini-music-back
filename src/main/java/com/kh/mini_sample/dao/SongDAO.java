package com.kh.mini_sample.dao;

import com.kh.mini_sample.common.Common;
import com.kh.mini_sample.vo.MemberVO;
import com.kh.mini_sample.vo.SongVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    // 노래정보 조회
    public List<SongVO> songSelect(String id) {
        List<SongVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM SONG";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int song_id = rs.getInt("SONG_ID");
                String title = rs.getString("TITLE");
                String artist = rs.getString("ARTIST");
                String song_url = rs.getString("SONG_URL");
                String cover_url = rs.getString("COVER_URL");

                SongVO vo = new SongVO();
                vo.setSongId(song_id);
                vo.setTitle(title);
                vo.setArtist(artist);
                vo.setSong_url(song_url);
                vo.setCover_url(cover_url);
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


    // 특정 노래 검색
    //노래찾기
    public List<SongVO> songCheck(String title) {
        List<SongVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            System.out.println("송DAO_TRY : " + title);
            String sql = "SELECT * FROM SONG WHERE TITLE  Like " + "'%" + title + "%'OR ARTIST=" + "'%" + title + "%' ORDER BY TITLE";
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) { // 읽은 데이타가 있으면 true
                int songId = rs.getInt("SONG_ID");
                String songTitle = rs.getString("TITLE");
                String albumName = rs.getString("ALBUM_NAME");
                String songArtist = rs.getString("ARTIST");
                String lyric = rs.getString("LYRICS");
                String sqlUrl = rs.getString("SONG_URL"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String coverUrl = rs.getString("COVER_URL");
                SongVO vo = new SongVO();
                vo.setSongId(songId);
                vo.setTitle(songTitle);
                vo.setAlbumName(albumName);
                vo.setArtist(songArtist);
                vo.setLyrics(lyric);
                vo.setSong_url(sqlUrl);
                vo.setCover_url(coverUrl);
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

    // 노래정보
    public List<SongVO> songInfo(String title) {
        List<SongVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = " SELECT " + title + " FROM ALBUM ";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                SongVO vo = new SongVO();
                int album_id = rs.getInt("ALBUM_ID");
                String songTitle = rs.getString("TITLE");
                String songArtist = rs.getString("ARTIST");
                String albumName = rs.getString("ALBUM_NAME");
                String coverUrl = rs.getString("COVER_URL");
                Date release = rs.getDate("RELEASE");
                String info = rs.getString("INFO");

                vo.setALBUM_ID(album_id);
                vo.setTitle(songTitle);
                vo.setArtist(songArtist);
                vo.setCover_url(coverUrl);
                vo.setRelease(release);
                vo.setInfo(info);
                vo.setAlbumName(albumName);
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
}
