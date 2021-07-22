package com.honeycomb.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.util.BeanCopierUtil;
import com.honeycomb.demo.entity.dao.DemoAccountMapper;
import com.honeycomb.demo.entity.dto.PageDemoDTO;
import com.honeycomb.demo.entity.po.DemoAccount;
import com.honeycomb.demo.entity.po.DemoAccountExample;
import com.honeycomb.demo.entity.po.RfDemoPO;
import com.honeycomb.demo.entity.vo.DemoVO;
import com.honeycomb.demo.service.DemoService;
import com.honeycomb.demo2.client.Demo2Client;
import com.honeycomb.demo2.entity.dto.Demo2DTO;
import com.honeycomb.demo2.entity.vo.Demo2VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description 华夏银行电子钱包回调相关
 * @Author Liuh
 * @Date 2020/6/4
 */
@Slf4j
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Resource
    Demo2Client demo2Client;
    @Autowired
    DemoAccountMapper demoAccountMapper;

    @Override
    public DemoVO demoTest(Demo2DTO demoDto) {
        StandardResultVO<Demo2VO> resultVO = demo2Client.demoTest(demoDto);
        if (resultVO.success()) {
           return BeanCopierUtil.initCopy(resultVO.getData(),DemoVO.class);
        }
        return null;
    }

    @Override
    public List<DemoAccount> listAccountByStatus(String accountStatus) {
        DemoAccountExample example = new DemoAccountExample();
        example.createCriteria()
                .andAccountStatusEqualTo(accountStatus);
        return demoAccountMapper.selectByExampleSelective(example);
    }

    @Override
    public PageInfo<RfDemoPO> countAdminPage(PageDemoDTO pageDemoDTO) {
        DemoAccountExample example = new DemoAccountExample();
        example.createCriteria()
                .andAccountStatusEqualTo(pageDemoDTO.getAccountStatus());
        return PageHelper.startPage(pageDemoDTO.getCurPage(), pageDemoDTO.getPageSize())
                .doSelectPageInfo(() -> demoAccountMapper.selectByExampleSelective(example));
    }

}
