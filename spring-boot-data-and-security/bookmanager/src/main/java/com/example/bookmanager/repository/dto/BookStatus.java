package com.example.bookmanager.repository.dto;

// 코드상으로 상태를 정의하는 것 보다는 클래스로 정의하는 것이 바람직하다는 이유로 해당 파일이 생성됨

import lombok.Data;

@Data
public class BookStatus {
    private int code;
    private String description;
    public BookStatus(int code){
        this.code = code;
        this.description = parseDescription(code);
    }

    public boolean isDisplay(){
        return code == 200;
    }
    private String parseDescription(int code){
        switch (code){
            case 100:
                return "판매종료";
            case 200:
                return "판매중";
            case 300:
                return "판매보류";
            default:
                return "미지원";
        }
    }

}
