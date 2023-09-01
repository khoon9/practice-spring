package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("---------");

        // 변환 관계
        // Text JSON -> object
        // Object -> Text JSON

        // 요청 및 응답
        // controller request json(text) -> object
        // response object -> json(text)

        ObjectMapper objectMapper = new ObjectMapper();

        // object -> text
        // object mapper 는 변환을 위해 dto class의 get method 를 활용함
        // 단, dto class의 기본적인 getter와 setter 이외의 method 중에 get~으로 시작하는 method가 있으면 안 된다.
        User user = new User("steve", 10, "010-1111-2222");
        String text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text -> object
        // object mapper 는 default 생성자를 필요로 한다. (즉, new User()가 가능해야함)
        User objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }

}
