package com.example.springioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 해당 어노테이션을 건너건너 들어가면, @Component 어노테이션에 의해 Bean으로 관리됨을 확인할 수 있다.
// @SpringBootApplication은 세부적으로는 @ComponentScan 어노테이션에 의해
// Bean 탐색이 이뤄지는 곳이다.
// @Component -> Bean 리스트에 등재
// 스프링 컨테이너 실행 -> Bean 리스트에 등재된 클래스가 객체화 되어 Bean 으로 관리
// Bean 은 객체로서 필요한 곳에 주입되어 사용된다.
@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

        // DI는 new 로 주입 및 사용하더라도 가능하지만, IOC 을 만족하기 위해선 new 를 없애야함
        // Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        // UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        // Encoder encoder = new Encoder(base64Encoder);
        // Encoder encoder = new Encoder(urlEncoder);

        // 객체들을 모두 Application 이 관리하기에 IOC 라 한다.
        Encoder encoder = context.getBean("base64Encode",Encoder.class);
        String url = "www.naver.com";
        String result = encoder.encode(url);
        System.out.println(result);


    }

    // @SpringApplicationContext 어노테이션을 사용하여 Bean을 가져올 수 있다.
}

// Bean을 주입받을 수 있는 위치는 변수, 생성자, set method 가 있다.
// @Configuration 어노테이션은 한 개의 클래스에서 여러개의 Bean 을 등록하겠다는 의미
@Configuration
class AppConfig{

    // 개발자가 만들더라도 new 를 사용하여서 코드에서 쓰는 것이 아니라, 미리 Bean을 등록하는 것
    // @Bean 어노테이션으로 인해 생성되는 Bean 의 이름 또한 겹치지 않도록 주의한다.
    // 안쪽에서 정의한 Bean이기에 autoWired 되지 않아 노 매치된다. 수동으로 불러와야한다.
    @Bean
    public Encoder base64Encode(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }
    // 또한 메소드를 Bean으로 생성할 때 비록 매개변수가 다르더라도 메소드 이름이 동일하면 안 된다.
    @Bean
    public Encoder urlEncode(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }

}
