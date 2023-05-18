package com.kh.mini_sample.vo;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
@Getter
@Setter
public class SongVO {
    private String title;
    private String artist;
    private String song_url;
    private String cover_url;
    private int like_count;
    private int songId;
    private String albumName;
    private String songWrite;
    private String lyrics;
    private int ALBUM_ID;
    private Date Release;
    private String info;
    private String user_id;
}
