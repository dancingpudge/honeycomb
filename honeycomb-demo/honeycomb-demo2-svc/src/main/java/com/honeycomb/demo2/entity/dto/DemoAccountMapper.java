package com.honeycomb.demo2.entity.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honeycomb.demo2.entity.po.DemoAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honeycomb.demo2.entity.vo.DemoAccountVO;

/**
 * <p>
 * 钱包账户表 Mapper 接口
 * </p>
 *
 * @author demo
 * @since 2020-12-23
 */
public interface DemoAccountMapper extends BaseMapper<DemoAccount> {

    IPage<DemoAccountVO> getPage(IPage page);

}
