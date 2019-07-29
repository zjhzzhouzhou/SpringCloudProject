package com.dyhospital.cloudhis.common.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpClientUtil {
	public static Log log = LogFactory.getLog(HttpClientUtil.class);
	private static MultiThreadedHttpConnectionManager connectionManager;
	private static HttpClient client;
	/**
	 * maximum number of connections allowed per host
	 */
	private static int maxHostConnections = 500;
	
	/**
	 * maximum number of connections allowed overall
	 */
	private static int maxTotalConnections = 500;
	
	/**
	 * the timeout until a connection is etablished
	 */
	private static int connectionTimeOut = 3000;
	
	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setDefaultMaxConnectionsPerHost(maxHostConnections);
		params.setMaxTotalConnections(maxTotalConnections);
		params.setConnectionTimeout(connectionTimeOut);
		params.setSoTimeout(connectionTimeOut);
		connectionManager.setParams(params);
		client = new HttpClient(connectionManager);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
	}

	public static String requestGet(String url) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		return getResponseStr(getMethod, null);
	}
	
	public static String requestGet(String url,NameValuePair[] values) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.setQueryString(values);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		return getResponseStr(getMethod, parseNameValuePair(values));
	}
	
	public static String requestGet(String url, int outTime) {
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		return getResponseStr(getMethod, null);
	}
	
	public static int getRequestStatusCode(String url, Map<String, String> headerMap, int outTime){
		// 模拟访问Header参数
		List<String> headerList = new ArrayList<String> ();
		headerList.add("X-UP-BEAR-TYPE".toLowerCase());
		headerList.add("x-up-calling-line-id".toLowerCase());
		headerList.add("referer".toLowerCase());
		headerList.add("User-Agent".toLowerCase());
		headerList.add("WDAccept-Encoding".toLowerCase());
		

		int statusCode = 0;
		GetMethod getMethod = new GetMethod(url);
		try {
			StringBuffer sb = new StringBuffer ();
			if(headerMap != null && headerMap.size() > 0){
				for(Iterator<String> it = headerMap.keySet().iterator(); it.hasNext(); ){
					String key = it.next();
					if(headerList.contains(key.toLowerCase())){
						getMethod.setRequestHeader(key, headerMap.get(key));
						sb.append(key + "=" + headerMap.get(key) + ", ");
					}
				}
			}
			log.info("[URL=" + url + ", " + sb.toString() + "]");
			statusCode = client.executeMethod(getMethod);
			if(statusCode == 200 
					&& getMethod.getResponseHeader("isError") != null
					&& StringUtil.nullToBoolean(getMethod.getResponseHeader("isError").getValue())){
				statusCode = 500;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}
		return statusCode;
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值List
	 * @return 调用得到的字符串
	 */
	public static String httpClientPost(String url,List<NameValuePair[]> values){
		PostMethod postMethod = new PostMethod(url);
		//将表单的值放入postMethod中
		for (NameValuePair[] value : values) {
			postMethod.addParameters(value);
		}
		return getResponseStr(postMethod, parseNameValuePair(values));

	}
	
	private static String parseNameValuePair(List<NameValuePair[]> values){
		StringBuilder sb = new StringBuilder();
		if(values==null || values.size()==0){
			return null;
		}
		for(NameValuePair[] value : values){
			sb.append(parseNameValuePair(value));
		}
		return sb.toString();
	}
	
	
	private static String parseNameValuePair(NameValuePair[] nv){
		StringBuilder sb = new StringBuilder();
		if(nv==null || nv.length==0){
			return null;
		}
		for(NameValuePair kv : nv){
			sb.append(kv.getName()).append("=").append(kv.getValue()).append("&");
		}
		return sb.toString();
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值
	 * @return 调用得到的字符串
	 */
	public static String httpClientPost(String url, NameValuePair[] values){
		List<NameValuePair[]> list = new ArrayList<NameValuePair[]>();
		list.add(values);
		return httpClientPost(url, list);
	}
	
	/**
	 * 使用get方式调用
	 * @return 调用得到的字符串
	 */
	public static String httpClientGet(String url){
		GetMethod getMethod = new GetMethod(url);		
		getMethod.addRequestHeader("NoAuth", "NoAuth");			
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		return getResponseStr(getMethod, null);

	}
	
	/**
     * 使用get方式调用，传入头参数。
     * @return 调用得到的字符串
     */
    public static String httpClientGet(String url, Map<?, ?> headerMap){
        GetMethod getMethod = new GetMethod(url);
        
        for (Map.Entry<?, ?>  m : headerMap.entrySet()) {
            if (!StringUtil.isNullStr(m.getKey())) {
                getMethod.addRequestHeader(String.valueOf(m.getKey()), String.valueOf(m.getValue()));     
            }
        }
                    
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        return getResponseStr(getMethod, null);

    }
	
	
	/**
	 * 将MAP转换成HTTP请求参数
	 * @return
	 */
	public static NameValuePair[] praseParameterMap(Map<String, String> map){
		
		NameValuePair[] nvps = new NameValuePair[map.size()];
		
		Set<String> keys = map.keySet();
		int i=0;
		for(String key:keys){
			nvps[i] = new NameValuePair();
			nvps[i].setName(key);
			nvps[i].setValue(map.get(key));
			
			i++;
		}
		              
		return nvps;
	}
	
	/**
	 * 使用post方式调用
	 * @param url 调用的URL
	 * @param values 传递的参数值
	 * @param xml 传递的xml参数
	 * @return
	 */
	public static String httpClientPost(String url, NameValuePair[] values, String xml){
		StringBuilder sb = new StringBuilder();
		
		log.debug(" http url :" + url);
	     for (NameValuePair nvp : values) {
	    	 log.debug(" http param :" + nvp.getName() + " = " + nvp.getValue());
	      }

		
		PostMethod method = new PostMethod(url);
		method.setQueryString(values);
		method.addRequestHeader("Content-Type", "text/xml;charset=UTF-8");
		RequestEntity reis = null;
		InputStream input = null;
		InputStream is = null;
		BufferedReader dis = null;
		try {
			input = new ByteArrayInputStream(xml.getBytes("utf-8"));
			reis = new InputStreamRequestEntity(input);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			method.setRequestEntity(reis);
			client.executeMethod(method);

			// 以流的行式读入，避免中文乱码
			is = method.getResponseBodyAsStream();
			dis = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String str = "";
			while ((str = dis.readLine()) != null) {
				sb.append(str);
			}
		} catch (HttpException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
			try {
				if (dis != null) {
                    dis.close();
                }
				if (is != null) {
                    is.close();
                }
				if (input != null) {
                    input.close();
                }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
	
	
	/**
	 * 发送post或get请求获取响应信息
	 * @param method	http请求类型,post或get请求
	 * @return			服务器返回的信息
	 */
    public static String getResponseStr(HttpMethodBase method, Object args) {
        StringBuilder sb = new StringBuilder();
        URI url = null;
        long beginTimes = System.currentTimeMillis();
        try {
            url = method.getURI();
            int statusCode = client.executeMethod(method);
            if (statusCode != 200) {
                log.error("HttpClient begin at " + beginTimes + ", HttpClient Error : statusCode = " + statusCode
                    + ", uri :" + method.getURI());
                return "";
            }
            // 以流的行式读入，避免中文乱码
            InputStream is = method.getResponseBodyAsStream();
            BufferedReader dis = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String str = "";
            while ((str = dis.readLine()) != null) {
                sb.append(str);
            }
        }
        catch (Exception e) {
            log.error("HttpClient begin at " + beginTimes + ", 调用远程出错;发生网络异常! uri :" + url + " params: " + args, e);
            // e.printStackTrace();
        }
        finally {
            // 关闭连接
            method.releaseConnection();
        }

        log.info("HttpClient begin at " + beginTimes + ", cost " + (System.currentTimeMillis() - beginTimes) + " ms,"
            + " request url " + url + " , params: " + args);
        return sb.toString();
    }
    
    public static String httpClientPostByStream(String url, String stream){
    	return httpClientPostByStream(url, stream, "application/x-www-form-urlencoded");
    }
    
    public static String httpClientPostByStream(String url, String stream, String contentType){
    	PostMethod postMethod = null;
		RequestEntity reis = null;
		InputStream input = null;
		InputStream is = null;
		BufferedReader dis = null;
		StringBuilder res = new StringBuilder();
		long beginTimes = System.currentTimeMillis();
		try {
			postMethod = new PostMethod(url);
			postMethod.addRequestHeader("Content-Type", contentType); //application/x-www-form-urlencoded
			input = new ByteArrayInputStream(stream.getBytes("utf-8"));
			reis = new InputStreamRequestEntity(input);
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler());

			postMethod.setRequestEntity(reis);
			int statusCode = client.executeMethod(postMethod);
			
			if (statusCode != 200) {
				log.error("HttpClient begin at " + beginTimes
						+ ", HttpClient Error : statusCode = " + statusCode
						+ ", uri :" + postMethod.getURI());
				return "";
			}

			// 以流的行式读入，避免中文乱码
			is = postMethod.getResponseBodyAsStream();
			dis = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String str = "";
			while ((str = dis.readLine()) != null) {
				res.append(str);
			}
		} catch (Exception e) {
			log.error("invoke http error!",e);
		} finally {
			try {
				if (postMethod != null){
					postMethod.releaseConnection();
				}
				if (dis != null) {
                    dis.close();
                }
				if (is != null) {
                    is.close();
                }
				if (input != null) {
                    input.close();
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("HttpClient begin at " + beginTimes + ", cost " + (System.currentTimeMillis() - beginTimes) + " ms,"
	            + " request url " + url + " , post stream: " + stream);
		return res.toString();
    }
    
}
