package com.kh.mini_sample.vo;

import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
public class MemberVO {
    private String USER_ID;
    private String USER_NAME;
    private String USER_PWD;
    private String USER_PWDCH;
    private String USER_ADDR;
    private String USER_PHONE;
    private String USER_EMAIL;
    private String RRN;
    private Date JOIN_DATE;
}


