package com.honeycomb.common.log;

/**
 * @program: honeycomb-parent
 * @description: 日志推送器，实现此接口来推送
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
public interface LogPusher {
    /**
     * 推送数据到指定的位置，各模块自己实现
     *
     * @param vo
     */
    void push(BusinessLogVO vo);
}
