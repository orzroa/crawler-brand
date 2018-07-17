package com.hizhu.crawler.brand.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.util.Map;

/**
 * 此类描述的是：发起HTTP请求[POST/GET]
 *
 * @author: liyang@hizhu.com
 * @version: 2017-11-17 上午10:59:07
 */
public class HttpConnectionManager {
	
	private final static Log log = LogFactory.getLog("HttpConnectionManagerLog");
	// 超时时间
	private final static int MAX_TIMEOUT = 20000;
	// PC浏览器标记
	private final static String USER_AGENT_PC = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0";
	// mobile浏览器标记
	private final static String USER_AGENT_MOBILE = "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_2 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11a501 Safari/9537.53";
	
//	private final static String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	
//	private final static String ACCEPT_JSON = "application/json, text/javascript, */*; q=0.01";
	
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";
	
	private final static String ACCEPT_ENCODING = "gzip, deflate, br";
	
	private final static String ACCEPT_LANGUAGE = "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3";
	
	/**
	 * 此方法描述的是：发起http请求[Get方式][附带默认重试三次]
	 *
	 * @param url 请求链接
	 * @param isMobile 是否通过手机请求
	 * @param isProxy 是否使用代理
	 * @param charset 设置页面解析编码[防止中文乱码]
	 * @param retryInterval 重试间隔时间[ms毫秒]
	 * @return
	 * @author: liyang@hizhu.com
	 * @version: 2017-11-22 上午10:38:04
	 */
	public static FetchedPageInfo sendGetAndRetry(String url, boolean isMobile, boolean isProxy, String charset, long retryInterval){

		/**
		 * 访问页面相关信息获取 重试三次 每次睡500ms
		 */
		FetchedPageInfo fetchedPageInfo = sendGet(url, isMobile, isProxy, charset);
		if(fetchedPageInfo == null){
			try {
				Thread.sleep(retryInterval);
				log.info(">>>>> HttpConnectionManager send GET retry first time =>>>" + url);
				fetchedPageInfo = sendGet(url, isMobile, isProxy, charset);
				if(fetchedPageInfo == null){
					Thread.sleep(retryInterval);
					log.info(">>>>> HttpConnectionManager send GET retry second time =>>>" + url);
					fetchedPageInfo = sendGet(url, isMobile, isProxy, charset);
					if(fetchedPageInfo == null){
						Thread.sleep(retryInterval);
						log.info(">>>>> HttpConnectionManager send GET retry third time =>>>" + url);
						fetchedPageInfo = sendGet(url, isMobile, isProxy, charset);
						if(fetchedPageInfo == null){
							return null;
						}else{
							return fetchedPageInfo;
						}
					}else{
						return fetchedPageInfo;
					}
				}else{
					return fetchedPageInfo;
				}
			} catch (Throwable e) {
				log.error("xxxxx HttpConnectionManager send GET retry error =>>>" + url);
				return null;
			}
		}else{
			return fetchedPageInfo;
		}
	}
	/**
	 * 此方法描述的是：发起http请求[Get方式]
	 *
	 * @param url 请求链接
	 * @param isMobile 是否通过手机请求
	 * @param isProxy 是否使用代理
	 * @param charset 设置页面解析编码[防止中文乱码]
	 * @return FetchedPageInfo
	 * @author: liyang@hizhu.com
	 * @version: 2017-11-21 下午1:55:38
	 */
	public static FetchedPageInfo sendGet(String url, boolean isMobile, boolean isProxy, String charset){
		// 默认PC
		String userAgent = USER_AGENT_PC;
		if(isMobile){
			userAgent = USER_AGENT_MOBILE;
		}
		try {
			// 设置请求参数
			Connection connection = Jsoup.connect(url)
										 .header("User-Agent", userAgent)
										 .header("Accept-Encoding", ACCEPT_ENCODING)
										 .header("Accept-Language", ACCEPT_LANGUAGE)
										 .ignoreContentType(true)
										 .ignoreHttpErrors(true)
										 .method(Method.GET)
										 .timeout(MAX_TIMEOUT);
			// 判断是否使用代理访问
			if(isProxy){
				String proxyIP = CommonUtils.fetchCatchProxyIP();
				JSONObject proxy_json = JSONObject.parseObject(proxyIP);
				connection.proxy(proxy_json.getString("ip"), Integer.parseInt(proxy_json.getString("port")));
			}else{
				connection.header("X-Forwarded-For", FetchRandomIP.getRandomIp());
			}
			// 发起请求
			Response response = connection.execute();
			if(response == null || response.body().isEmpty() || (response.statusCode() != 200 && response.statusCode() != 404)){
				log.info("xxxxx HttpConnectionManager send GET result is null =>>>" + url);
				return null;
			}
			// TODO 设置页面解析编码[防止中文乱码]
			if(!charset.isEmpty()){
				response.charset(charset);
			}
			
			// 设置返回实体
			FetchedPageInfo fetchedPageInfo = new FetchedPageInfo();
			fetchedPageInfo.setUrl(url);
			fetchedPageInfo.setStatusCode(response.statusCode());
			fetchedPageInfo.setContent(response.parse().html());
			
			return fetchedPageInfo;
		} catch (Throwable e) {
			log.error("xxxxx HttpConnectionManager send GET error =>>>" + url);
			return null;
		}
	}
	/**
	 * 此方法描述的是：发起http请求[Post方式][附带默认重试三次]
	 *
	 * @param url 请求链接
	 * @param dataMap 请求参数
	 * @param isProxy 是否使用代理
	 * @param retryInterval 重试间隔时间[ms毫秒]
	 * @return
	 * @author: liyang@hizhu.com
	 * @version: 2017-9-6 下午4:29:08
	 */
	public static FetchedPageInfo sendPostAndRetry(String url, Map<String, String> headerMap, Map<String, String> dataMap, boolean isProxy, long retryInterval){
		
		FetchedPageInfo fetchedPageInfo = sendPost(url, headerMap, dataMap, isProxy);
		if(fetchedPageInfo == null){
			try {
				Thread.sleep(retryInterval);
				log.info(">>>>> HttpConnectionManager send POST retry first time =>>>" + url);
				fetchedPageInfo = sendPost(url, headerMap, dataMap, isProxy);
				if(fetchedPageInfo == null){
					Thread.sleep(retryInterval);
					log.info(">>>>> HttpConnectionManager send POST retry second time =>>>" + url);
					fetchedPageInfo = sendPost(url, headerMap, dataMap, isProxy);
					if(fetchedPageInfo == null){
						Thread.sleep(retryInterval);
						log.info(">>>>> HttpConnectionManager send POST retry third time =>>>" + url);
						fetchedPageInfo = sendPost(url, headerMap, dataMap, isProxy);
						if(fetchedPageInfo == null){
							return null;
						}else{
							return fetchedPageInfo;
						}
					}else{
						return fetchedPageInfo;
					}
				}else{
					return fetchedPageInfo;
				}
			} catch (Throwable e) {
				log.error("xxxxx HttpConnectionManager send POST retry error =>>>" + url);
				return null;
			}
		}else{
			return fetchedPageInfo;
		}
	}
	/**
	 * 此方法描述的是：发起http请求[Post方式]
	 *
	 * @param url 请求链接
	 * @param dataMap 请求参数
	 * @param isProxy 是否使用代理
	 * @return
	 * @author: liyang@hizhu.com
	 * @version: 2017-9-6 下午4:14:02
	 */
	public static FetchedPageInfo sendPost(String url, Map<String, String> headerMap, Map<String, String> dataMap, boolean isProxy){
		// 默认PC
		String userAgent = USER_AGENT_PC;
		try {
			// 设置请求参数
			Connection connection = null;
			if(headerMap == null){
				connection = Jsoup.connect(url)
								  .header("User-Agent", userAgent)
								  .header("Accept-Encoding", ACCEPT_ENCODING)
								  .header("Accept-Language", ACCEPT_LANGUAGE)
								  .ignoreContentType(true)
								  .ignoreHttpErrors(true)
								  .method(Method.POST)
								  .timeout(MAX_TIMEOUT);
			}else{
				connection = Jsoup.connect(url)
								  .header("User-Agent", userAgent)
								  .header("Accept-Encoding", ACCEPT_ENCODING)
								  .header("Accept-Language", ACCEPT_LANGUAGE)
								  .headers(headerMap)
								  .ignoreContentType(true)
								  .ignoreHttpErrors(true)
								  .method(Method.POST)
								  .timeout(MAX_TIMEOUT);
			}
			if(dataMap != null){
				connection.data(dataMap);
			}
			// 判断是否使用代理访问
			if(isProxy){
				String proxyIP = CommonUtils.fetchCatchProxyIP();
				JSONObject proxy_json = JSONObject.parseObject(proxyIP);
				connection.proxy(proxy_json.getString("ip"), Integer.parseInt(proxy_json.getString("port")));
			}else{
				connection.header("X-Forwarded-For", FetchRandomIP.getRandomIp());
			}
			// 发起请求
			Response response = connection.execute();
			if(response == null || response.body().isEmpty() || (response.statusCode() != 200 && response.statusCode() != 404)){
				log.info("xxxxx HttpConnectionManager send POST result is null =>>>" + url);
				return null;
			}
			// 设置返回实体
			FetchedPageInfo fetchedPageInfo = new FetchedPageInfo();
			fetchedPageInfo.setUrl(url);
			fetchedPageInfo.setStatusCode(response.statusCode());
			fetchedPageInfo.setContent(response.parse().html());
			
			return fetchedPageInfo;
		} catch (Throwable e) {
			log.error("xxxxx HttpConnectionManager send POST error =>>>" + url);
			return null;
		}
	}
	/**
	 * 此方法描述的是：发起HTTP请求[Post方式][附带默认重试三次]JSON参数
	 *
	 * @param url 请求链接
	 * @param json 请求参数
	 * @param isProxy 是否使用代理
	 * @param retryInterval 重试间隔时间[ms毫秒]
	 * @return FetchedPageInfo
	 * @author: liyang@hizhu.com
	 * @version: 2017-11-30 上午10:49:35
	 */
	public static FetchedPageInfo sendJsonPostAndRetry(String url, String json, boolean isProxy, long retryInterval){
		
		FetchedPageInfo fetchedPageInfo = sendJsonPost(url, json, isProxy);
		if(fetchedPageInfo == null){
			try {
				Thread.sleep(retryInterval);
				log.info(">>>>> HttpConnectionManager send json POST retry first time =>>>" + url);
				fetchedPageInfo = sendJsonPost(url, json, isProxy);
				if(fetchedPageInfo == null){
					Thread.sleep(retryInterval);
					log.info(">>>>> HttpConnectionManager send json POST retry second time =>>>" + url);
					fetchedPageInfo = sendJsonPost(url, json, isProxy);
					if(fetchedPageInfo == null){
						Thread.sleep(retryInterval);
						log.info(">>>>> HttpConnectionManager send json POST retry third time =>>>" + url);
						fetchedPageInfo = sendJsonPost(url, json, isProxy);
						if(fetchedPageInfo == null){
							return null;
						}else{
							return fetchedPageInfo;
						}
					}else{
						return fetchedPageInfo;
					}
				}else{
					return fetchedPageInfo;
				}
			} catch (Throwable e) {
				log.error("xxxxx HttpConnectionManager send json POST retry error =>>>" + url);
				return null;
			}
		}else{
			return fetchedPageInfo;
		}
	}
	/**
	 * 此方法描述的是：发起HTTP请求[Post方式]JSON参数
	 *
	 * @param url 请求链接
	 * @param json 请求JSON参数
	 * @param isProxy 是否使用代理
	 * @return FetchedPageInfo
	 * @author: liyang@hizhu.com
	 * @version: 2017-11-30 上午10:33:02
	 */
	public static FetchedPageInfo sendJsonPost(String url, String json, boolean isProxy){
		try {
			// 设置请求参数
			Connection connection = null;
			if(json.isEmpty()){
				connection = Jsoup.connect(url)
								 .ignoreContentType(true)
								 .ignoreHttpErrors(true)
								 .header("User-Agent", USER_AGENT_PC)
								 .header("Content-Type", CONTENT_TYPE)
								 .method(Method.POST)
								 .timeout(MAX_TIMEOUT);
			}else{
				connection = Jsoup.connect(url)
								 .ignoreContentType(true)
								 .ignoreHttpErrors(true)
								 .header("User-Agent", USER_AGENT_PC)
								 .header("Content-Type", CONTENT_TYPE)
								 .requestBody(json)
								 .method(Method.POST)
								 .timeout(MAX_TIMEOUT);
			}
			// 判断是否使用代理访问
			if(isProxy){
				String proxyIP = CommonUtils.fetchCatchProxyIP();
				JSONObject proxy_json = JSONObject.parseObject(proxyIP);
				connection.proxy(proxy_json.getString("ip"), Integer.parseInt(proxy_json.getString("port")));
			}else{
				connection.header("X-Forwarded-For", FetchRandomIP.getRandomIp());
			}
			// 发起请求
			Response response = connection.execute();
			if(response == null || response.body().isEmpty() || (response.statusCode() != 200 && response.statusCode() != 404)){
				log.info("xxxxx HttpConnectionManager send json POST result is null =>>>" + url);
				return null;
			}
			// 设置返回实体
			FetchedPageInfo fetchedPageInfo = new FetchedPageInfo();
			fetchedPageInfo.setUrl(url);
			fetchedPageInfo.setStatusCode(response.statusCode());
			fetchedPageInfo.setContent(response.parse().html());
			
			return fetchedPageInfo;
		} catch (Throwable e) {
			log.error("xxxxx HttpConnectionManager send json POST error =>>>" + url);
			return null;
		}
	}
	
}