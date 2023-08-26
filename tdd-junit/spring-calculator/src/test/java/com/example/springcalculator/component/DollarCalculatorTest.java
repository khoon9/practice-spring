// 일반적으로, 동일한
package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class DollarCalculatorTest {
    // java 의 @Mock 와 기능은 동일. Bean 으로 관리되기에 @MockBean 사용
    // 즉, Mockito 기능을 사용하기 위해 기입.
    @MockBean
    private MarketApi marketApi;

    // 스프링 컨테이너에 있는 Bean 을 가져온다. 의존성 주입.
    @Autowired
    private Calculator Calculator;

    @Test
    public void dollarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000);

        int sum = Calculator.sum(10,10);
        int minus = Calculator.minus(10,10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0, minus);
    }

}
