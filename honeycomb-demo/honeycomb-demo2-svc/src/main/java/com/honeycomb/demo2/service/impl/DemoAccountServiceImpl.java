package com.honeycomb.demo2.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honeycomb.demo2.entity.dto.DemoAccountDTO;
import com.honeycomb.demo2.entity.dto.DemoAccountMapper;
import com.honeycomb.demo2.entity.po.DemoAccount;
import com.honeycomb.demo2.entity.vo.DemoAccountVO;
import com.honeycomb.demo2.service.DemoAccountService;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @program: hoenycomb-parent
 * @description: demo 实现类
 * @author: wangzz
 * @create: 2020-12-23 18:59
 */
@Service
public class DemoAccountServiceImpl implements DemoAccountService {
    @Resource
    private DemoAccountMapper demoAccountMapper;

    @Override
    public Integer insertDemoAccount(DemoAccountDTO demoAccountDTO) {
        DemoAccount demoAccount = new DemoAccount();
        demoAccount.setAccountStatus("ACTIVE");
        demoAccount.setCreateTime(LocalDateTime.now());
        demoAccount.setIsRecharge(0);
        demoAccount.setIsDelete(false);
        demoAccount.setUpdateTime(LocalDateTime.now());
        demoAccount.setActiveTime(LocalDateTime.now());
        demoAccount.setTerminal("web");
        demoAccount.setUnionId(UUID.randomUUID().toString());
        demoAccount.setUnconsciousStatus(false);
        BeanCopier copier = BeanCopier.create(DemoAccountDTO.class, DemoAccount.class, false);
        copier.copy(demoAccountDTO, demoAccount, null);
        return demoAccountMapper.insert(demoAccount);

    }

    @Override
    public IPage<DemoAccountVO> getDemoAccountPage(Integer pageNum, Integer pageSize) {
        Page page =new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return demoAccountMapper.getPage(page);
    }
}
