package com.ld.pattern.strategypattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Client {

    public static void main(String[] args) {

        Context context = new Context(new Bluepen());

        context.excuteDraw(100, 10, 21);
        log.info("dsadsadsa");
    }
}
