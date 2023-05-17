package com.kh.mini_sample.controller;


import com.kh.mini_sample.dao.MemberDAO;
import com.kh.mini_sample.sevice.MailService;
import com.kh.mini_sample.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://192.168.110.22:3000") // 다른 도메인에서 해당 API접근 가능
@RestController
@RequiredArgsConstructor // final로 선언된 필드나, @NonNull인 필드값만을 파라미터로 받는 생성자를 생성

public class MemberController {

    // GET : 회원 조회
    @GetMapping("/member")
    public ResponseEntity<List<MemberVO>> memberList(@RequestParam String USER_ID) {
        System.out.println("ID : " + USER_ID);
        MemberDAO dao = new MemberDAO();
        List<MemberVO> list = dao.memberSelect();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // POST : 로그인
    @PostMapping("/login") // HTTP POST 요청으로 /login 주소에 접근하면 memberLogin() 메소드가 실행됨
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        // HTTP 요청 바디에 담긴 JSON 객체를 Map<String, String>으로 변환하여 받아옴
        String id = loginData.get("id");
        String pwd = loginData.get("pwd");
        // 이 객체는 "id"와 "pwd"라는 key를 가지고 옴
        System.out.println("ID : " + id);
        System.out.println("PWD : " + pwd);
        MemberDAO dao = new MemberDAO();
        boolean result = dao.loginCheck(id, pwd);
        //MemberDAO 객체를 생성하여, loginCheck() 메소드로 id와 pwd를 인자로 넘겨주어 로그인 성공 여부를 반환
        return new ResponseEntity<>(result, HttpStatus.OK);
        // ResponseEntity 객체를 이용해 반환값과 HTTP 응답 코드를 설정하여 클라이언트에게 응답함
    }

    // GET : 가입 여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        MemberDAO dao = new MemberDAO();
        System.out.println("id : " + id);
        boolean isTrue = dao.regMemberCheck(id);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }


    @PostMapping("/sec")
    //POST : 회원탈퇴
    public ResponseEntity<Boolean> memberLSec(@RequestBody Map<String, String> secData){
        String id = secData.get("id"); //키 값이 입력되어 value 값을 출력 할 수 있다.
        String pwd = secData.get("pwd");
        System.out.println("ID 탈퇴: " + id);
        System.out.println("pwd 탈퇴: " + pwd);
        MemberDAO dao = new MemberDAO();
        boolean result = dao.secCheck(id,pwd);
        //  DAO 객체의 secCheck() 메소드를 호출하여 입력된 아이디와 비밀번호가 일치하는지 검증
        return new ResponseEntity<>(result, HttpStatus.OK);
        // 결과는 boolean 값으로 반환되며, HttpStatus는 OK로 설정
    }


    // POST : 회원 가입
    @PostMapping("/new") // /new"라는 URL로 POST 요청이 들어올 때 이 메소드를 실행하도록 함
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        //  HTTP 요청 바디에 담긴 JSON 데이터를 자바 객체로 매핑하는 역할을 함
        // 요청 바디의 내용은 Map<String, String> 타입의 regData 변수에 저장
        String getId = regData.get("USER_ID");
        String getPwd = regData.get("USER_PWD");
        String getPwdCh = regData.get("USER_PWDCH");
        String getName = regData.get("USER_NAME");
        String getMail = regData.get("USER_EMAIL");
        String getPhone = regData.get("USER_PHONE");
        String getAddr = regData.get("USER_ADDR");
        String getRrn = regData.get("RRN");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberRegister(getId, getPwd, getPwdCh,getName, getMail, getPhone, getAddr, getRrn);
        // DAO 객체의 memberRegister 메소드를 호출하여 회원 가입을 처리, 이후 결과를 isTrue 변수에 저장
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
        // isTrue 값을 JSON 형태로 응답, HTTP 응답 상태 코드는 HttpStatus.OK로 설정
    }



    private final MailService mailService;
    @GetMapping("/mail") // getMapping어노테이션을 사용해 /mail요청을 받음
    public  ResponseEntity<String> MailSend(@RequestParam String mail){
        // 파라미터로 넘어온 이메일 주소를 받음
        System.out.println("메일 인증 호출 : " + mail);
        int number = mailService.sendMail(mail); // 랜덤한 숫자를 생성
        String num = "" + number; // number를 문자열로 형 변환

        return new ResponseEntity<>(num, HttpStatus.OK);
        // ResponseEntity 클라이언트에게 응답을 보낼 때 사용하는 객체
    }


    // 메일인증코드 확인
    @PostMapping("/verify") // 주소창에 인증코드가 뜨지 않기 위해 post 방식 사용
    public ResponseEntity<Boolean> verifyCode(@RequestBody Map<String, String> mailData) {
        String mail = mailData.get("mail"); //키 값이 입력되어 value 값을 출력 할 수 있다.
        int code = Integer.parseInt(mailData.get("code"));
        System.out.println("메일 : " + mail + ", 코드" + code);
        boolean isTrue = mailService.verifyCode(mail, code);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

}
