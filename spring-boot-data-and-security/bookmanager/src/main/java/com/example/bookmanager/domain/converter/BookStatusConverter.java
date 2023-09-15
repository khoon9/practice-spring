package com.example.bookmanager.domain.converter;

// 인터페이스를 하나 작성

import com.example.bookmanager.repository.dto.BookStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
// AttributeConverter<X, Y> 에서 X 는 데이터베이스 entity 을 말하며, Y 는 데이터베이스의 컬럼 Type
// 또한 제너릭 타입으로는 int 와 같은 형식은 불가. 레버드 가능.

// 컨버터에서 nullpoint error 발생을 예방해야 한다.
// DB 에 저장하는 Type 은 Integer 이고, 서비스 내에서는 EntityAttribute 으로 BookStatus 존재한다.
@Converter
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    // BookStatus 을 받아서 처리
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }
    // 널포인트 처리를 해줬다. not null 이 아닌 dbData 대한 오류 대책이다.
    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
