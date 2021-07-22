package com.honeycomb.demo2;


import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.base.exception.ApiException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/18 2:39 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
public class ReformerCommonFallBackFactory<T> implements FallbackFactory {
    @Override
    public T create(Throwable throwable) {
        System.out.println(JSONObject.toJSONString(throwable));
        throw new ApiException("1000");
    }
}
