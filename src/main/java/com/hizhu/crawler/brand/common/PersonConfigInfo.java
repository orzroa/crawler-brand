package com.hizhu.crawler.brand.common;

/**
 * 此类描述的是：[个人抓取]配置信息模型
 *
 * @author: liyang@hizhu.com
 * @version: 2017-9-5 下午3:13:38
 */
public class PersonConfigInfo {
	
	private String city_code;// 城市编号
	
	private String info_resource;// 来源渠道名称
	
	private String region_name;// 区域名称
	
	private String scope_name;// 板块名称
	
	private String rent_name;// 出租类型名称[合租/整套]
	
	private int rob_count;// 抓取数量限制
	
	private String client_name;// 房东姓名
	
	private String client_phone;// 联系电话
	
	private String estate_name;// 小区名称过滤
	
	private String room_description;// 房源描述信息过滤
	
	private int handle_time;// 数据操作时间
	
	private String handle_man;// 操作人名称

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getInfo_resource() {
		return info_resource;
	}

	public void setInfo_resource(String info_resource) {
		this.info_resource = info_resource;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getScope_name() {
		return scope_name;
	}

	public void setScope_name(String scope_name) {
		this.scope_name = scope_name;
	}

	public String getRent_name() {
		return rent_name;
	}

	public void setRent_name(String rent_name) {
		this.rent_name = rent_name;
	}

	public int getRob_count() {
		return rob_count;
	}

	public void setRob_count(int rob_count) {
		this.rob_count = rob_count;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_phone() {
		return client_phone;
	}

	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}

	public String getEstate_name() {
		return estate_name;
	}

	public void setEstate_name(String estate_name) {
		this.estate_name = estate_name;
	}

	public String getRoom_description() {
		return room_description;
	}

	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}
	
	public int getHandle_time() {
		return handle_time;
	}

	public void setHandle_time(int handle_time) {
		this.handle_time = handle_time;
	}

	public String getHandle_man() {
		return handle_man;
	}

	public void setHandle_man(String handle_man) {
		this.handle_man = handle_man;
	}
	
}
