package com.honeycomb.demo.service;


import com.github.pagehelper.PageInfo;
import com.honeycomb.demo.entity.dto.PageDemoDTO;
import com.honeycomb.demo.entity.po.DemoAccount;
import com.honeycomb.demo.entity.po.RfDemoPO;
import com.honeycomb.demo.entity.vo.DemoVO;
import com.honeycomb.demo2.entity.dto.Demo2DTO;

import java.util.List;

/**
 * @Description 演示demo
 * @Author LiuH
 * @Date 2020/6/4
 */
public interface DemoService {
    /**
     * 演示样例
     * 功能注释写在接口上，不要放到具体实现上
     *
     * @param demoDto
     * @return StandardResultVO
     */
    DemoVO demoTest(Demo2DTO demoDto);

    /**
     * 账户信息查询
     *
     * @return
     */
    List<DemoAccount> listAccountByStatus(String accountStatus);


    /**
     * 分页插件使用
     *
     * @param pageDemoDTO
     * @return
     */
    PageInfo<RfDemoPO> countAdminPage(PageDemoDTO pageDemoDTO);
}
