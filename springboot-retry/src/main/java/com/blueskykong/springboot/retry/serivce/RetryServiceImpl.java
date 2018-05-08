package com.blueskykong.springboot.retry.serivce;

import com.blueskykong.springboot.retry.exception.RetryException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Maps;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author keets
 * @data 2018/5/8.
 */
@Service
@Slf4j
public class RetryServiceImpl implements RetryService {

    int i = 1;

    @Override
    @Retryable(include = {RetryException.class}, maxAttempts = 4, backoff = @Backoff(delay = 1000l, multiplier = 1))
    public String retry() {
        log.info("测试retry");
        i++;//生产环境此处应该为调用第三方接口，判断接口返回code
        if (i == 30) {
            return i + "";
        }
        log.info("============" + i);
        RetryException retryException = RetryException.builder().code("9999").message("连接超时").build();
        throw retryException;
    }

    @Override
    public Map testRetry() {
        Map map = new HashMap();
        String s = retry();
        if (s.equals("3")) {
            log.info("成功啦啦啦啦啦啦啦啦");
        } else {
            log.info("失败了");
        }
        map.put("result", s);
        return map;
    }

    @Recover
    public String recover(RetryException e) {
        log.info(e.getMessage());
        return "6";
    }
}
