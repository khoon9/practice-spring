package com.example.validation.validator;

import com.example.validation.annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// ConstraintValidator<YearMonth, String> 에서, 사용 Validator 와 입력 받을 자료형을 지정
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {
    // 확인해야 될 값.
    private String pattern;


    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // yyyyMM
        try {
            // 여기서 LocalDate 는 dd 를 확인해야 정상적으로 작동하기 때문에 그걸 고려했다.
            // 날짜가 맞지 않으면 예외가 발생하도록 설정
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.pattern));
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
