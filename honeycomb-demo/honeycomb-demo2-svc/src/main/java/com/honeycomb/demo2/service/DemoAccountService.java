package com.honeycomb.demo2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honeycomb.demo2.entity.dto.DemoAccountDTO;
import com.honeycomb.demo2.entity.vo.DemoAccountVO;

/**
 * @program: hoenycomb-parent
 * @description: demo 接口
 * @author: wangzz
 * @create: 2020-12-23 18:57
 */
public interface DemoAccountService {

    /**
     * 新增demo
     * @param demoAccountDTO
     * @return
     */
   Integer insertDemoAccount(DemoAccountDTO demoAccountDTO);


    /**
     * 获取demo数据分页
     * @param pageNum
     * @param pageSize
     * @return
     */
   IPage<DemoAccountVO> getDemoAccountPage(Integer pageNum,Integer pageSize);

}
