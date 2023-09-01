package com.example.springioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class Encoder {
    // 의존성 주입에 의해 외부에서 받아들인 의존성을 담을 그릇을 생성했다.
    private IEncoder iEncoder;
    // 외부에서 주입된 의존성을 그릇에 담았다.
    // 여기서 IEncoder 로 가져올 수 있는 Component 등재 클래스가 2가지이기에 하나를 지정해줘야 한다.
    // ex) @Qualifier("base74Encoder") IEncoder iEncoder
    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }
    // 해당 의존성이 공유한 인터페이스의 메소드를 호출한다.

    public void setiEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
