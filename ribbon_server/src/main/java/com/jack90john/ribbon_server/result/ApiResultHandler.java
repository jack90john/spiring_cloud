package com.jack90john.ribbon_server.result;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 * Designer: jack
 * Date: 2018/11/5
 * Version: 1.0.0
 */

@ControllerAdvice
public class ApiResultHandler<T> implements ResponseBodyAdvice<T> {

    private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] reuqestMethodArray = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    /**
     * 对所有RestController的接口方法进行拦截
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        return Arrays.stream(reuqestMethodArray).anyMatch(method -> method.isAnnotation() && annotatedElement.isAnnotationPresent(method));
    }

    @Override
    public T beforeBodyWrite(T body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ApiResult<T> out;
        ObjectMapper mapper = mapperThreadLocal.get();
        serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (body instanceof ErrorCode) {
            ErrorCode errorCode = (ErrorCode) body;
            out = new ApiResult<T>(){{
                setCode(errorCode.getCode());
                setMessage(errorCode.getMsg());
            }};
        } else {
            out = new ApiResult<>(ErrorCode.SUCCESS, body);
        }
        try {
            //因为是String类型，我们要返回Json字符串，否则SpringBoot框架会转换出错
            return (T) mapper.writeValueAsString(out);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ApiResult<String> nullPointerExceptionHandler(HttpServletRequest request, Exception exception) {
        return handleErrorInfo(ErrorCode.PARAMS_ERROR ,request, exception.getMessage());
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    @ResponseBody
    public ApiResult<String> hystrixRuntimeExceptionHandler(HttpServletRequest request, HystrixRuntimeException exception) {
        Throwable fallbackException = exception.getCause();
        if (fallbackException instanceof TimeoutException) {
            return handleErrorInfo(ErrorCode.TIME_OUT ,request, exception.getMessage());
        }
        return handleErrorInfo(ErrorCode.SERVER_ERROR ,request, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult<String> exceptionHandler(HttpServletRequest request, Exception exception) {
        return handleErrorInfo(ErrorCode.SERVER_ERROR ,request, exception.getMessage());
    }

    private ApiResult<String> handleErrorInfo(ErrorCode errorCode, HttpServletRequest request, String message) {
        ApiResult<String> errorMessage = new ApiResult<>();
        errorMessage.setCode(errorCode.getCode());
        errorMessage.setMessage(errorCode.getMsg());
        errorMessage.setValue(message);
        return errorMessage;
    }
}
