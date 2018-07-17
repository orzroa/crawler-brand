package com.hizhu.crawler.brand.common;

public class HouseInfo {

	private String id;
	private String city_code;
	private String info_resource;
	private int region_id;
	private String region_name;
	private int scope_id;// 板块（商圈）ID
	private String scope_name;// 板块（商圈）名称
	private String estate_id;
	private String estate_name;
	private String estate_address;
	private String unit_no;
	private float area;
	private float house_area;
	private String house_no;
	private String room_no;
	private int floor;
	private int floor_total;
	private int room_num;
	private int hall_num;
	private int wei_num;
	private String house_direction;
	private String house_type;
	private String house_direction_name; // 房屋朝向
	private String pay_method; // 付款方式编号
	private String pay_method_name; // 付款方式
	private String public_equipment;
	private String room_equipment;
	private String equipment_names; // 设施名称
	private String room_name; // 房间名称（主次）
	private String room_type;
	private float room_money; // 租金
	private String room_description; //房间描述
	private String fangdong_phone; // 房东联系电话
	private String fangdong_name; // 房东姓名
	private String info_resource_url; // url地址
	private String business_type;// 业务类型
	private String decoration;
	private String decoration_name;// 装修情况
	private String third_id;// 第三方ID
	private String third_no;// 第三方编号
	private String house_type_name;
	private String brand_type;
	private int create_time;
	private int update_time;
	private int status;
	private String sim_number;
	private String publish_date;
	private String last_follow_date;
	private String record_status;
	private long pk_id;
	private String original_img_urls;
	private int girl_tag;
	private int rent_type;// 租赁类型[整租2/合租1]
	private String main_img_id;
	private String main_img_path;
	private String customer_id;
	private int uproom_type = 0;// 个人上房免审核标记[0-需要审核/1-免审核]
	private int phone_status = 0;// 异常号码标识[0-正常号码/1-异常号码]
	private int show_call_bar = 1;// 是否显示联系房东按钮[0-不显示/1-显示]
	private int show_reserve_bar = 1;// 是否显示预约按钮[0-不显示/1-显示]
	private float low_price;// 最低价格
	private int is_owner = 2;// 帐号角色
	private String principal_man = "";// 房东负责人
	private int is_commission = 0;// 是否佣金付费
	private int is_monthly = 0;// 是否包月付费
	private int is_cut = 0;//是否隔断[0-非隔断/1-隔断]
	private int had_vedio = 0;// 是否有视频[0-无/1-有]
	
	
	public int getHad_vedio() {
		return had_vedio;
	}
	public void setHad_vedio(int had_vedio) {
		this.had_vedio = had_vedio;
	}
	public int getIs_cut() {
		return is_cut;
	}
	public void setIs_cut(int is_cut) {
		this.is_cut = is_cut;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getEstate_id() {
		return estate_id;
	}
	public void setEstate_id(String estate_id) {
		this.estate_id = estate_id;
	}
	public String getEstate_name() {
		return estate_name;
	}
	public void setEstate_name(String estate_name) {
		this.estate_name = estate_name;
	}
	public String getEstate_address() {
		return estate_address;
	}
	public void setEstate_address(String estate_address) {
		this.estate_address = estate_address;
	}
	public String getUnit_no() {
		return unit_no;
	}
	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public float getHouse_area() {
		return house_area;
	}
	public void setHouse_area(float house_area) {
		this.house_area = house_area;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public int getFloor_total() {
		return floor_total;
	}
	public void setFloor_total(int floor_total) {
		this.floor_total = floor_total;
	}
	public int getRoom_num() {
		return room_num;
	}
	public void setRoom_num(int room_num) {
		this.room_num = room_num;
	}
	public int getHall_num() {
		return hall_num;
	}
	public void setHall_num(int hall_num) {
		this.hall_num = hall_num;
	}
	public int getWei_num() {
		return wei_num;
	}
	public void setWei_num(int wei_num) {
		this.wei_num = wei_num;
	}
	public String getHouse_direction() {
		return house_direction;
	}
	public void setHouse_direction(String house_direction) {
		this.house_direction = house_direction;
	}
	public String getHouse_type() {
		return house_type;
	}
	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}
	public String getHouse_direction_name() {
		return house_direction_name;
	}
	public void setHouse_direction_name(String house_direction_name) {
		this.house_direction_name = house_direction_name;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getPay_method_name() {
		return pay_method_name;
	}
	public void setPay_method_name(String pay_method_name) {
		this.pay_method_name = pay_method_name;
	}
	public String getPublic_equipment() {
		return public_equipment;
	}
	public void setPublic_equipment(String public_equipment) {
		this.public_equipment = public_equipment;
	}
	public String getRoom_equipment() {
		return room_equipment;
	}
	public void setRoom_equipment(String room_equipment) {
		this.room_equipment = room_equipment;
	}
	public String getEquipment_names() {
		return equipment_names;
	}
	public void setEquipment_names(String equipment_names) {
		this.equipment_names = equipment_names;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public float getRoom_money() {
		return room_money;
	}
	public void setRoom_money(float room_money) {
		this.room_money = room_money;
	}
	public String getRoom_description() {
		return room_description;
	}
	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}
	public String getFangdong_phone() {
		return fangdong_phone;
	}
	public void setFangdong_phone(String fangdong_phone) {
		this.fangdong_phone = fangdong_phone;
	}
	public String getFangdong_name() {
		return fangdong_name;
	}
	public void setFangdong_name(String fangdong_name) {
		this.fangdong_name = fangdong_name;
	}
	public String getInfo_resource_url() {
		return info_resource_url;
	}
	public void setInfo_resource_url(String info_resource_url) {
		this.info_resource_url = info_resource_url;
	}
	public String getScope_name() {
		return scope_name;
	}
	public void setScope_name(String scope_name) {
		this.scope_name = scope_name;
	}
	public int getScope_id() {
		return scope_id;
	}
	public void setScope_id(int scope_id) {
		this.scope_id = scope_id;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public String getDecoration_name() {
		return decoration_name;
	}
	public void setDecoration_name(String decoration_name) {
		this.decoration_name = decoration_name;
	}
	public String getThird_id() {
		return third_id;
	}
	public void setThird_id(String third_id) {
		this.third_id = third_id;
	}
	public String getThird_no() {
		return third_no;
	}
	public void setThird_no(String third_no) {
		this.third_no = third_no;
	}
	public String getHouse_type_name() {
		return house_type_name;
	}
	public void setHouse_type_name(String house_type_name) {
		this.house_type_name = house_type_name;
	}
	public String getBrand_type() {
		return brand_type;
	}
	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}
	public int getCreate_time() {
		return create_time;
	}
	public void setCreate_time(int create_time) {
		this.create_time = create_time;
	}
	public int getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(int update_time) {
		this.update_time = update_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSim_number() {
		return sim_number;
	}
	public void setSim_number(String sim_number) {
		this.sim_number = sim_number;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public String getLast_follow_date() {
		return last_follow_date;
	}
	public void setLast_follow_date(String last_follow_date) {
		this.last_follow_date = last_follow_date;
	}
	public String getRecord_status() {
		return record_status;
	}
	public void setRecord_status(String record_status) {
		this.record_status = record_status;
	}
	public long getPk_id() {
		return pk_id;
	}
	public void setPk_id(long pk_id) {
		this.pk_id = pk_id;
	}
	public String getOriginal_img_urls() {
		return original_img_urls;
	}
	public void setOriginal_img_urls(String original_img_urls) {
		this.original_img_urls = original_img_urls;
	}
	public int getGirl_tag() {
		return girl_tag;
	}
	public void setGirl_tag(int girl_tag) {
		this.girl_tag = girl_tag;
	}
	public int getRent_type() {
		return rent_type;
	}
	public void setRent_type(int rent_type) {
		this.rent_type = rent_type;
	}
	public String getMain_img_id() {
		return main_img_id;
	}
	public void setMain_img_id(String main_img_id) {
		this.main_img_id = main_img_id;
	}
	public String getMain_img_path() {
		return main_img_path;
	}
	public void setMain_img_path(String main_img_path) {
		this.main_img_path = main_img_path;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public int getUproom_type() {
		return uproom_type;
	}
	public void setUproom_type(int uproom_type) {
		this.uproom_type = uproom_type;
	}
	public int getPhone_status() {
		return phone_status;
	}
	public void setPhone_status(int phone_status) {
		this.phone_status = phone_status;
	}
	public int getShow_call_bar() {
		return show_call_bar;
	}
	public void setShow_call_bar(int show_call_bar) {
		this.show_call_bar = show_call_bar;
	}
	public int getShow_reserve_bar() {
		return show_reserve_bar;
	}
	public void setShow_reserve_bar(int show_reserve_bar) {
		this.show_reserve_bar = show_reserve_bar;
	}
	public float getLow_price() {
		return low_price;
	}
	public void setLow_price(float low_price) {
		this.low_price = low_price;
	}
	public int getIs_owner() {
		return is_owner;
	}
	public void setIs_owner(int is_owner) {
		this.is_owner = is_owner;
	}
	public String getPrincipal_man() {
		return principal_man;
	}
	public void setPrincipal_man(String principal_man) {
		this.principal_man = principal_man;
	}
	public int getIs_commission() {
		return is_commission;
	}
	public void setIs_commission(int is_commission) {
		this.is_commission = is_commission;
	}
	public int getIs_monthly() {
		return is_monthly;
	}
	public void setIs_monthly(int is_monthly) {
		this.is_monthly = is_monthly;
	}
}
