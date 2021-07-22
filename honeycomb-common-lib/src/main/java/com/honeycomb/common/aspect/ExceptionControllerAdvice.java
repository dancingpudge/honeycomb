package com.honeycomb.common.aspect;

import com.honeycomb.common.annotation.EnableHoneycombApiExp;
import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.base.exception.ApiException;
import com.honeycomb.common.base.exception.HoneycombExceptionHandler;
import com.honeycomb.common.util.ApiResultUtil;
import com.honeycomb.common.util.StackTraceLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;


/**
 * @Author LiuH
 * @Date 2020/7/29
 */
@ControllerAdvice
@ConditionalOnBean(annotation = EnableHoneycombApiExp.class)
public class ExceptionControllerAdvice {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private static Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class.getName());

    @ResponseBody
    @ExceptionHandler({Exception.class})
    protected StandardResultVO handleInvalidRequest(Exception e) {
        try {
            //根据接口类型返回相应的所有bean
            Map<String, HoneycombExceptionHandler> map = webApplicationContext.getBeansOfType(HoneycombExceptionHandler.class);
            map.entrySet().forEach(eh -> eh.getValue().handle(e));
        } catch (Exception e1) {
            log.info(e1.getMessage());
        }
        if (e instanceof ApiException) {
            ApiResultUtil apiResultUtil = ApiResultUtil.getInstance();
            String message = apiResultUtil.getErrorInfo(((ApiException) e).getErrCode());
            if (StringUtils.isEmpty(message)) {
                message = ((ApiException) e).getErrMsg();
            }
            return StandardResultVO.errorResult(((ApiException) e).getErrCode(), message);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = ex.getBindingResult();
            StringBuilder errMsg = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
            for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
                if (i > 0) {
                    errMsg.append(",");
                }
                FieldError error = bindingResult.getFieldErrors().get(i);
                errMsg.append(error.getDefaultMessage());
            }
            return StandardResultVO.errorResult(errMsg.toString());
        } else {
            log.error(StackTraceLogUtil.getStackTraceAsString(e));
        }
        e.printStackTrace();
        return StandardResultVO.errorResult(e.getMessage());
    }

}
