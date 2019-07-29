package com.dyhospital.cloudhis.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;


public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);  
	
	/**
	 * get请求获得json
	 * 
	 * @param url
	 * @return
	 */
	public static String javahttpGet(String url) {

		try {
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			// final Date date = new Date();
			if (url.indexOf('?') > 0) {
				url += "&";
			} else {
				url += "?";
			}
			GetMethod postMethod = new GetMethod(url);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				 logger.info(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * get请求获得json
	 * 
	 * @param url
	 * @return
	 */
	public static String javahttpGet_old(String url) {

		try {
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			GetMethod getMethod = new GetMethod(url);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(getMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = getMethod.getResponseBodyAsString();
			} else {
				logger.debug(getMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * @param paramName 参数名称

	 * @return
	 */
	public static String javahttpPost(String paramName, String xmlInfo, String url) {

		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);// 设置连接超时时间为2秒（连接初始化时间）
			PostMethod postMethod = new PostMethod(url);
			//postMethod.setRequestBody(xmlInfo);			// 参数设置
			//postMethod.setParameter(paramName, xmlInfo);
			postMethod.addRequestHeader("Content-type","application/json; charset=utf-8");  
			postMethod.addRequestHeader("Accept", "application/json");  
			//postMethod.setRequestEntity(new RequestEntity(xmlInfo, Charset.forName("UTF-8"))); 
			RequestEntity requestEntity = new StringRequestEntity(xmlInfo);
			postMethod.setRequestEntity(requestEntity);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				logger.debug(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**

	 * @return
	 */
	public static String javahttpPost_old(String xmlInfo, String url) {

		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			PostMethod postMethod = new PostMethod(url);
			// 参数设置
			postMethod.setParameter("jsonString", xmlInfo);
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				logger.debug(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	/**
	 * 实现多个参数传递，进行远程访问并获取返回值
	 * 
	 * @param mapInfo 调用时的参数集合map
	 * @param outTime 超时时间 毫秒
	 * @return
	 */
	public static String javahttpPost(Map<String, Object> mapInfo, String url,int outTime) {

		try {
			String getStr = "";
			/** post方式 */
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(outTime);
			PostMethod postMethod = new PostMethod(url);
			// 参数设置
			// 遍历map
			if (mapInfo != null) {
				Iterator<String> iter = mapInfo.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = mapInfo.get(key);
					postMethod.setParameter(key, value + "");
				}
				// 执行postMethod
				client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
				// 执行并返回状
				int status = client.executeMethod(postMethod);
				if (status == HttpStatus.SC_OK) {					
					getStr = postMethod.getResponseBodyAsString();
				}
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}
	
	public static String javahttpPost(Map<String, Object> mapInfo, String url){
		
		return javahttpPost(mapInfo,url,10000);
	}
	
	/**
	 * 实现多个参数传递，进行远程访问并获取返回值
	 * 
	 * @updateDate 2015-10-9 上午11:27:52
	 * @updater hxo
	 * @updateRemark 闲置旧方法，新请求方法新增令牌
	 * @param mapInfo 调用时的参数集合map
	 * @return
	 */
	public static String javahttpPost_old(Map<String, Object> mapInfo, String url) {

		try {
			String getStr = "";
			/** post方式 */
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(4000);
			PostMethod postMethod = new PostMethod(url);
			// 参数设置
			// 遍历map
			if (mapInfo != null) {
				Iterator<String> iter = mapInfo.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					Object value = mapInfo.get(key);
					postMethod.setParameter(key, value + "");
				}
				// 执行postMethod
				client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
				// 执行并返回状
				int status = client.executeMethod(postMethod);
				if (status == HttpStatus.SC_OK) {
					
					getStr = postMethod.getResponseBodyAsString();
				}
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
		finally {
		}
	}

	
	/** 
	    * Get XML String of utf-8 
	    *  
	    * @return XML-Formed string 
	    */  
	    public static String getUTF8XMLString(String xml) {  
		    // A StringBuffer Object  
		    StringBuffer sb = new StringBuffer();  
		    sb.append(xml);  
		    String xmString = "";  
		    String xmlUTF8="";  
		    try {  
		    xmString = new String(sb.toString().getBytes("UTF-8"));  
		    //xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");  
		    System.out.println("utf-8 编码：" + xmlUTF8) ;  
		    } catch (UnsupportedEncodingException e) {  
		    e.printStackTrace();
		    }  
		    // return to String Formed  
		    return xmString;  
	    }  
	
	

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
	
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {	 
	    StringBuffer string = new StringBuffer();
	 
	    String[] hex = unicode.split("\\\\u");
	 
	    for (int i = 1; i < hex.length; i++) {	 
	    	//try {
		        // 转换出每一个代码点
	    		String code = hex[i].substring(0, 4);	    			    		
		        int data = Integer.parseInt(code, 16);
		        if (hex[i].length() > 4) {
		        	string.append((char) data + hex[i].substring(4));
		        } else {
		        	string.append((char) data);
		        }		        
		        // 追加成string
		        
	    	//} catch(Exception ex) {	    		
	    	//}
	    }
	 
	    return string.toString();
	}


	/**

	 * @return
	 */
	public static String javahttpPostForm(String xmlInfo, String url) {
		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			PostMethod postMethod = new PostMethod(url);
			// 参数设置
			postMethod.setParameter("jsonString", xmlInfo);
			postMethod.setRequestHeader("Content-Type", "multipart/form-data;");
			postMethod.setRequestHeader("Content-Type", "multipart/form-data;");
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			String getStr = "";
			if (status == HttpStatus.SC_OK) {
				getStr = postMethod.getResponseBodyAsString();
			} else {
				logger.debug(postMethod.getResponseBodyAsString());
			}
			client.getHttpConnectionManager().closeIdleConnections(1);
			return getStr;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		} finally {
		}
	}



    /**
     * http poet报文请求 是否http200响应
     * @return 返回内容
     */
    public static boolean javahttpPostStatus(Map<String,Object> mapInfo,String url){
        try {
            String getStr = "";
            /** post方式 */
            HttpClient client = new HttpClient();
			// 设置连接超时时间为2秒（连接初始化时间）
            client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            PostMethod postMethod = new PostMethod(url);
            // 参数设置
            // 遍历map
            if (mapInfo != null) {
                Iterator<String> iter = mapInfo.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Object value = mapInfo.get(key);
                    postMethod.setParameter(key, value + "");
                }
                // 执行postMethod
                client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
                // 执行并返回状
                int status = client.executeMethod(postMethod);
                if (status == HttpStatus.SC_OK) {
                    return true;
                }
            }
            client.getHttpConnectionManager().closeIdleConnections(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
