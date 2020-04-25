package com.darben.controller;

import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @description: exec-sub-appweb 模块fallback
 * @author: darben
 * @create: 2020-04-09 14:06
 */
@RestController
public class AppFallBackController {

    @RequestMapping(value ="/app/fallback")
    public Mono<String> fallback(){
        return Mono.just("service error, jump fallback");
    }

}
