package com.soapsnake.pattern.structurals.strategypattern.example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Client {

    public static void main(String[] args) {

        Context context = new Context(new Bluepen());
        context.excuteDraw(100, 10, 21);
        log.info("dsadsadsa");


        Context context1 = new Context(new Redpen());
        context1.excuteDraw(80, 20, 30);


        Context context2 = new Context(new Yellowpen());
        context2.excuteDraw(300, 40, 100);


    }
}
