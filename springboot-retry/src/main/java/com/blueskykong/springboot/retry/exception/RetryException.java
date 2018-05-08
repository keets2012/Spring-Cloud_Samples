package com.blueskykong.springboot.retry.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * @author keets
 * @data 2018/5/8.
 */
@Builder
@Getter
public class RetryException extends RuntimeException {
    private String code;
    private String message;
}
