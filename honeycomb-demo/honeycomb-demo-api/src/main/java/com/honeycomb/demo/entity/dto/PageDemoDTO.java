package com.honeycomb.demo.entity.dto;

import com.honeycomb.common.entity.dto.PageForm;
import lombok.Data;

/**
 * @PackgeName: com.honeycomb.demo.entity.dto
 * @Description:
 * @Author: LiuH
 * @Date 2020-12-23 15:21:27
 **/
@Data
public class PageDemoDTO extends PageForm {
    private String accountStatus;
}
