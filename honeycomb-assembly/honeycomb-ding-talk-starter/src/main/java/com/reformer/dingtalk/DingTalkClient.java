package com.honeycomb.dingtalk;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 钉钉消息报警功能
 *
 * @author guanym
 */
public class DingTalkClient {
    /**
     * 消息内容 { "content": "我就是我, 是不一样的烟火" }
     */
    protected static JSONObject jsonText = new JSONObject();
    /**
     * 发送的消息体 ，格式： { "msgtype": "text", "text": { "content": "我就是我, 是不一样的烟火" },
     * "at": { "atMobiles": [ "156xxxx8827", "189xxxx8325" ], "isAtAll": false }
     * }
     */
    protected HashMap<String, Object> jsonReq = new HashMap<>();
    DingTalkProperties dingTalkProperties;
    RestTemplate restTemplate;

    public DingTalkClient(DingTalkProperties dingTalkProperties, RestTemplate restTemplate) {
        this.dingTalkProperties = dingTalkProperties;
        this.restTemplate = restTemplate;
        init();
    }

    /**
     * 发送报警消息
     *
     * @param msg
     * @return
     */
    public DingtalkResult send(String msg) {
        return send(dingTalkProperties.getWebhook(), msg);
    }

    /**
     * 自定义发送钉钉的url
     *
     * @param url 钉钉机器人url
     * @param msg 消息
     * @return 钉钉返回对象
     */
    public DingtalkResult send(String url, String msg){
        DingtalkResult result = new DingtalkResult();
        if (!dingTalkProperties.getOpen()) {
            result.setErrcode("1");
            result.setErrmsg("未启用钉钉报警功能");
            return result;
        }
        // 消息内容
        JSONObject content = new JSONObject();
        content.put("content", dingTalkProperties.getMessagePrefix() + msg);
        try {
            // 发送消息
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HashMap<String, Object> request = new HashMap<>();
            request.put("text", content);
            buildRequest(request);
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(request, headers);
            result = restTemplate.postForObject(url, requestEntity, DingtalkResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            result.setErrcode("-1");
            result.setErrmsg("发送失败：" + e.getMessage());
        }
        return result;
    }

    private void buildRequest(Map<String, Object> request){
      for (Entry<String, Object> entry : jsonReq.entrySet()) {
        request.put(entry.getKey(), entry.getValue());
      }
    }

    public void init() {
        // 初始化请求参数
        jsonReq.put("msgtype", "text");
        // at
        JSONObject jsat = new JSONObject();
        jsat.put("isAtAll", dingTalkProperties.getIsAtAll());
        // 判断是否有提醒的人
        if (!StringUtils.isEmpty(dingTalkProperties.getAtMobiles())) {
            String[] ms = dingTalkProperties.getAtMobiles().split(",");
            jsat.put("atMobiles", ms);
        }
        jsonReq.put("at", jsat);
    }

}
