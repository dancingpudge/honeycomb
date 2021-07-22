package com.honeycomb.common.util;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: com.honeycomb.common.util <br>
 * @date: 2021/1/18 1:33 下午 <br>
 * @version: 0.0.1-SNAPSHOT <br>
 * @author liuhui
 */
public class JoinPointUtils {

    public static HashMap getParams(JoinPoint joinPoint) {
        HashMap allParams = new HashMap();
        Object[] paramValues = joinPoint.getArgs();
        Stream<?> stream = ArrayUtils.isEmpty(paramValues) ? Stream.empty() : Arrays.asList(paramValues).stream();
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            if (logArgs.get(i) instanceof Map<?, ?>) {
                //提取方法中的MAP参数，用于记录进日志中
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) paramValues[i];
                allParams.putAll(map);
            } else {
                allParams.put(paramNames[i], paramValues[i]);
            }
        }
        return allParams;
    }
}
