package com.kh.mini_sample.vo;

import lombok.Data;

@Data
public class MailVO {
    private String receiver; // 받는사람
    private String title; // 제목
    private String content; // 내용
}
