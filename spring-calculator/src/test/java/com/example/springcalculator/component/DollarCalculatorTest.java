package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //이거 달면 싹다 import 됨. 밑에 @Import 사용안해도 된다.
//@Import({MarketApi.class, DollarCalculator.class}) //내가 필요한 MarketApi import 시키겠다.
class DollarCalculatorTest {
    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    public void dollarCalculatorTest(){
        Mockito.when(marketApi.connect()).thenReturn(3000);

        int sum = calculator.sum(10,10);
        int minus = calculator.minus(10,10);

        Assertions.assertEquals(60000,sum);
        Assertions.assertEquals(0,minus);
    }
}