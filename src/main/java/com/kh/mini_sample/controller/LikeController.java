package com.kh.mini_sample.controller;

import com.kh.mini_sample.dao.LikeDAO;
import com.kh.mini_sample.dao.SongDAO;
import com.kh.mini_sample.vo.SongVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://192.168.110.22:3000")
@RestController
public class LikeController {
    // Get : 좋아요 수 많은 순으로 노래조회
    @GetMapping("/songChart")
    public ResponseEntity<List<SongVO>> songChart(@RequestParam String id) {
        System.out.println("id : " + id);
        LikeDAO dao = new LikeDAO();
        List<SongVO> list = dao.songChart(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

