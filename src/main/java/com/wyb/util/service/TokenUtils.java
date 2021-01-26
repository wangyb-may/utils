package com.wyb.util.service;


import com.wyb.util.model.dto.JwtBody;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 创建时间：2021/1/22 11:57
 * token工具对外接口
 * @author wyb
 */
public interface TokenUtils<T> {

	/**
	 * token解析，需传入token
	 * @param token token
	 * @return JwtBody
	 * @throws NoSuchAlgorithmException
	 */
	JwtBody signatureToken(String token) throws NoSuchAlgorithmException;

	/**
	 * 根据传入字段生成token
	 * @param object 需包含信息
	 * @param expireAt 过期时间，默认三十分钟
	 * @param subject 主键
	 * @return token
	 * @throws NoSuchAlgorithmException 解析异常
	 */
	String generateToken(T object, Date expireAt, String subject) throws NoSuchAlgorithmException;

}
