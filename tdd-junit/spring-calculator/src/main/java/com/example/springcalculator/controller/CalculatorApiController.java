package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.dto.Req;
import com.example.springcalculator.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    // Calculator 가 의존성 주입을 받는 ICalculator 인터페이스를 상속한 Bean 이 한 가지밖에 없으므로
    // 자동으로  의존성 주입이 이뤄진다.
    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        return calculator.sum(x, y);
    }
    @PostMapping("/minus")
    public Res minus(@RequestBody Req req){

        int result = calculator.minus(req.getX(),req.getY());
        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());

        return res;
    }


}
