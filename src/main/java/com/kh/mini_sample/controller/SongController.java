package com.kh.mini_sample.controller;
import com.kh.mini_sample.dao.SongDAO;
import com.kh.mini_sample.vo.SongVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://192.168.110.22:3000")
@RestController
public class SongController {

    @PostMapping("/findsong")
    //POST : 노래찾기
    public ResponseEntity<List<SongVO>> findSong(@RequestBody Map<String, String> titleData){
        String title = titleData.get("title");
        SongDAO sDao = new SongDAO();
        List<SongVO> url = sDao.songCheck(title);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    // 앨범정보
    @GetMapping("/album")
    public ResponseEntity<List<SongVO>> album(@RequestParam String title) {
        System.out.println("title : " + title);
        SongDAO dao = new SongDAO();
        List<SongVO> list = dao.songInfo(title);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
