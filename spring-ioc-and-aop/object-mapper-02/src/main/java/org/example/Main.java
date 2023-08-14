package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.dto.Car;
import org.example.dto.User;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setName("홀길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");
        List<Car> carList = Arrays.asList(car1,car2);
        user.setCars(carList);

        //System.out.println(user);
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        // 여기서 cars는 List 로서 배열 노드이다. 따라서 ArrayNode 사용
        JsonNode cars = jsonNode.get("cars");
        // 강제 형변환으로, 강제 파싱을 해서 배열 노드 그릇에 담는다.
        ArrayNode arrayNode = (ArrayNode) cars;
        // 맵을 원하는 객체로 바꾸어 원하는 클래스 그릇으로 담는다.
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        // 다음과 같이, 배열을 json 에서 추출하여 수정을 가하는 모습을 볼 수 있다.
        boolean first = true;
        for (Car car : _cars){
            if(first){
                car.setName("A4");
                car.setCarNumber("33가 3333");
                car.setType("new type");
                first = false;
            }
        }
        System.out.println(_cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);

        System.out.println(objectNode.toPrettyString());
    }
}