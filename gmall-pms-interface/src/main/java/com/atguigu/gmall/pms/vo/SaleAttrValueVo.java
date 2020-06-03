package com.atguigu.gmall.pms.vo;

import lombok.Data;

import java.util.Set;

/**
 * @author ZCC
 * @date 2020/6/3 17:10
 */
@Data
public class SaleAttrValueVo {
    private Long attrId;
    private String attrName;
    private Set<String> attrValues;
}
