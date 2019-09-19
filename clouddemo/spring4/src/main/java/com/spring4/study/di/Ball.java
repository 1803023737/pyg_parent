package com.spring4.study.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ball {

    @Autowired
    //@Qualifier(value = "basketBall")
    private BasketBall basketBall;


    //@Resource
    //private BasketBall basketBall;

    //@Inject
    //private BasketBall basketBall;

    public BasketBall getBasketBall() {
        return basketBall;
    }

    //public void setBasketBall(BasketBall basketBall) {
    //    this.basketBall = basketBall;
    //}
}
