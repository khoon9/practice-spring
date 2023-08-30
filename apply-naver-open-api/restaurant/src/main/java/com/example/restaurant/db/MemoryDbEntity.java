package com.example.restaurant.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 모든 데이터가 이 클래스를 상속. 즉, index 라는 key 를 동일하게 갖게한다.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryDbEntity {

    protected Integer index;

}
