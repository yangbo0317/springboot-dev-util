package com.yangbo.springboot.springbootmq.com.yangbo.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * yangbo
 * 2018-07-18
 * springboot-dev-util
 **/
@WebFilter(filterName="druidStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        })

public class DruidStatFilter extends WebStatFilter {
}
