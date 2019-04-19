/*package com.chehaha.wechat.util;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chehaha.common.exception.FrameworkException;
import com.chehaha.common.util.json.impl.FastjsonUtil;
import com.chehaha.wechat.api.WechatConfig;

import net.sf.json.JSONObject;

public class DefinedPost {
	

	public static RestTemplate restTemplate = new RestTemplate(); 

	*//**
	 * @param Url
	 * @param params
	 * @param c
	 * @return 通用提交
	 *//*
	public static  JSONObject myDefinedPost(String url, Object pojo) throws FrameworkException {
		String token=WechatConfig.getAccessToken();
		JSONObject postForResult = null;
		try {
			restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			// post
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> data = new HttpEntity<String>(FastjsonUtil.parse(pojo), headers);
			postForResult = restTemplate.postForEntity(url, data, JSONObject.class, token).getBody();
		} catch (Exception ex) {
			throw new FrameworkException("提交问题");
		}
		return postForResult;
	}

	*//**
	 * @param url
	 * @param pojo
	 * @return 字符串类型提交后的结果
	 * @throws FrameworkException
	 *//*
	public static String myDefinedPostForString(String url, Object pojo) throws FrameworkException  {
		String token=WechatConfig.getAccessToken();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    JSONObject jsonObj = JSONObject.fromObject(pojo);
		HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
	    String result = restTemplate.postForObject(url, formEntity, String.class,token);
	    return result;
	}
	
	
}
*/