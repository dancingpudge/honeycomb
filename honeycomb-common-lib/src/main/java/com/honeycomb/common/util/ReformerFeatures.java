package com.honeycomb.common.util;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @description: com.honeycomb.common.util <br>
 * @date: 2021/2/26 3:32 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public class ReformerFeatures<T> implements ObjectFeaturesFunction<T> {
    @Override
    public String features(Object o) {
        return SecureUtil.md5(JSONObject.toJSONString(o));
    }
}
