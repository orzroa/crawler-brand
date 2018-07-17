package com.hizhu.crawler.brand.common;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * 此类描述的是：通用工具类
 *
 * @author: liyang@hizhu.com
 * @version: 2017-8-30 下午5:03:36
 */
public class CommonUtils {
	
	private static final Log log = LogFactory.getLog("CommonUtilsLog");
	
	/**
	 * 此方法描述的是：获取输入流数据量
	 *
	 * @param inStream
	 * @return
	 * @throws Exception
	 * @author: liyang@hizhu.com
	 * @version: 2017年3月3日 下午7:14:26
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while((len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String sendPost(String url, String param) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		try {
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection)connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
			httpConn.setRequestProperty("Accept-Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			param = param.replace("+", "%2B");
			out.write(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			log.info("xxxxx utils sendPost() error =>>>" + url, e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				log.info("xxxxx utils sendPost() error =>>>" + url, ex);
			}
		}
		return result;
	}
	// 获取带"-"的UUID
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str;
    }
	// 获取不带"-"的UUID
	public static String getStringUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
		return temp;
 	}
	// 生成pk_id
	public static long getPk_id() {
		long number = 0;
		StringBuffer pk_id = new StringBuffer(Long.toString(System.currentTimeMillis()));
		for(int a=0;a<5;a++){
			String randomNum = Long.toString((long)(Math.random()*10));
			pk_id.append(randomNum);
		}
		number = Long.parseLong(pk_id.toString());
		return number;
    }
	// 插入中介库
	public static void insertAgentPhone(String phone) {
		// 加入黑名单库
		String url = "http://120.26.119.103/admin/Common/pullBlackUser?bak_type=4&out_house=0&oper_id=AddAgent&bak_info=中介聚合&mobile=" + phone;
		try {
			Jsoup.connect(url).timeout(30000).get();
		} catch (Throwable e) {
			e.printStackTrace();
			log.info("xxxxx utils insertAgentPhone() insert agent phone error =>>>" + phone, e);
		}
    }
	/**
	 * 此方法描述的是：获取代理IP方法
	 *
	 * @return
	 * @author: liyang@hizhu.com
	 * @version: 2017年2月9日 上午10:31:52
	 */
	public static String fetchCatchProxyIP(){
		String ip = "";
		try {
			Document doc = Jsoup.connect("http://120.27.162.0:8090/HouseData/proxy/getCatch").timeout(30000).get();
			ip = doc.select("body").text();
			return ip;
		} catch (Throwable e) {
			log.info("xxxxx utils fetchCatchProxyIP() fetch catchProxy ip error.", e);
			return ip;
		}
	}
	/**
	 * 此方法描述的是：获取代理IP方法
	 *
	 * @return
	 * @author: liyang@hizhu.com
	 * @version: 2017-8-30 下午4:55:30
	 */
	public static String fetchProxyIP(){
		String ip = "";
		try {
			Document doc = Jsoup.connect("http://120.27.162.0:8090/HouseData/proxy/get").timeout(30000).get();
			ip = doc.select("body").text();
			return ip;
		} catch (Throwable e) {
			log.info("xxxxx utils fetchProxyIP() fetch proxy ip error.", e);
			return ip;
		}
	}
	// MD5加密
	public static String strToMD5(String str) {
		byte[] input = str.getBytes();
		String md5str = null;
		try {
			// 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算后获得字节数组
			byte[] buff = md.digest(input);
			// 把数组每一字节换成16进制连成md5字符串
			StringBuffer md5Buffer = new StringBuffer();
			// 把数组每一字节换成16进制连成md5字符串
			int digital;
			for (int i = 0; i < buff.length; i++) {
				digital = buff[i];
				if(digital < 0) {
					digital += 256;
				}
				if(digital < 16){
					md5Buffer.append("0");
				}
				md5Buffer.append(Integer.toHexString(digital));
			}
			md5str = md5Buffer.toString().toUpperCase();
		} catch (Throwable e) {
			log.info("xxxxx utils strToMD5() string to MD5 error =>>>" + str, e);
		}
		
		return md5str;
	}
	// 生成随机整数
	public static int getRandomTime(int num){
		Random rd = new Random();
		int x = rd.nextInt(num);
		return x;
	}
	// 验证字符串编码
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {//判断是不是GB2312
				String s = encode;
				return s;//是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {        }
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {//判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {        }
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {//判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {        }
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {//判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {        }
		
		return "";
	}
	// 替换字符串中的Emoji表情
	public static String filterEmoji(String source,String slipStr) {
		if(StringUtils.isNotBlank(source)){
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
		}else{
			return source;
		}
	}
	/**
	 * 此方法描述的是：图片压缩
	 *
	 * @param inStream 输入流
	 * @param maxWidth 最大宽度
	 * @param maxHeight 最大高度
	 * @return BufferedImage
	 * @author: liyang@hizhu.com
	 * @version: 2017-12-7 下午4:27:09
	 */
	public static BufferedImage compressImage(InputStream inStream, int maxWidth, int maxHeight){
		try {
			ScaleParameter scaleParam = new ScaleParameter(maxWidth, maxHeight);
			ImageRender rr = new ReadRender(inStream);
			ImageRender sr = new ScaleRender(rr, scaleParam);
			BufferedImage bi = sr.render().getAsBufferedImage();
			return bi;
		} catch (Throwable e) {
			log.info("xxxxx utils compressImage() error.", e);
		}
		return null;
	}
	
}
