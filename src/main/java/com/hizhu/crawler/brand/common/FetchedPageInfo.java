package com.hizhu.crawler.brand.common;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 此类描述的是：访问页面相关信息
 *
 * @author: liyang@hizhu.com
 * @version: 2017-9-6 下午2:32:40
 */
public class FetchedPageInfo {
	
	// 页面链接
	private String url;
	// 页面html内容
	private String content;
	// 页面请求状态码
	private int statusCode;
	// 页面所需cookie
	private String cookie;
	// 相关房源状态
	private List<HouseInfo> statusList;
	// 相关小区信息
	private List<HouseInfo> estateInfoList;
	// 品牌公寓配置
	private List<HouseInfo> brandConfigInfoList;
	// 配置文件信息
	private Properties properties;
	// [个人抓取]数量限制
	private Map<String, Boolean> limitPersonConfig;
	// 抓取配置信息
	List<PersonConfigInfo> personConfigInfoList;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public List<HouseInfo> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<HouseInfo> statusList) {
		this.statusList = statusList;
	}
	public List<HouseInfo> getEstateInfoList() {
		return estateInfoList;
	}
	public void setEstateInfoList(List<HouseInfo> estateInfoList) {
		this.estateInfoList = estateInfoList;
	}
	public List<HouseInfo> getBrandConfigInfoList() {
		return brandConfigInfoList;
	}
	public void setBrandConfigInfoList(List<HouseInfo> brandConfigInfoList) {
		this.brandConfigInfoList = brandConfigInfoList;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Map<String, Boolean> getLimitPersonConfig() {
		return limitPersonConfig;
	}
	public void setLimitPersonConfig(Map<String, Boolean> limitPersonConfig) {
		this.limitPersonConfig = limitPersonConfig;
	}
	public List<PersonConfigInfo> getPersonConfigInfoList() {
		return personConfigInfoList;
	}
	public void setPersonConfigInfoList(List<PersonConfigInfo> personConfigInfoList) {
		this.personConfigInfoList = personConfigInfoList;
	}
	
}
