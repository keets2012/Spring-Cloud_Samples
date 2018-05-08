package com.blueskykong.springboot.retry.rest;

import com.blueskykong.springboot.retry.serivce.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author keets
 * @data 2018/5/8.
 */
@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PayService payService;

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam int num) throws Exception {
        int remainingnum = payService.minGoodsnum(num == 0 ? 1 : num);
        logger.info("剩余的数量===" + remainingnum);
        return "库库存成功";
    }

}

