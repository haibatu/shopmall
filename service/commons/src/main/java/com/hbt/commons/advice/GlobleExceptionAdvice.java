package com.hbt.commons.advice;

import com.hbt.commons.exception.UniteException;
import com.hbt.commons.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobleExceptionAdvice {

    /**
     * 对UniteException异常进行处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = UniteException.class)
    public CommonResponse<String> handlerUniteException(
            HttpServletRequest request,
            UniteException ex){
        CommonResponse<String> response = new CommonResponse<>(
                -1, "uniteException");
        response.setData(ex.getMessage());
        return response;
    }
}
