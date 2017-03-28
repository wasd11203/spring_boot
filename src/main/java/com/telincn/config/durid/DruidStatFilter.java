package com.telincn.config.durid;

import javax.servlet.annotation.WebFilter;  
import javax.servlet.annotation.WebInitParam;  
  
import com.alibaba.druid.support.http.WebStatFilter;  
  
/**
 * durid监控的过滤器：
 *  当访问下列的url以及资源时，不被durid监控
 * @author ganzhigang
 * 时间：2017年3月28日 上午9:34:40
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",  
    initParams={  
        @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源  
   }  
)  
public class DruidStatFilter extends WebStatFilter {  
  
}  