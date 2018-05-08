package com.blueskykong.springboot.retry.rest;

import com.blueskykong.springboot.retry.serivce.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author keets
 * @data 2018/5/8.
 */
@RestController
@RequestMapping("retry")
public class RetryController {
    @Autowired
    public RetryService testService;

    @RequestMapping(value = "testRetry", method = {RequestMethod.POST, RequestMethod.GET})
    public Map testRetry() {
        Map map = new HashMap();
        try {
            map = testService.testRetry();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        }
    }
}
