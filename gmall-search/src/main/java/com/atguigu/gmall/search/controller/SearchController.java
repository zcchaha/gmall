package com.atguigu.gmall.search.controller;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.search.bean.SearchParam;
import com.atguigu.gmall.search.bean.SearchResponseVo;
import com.atguigu.gmall.search.service.SeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZCC
 * @date 2020/5/27 18:03
 */
@RestController
public class SearchController {

    @Autowired
    private SeacherService seacherService;

    @GetMapping("search")
    public ResponseVo<Object> search(SearchParam searchParam){
        SearchResponseVo searchResponseVo = this.seacherService.search(searchParam);
        return ResponseVo.ok(searchResponseVo);
    }

}
