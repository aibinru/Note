package com.demo03.controller;


import com.demo03.entity.BrandEntity;
import com.demo03.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("/info/{brandId}")
    public BrandEntity queryBrand(@PathVariable("brandId") Long brandId) {
        return brandService.getById(brandId);
    }

    @RequestMapping("/querys")
    public BrandEntity query() {
        return brandService.getById(1);
    }

    @RequestMapping("/query")
    public String hello() {
        return "query";
    }

    //保存
    @RequestMapping("/save")
    public String save(@RequestBody BrandEntity brand) {
        brandService.save(brand);
        return "success";
    }
}
