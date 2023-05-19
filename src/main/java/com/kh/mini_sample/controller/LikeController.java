package com.kh.mini_sample.controller;

import com.kh.mini_sample.dao.LikeDAO;
import com.kh.mini_sample.dao.SongDAO;
import com.kh.mini_sample.vo.SongVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000") // 다른 도메인에서 해당 API접근 가능
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
    @PostMapping("/songLike")
    public ResponseEntity<Boolean> songLike(@RequestBody Map<String, String> likeData) {
        String id = likeData.get("id");
        String songId = likeData.get("songId");
        System.out.println("id : " + id);
        System.out.println("songId : " + songId);

        LikeDAO dao = new LikeDAO();
        boolean isTrue = dao.songLikeInsert(id, songId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/songLikeDelete")
    public ResponseEntity<Boolean> songlikeDelete(@RequestBody Map<String, String> likeDelData) {
        String id = likeDelData.get("id");
        String songId = likeDelData.get("songId");
        System.out.println("id : " + id);
        System.out.println("songID : " + songId);

        LikeDAO dao = new LikeDAO();
        boolean isTrue = dao.songlikeDelete(id, songId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}

