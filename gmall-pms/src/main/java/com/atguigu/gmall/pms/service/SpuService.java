package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.SpuVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.pms.entity.SpuEntity;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * spu信息
 *
 * @author zcc
 * @email zcc@atguigu.com
 * @date 2020-05-17 11:28:58
 */
public interface SpuService extends IService<SpuEntity> {

    PageResultVo queryPage(PageParamVo paramVo);

    PageResultVo querySpuInfo(Long categoryId, PageParamVo pageParamVo);

    void bigSave(SpuVo spuVo) throws FileNotFoundException;
}

