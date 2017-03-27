package com.telincn.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import com.telincn.mapper.RoleMapper;
import com.telincn.security.util.UrlMatcher;
import com.telincn.security.util.impl.AntUrlPathMatcher;

/**
 * 基于url 的鉴权
 * @author ganzhigang
 * 时间：2017年3月22日 上午10:10:52
 */
@Service("securityMetadataSource")
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	public static Logger logger = LoggerFactory.getLogger(InvocationSecurityMetadataSource.class);
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
//	@Autowired
//	private RoleMapper roleMapper;
	
	// tomcat启动时实例化一次
	@Autowired
	public InvocationSecurityMetadataSource(RoleMapper roleMapper) {
		logger.debug("初始化 角色-- 资源关系");
		loadResourceDefine(roleMapper);
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine(RoleMapper roleMapper) {
//		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
//		atts.add(ca);
//		resourceMap.put("/hello", atts);
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<Map<String, Object>> res = roleMapper.loadSourceWithRoleAssociation();
		Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
		
        for(Map<String, Object> items : res){
        	
        	String keyTmp = (String) items.get("SOURCE_URL");
        	String valueItemTmp = (String) items.get("ROLE_NAME");
        	ConfigAttribute ca = new SecurityConfig(valueItemTmp);
        	
        	if(resourceMap.containsKey(keyTmp)){
        		resourceMap.get(keyTmp).add(ca);
        	}else{
        		attrs.add(ca);
        		resourceMap.put(keyTmp, attrs);
        	}
        }
	}

	// 参数是要访问的url，返回这个url对于的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		logger.debug("获得可访问该url的所有权限");
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		logger.debug("当前访问的URL:"+url);
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}