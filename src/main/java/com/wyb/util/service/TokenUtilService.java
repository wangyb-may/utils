package com.wyb.util.service;

import com.wyb.util.exception.TokenException;
import com.wyb.util.model.dto.JwtBody;
import com.wyb.util.model.enums.TokenReturnCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 创建时间：2021/1/22 15:10
 * token工具实现类
 * @author wyb
 */
@Service
@Slf4j
public class TokenUtilService implements TokenUtils{

	/**
	 * 加密方式
	 */
	private static final String ALGORITHM_FAMILY_NAME = "RSA";

	/**
	 * 私钥，加密用
	 */
	private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKXTUPam3O/saqigmyWwcmmIWrjPs0WgIgRD5PgmrOLpRkbaRSTG1bUZ/hZdqpx8AknH4ghM9vMSbPVM9ePVkVeX0CxvyECqXtfKhqqYaRQ2zKsMI4tDUvxVrIU+xdSlegJTDB99dLotekUu9QyUs4goipvuX0jq1Pdw6LBCzb0NpV29/8cx4SBEDMhLk7sDyQLKMFru96lxu8V7pabwXzUl06YDiKk9ORVw6dwybx+/PgcOOV4gf2Lt7tg+85phwXwVZIjdVQwTDHxJf0vnfRRQZdglW7+6pFN4U7zPsc0JsneD3vAdQLdNdYr7ao0hmIuw66Ty36RarlwP21rUWVAgMBAAECggEAEyUKbnOfdgCypG4tIUyPFLwN4srDB19c4/INXRsChDzVs3+oOOryRqxonttlAhDteSNeIEnAGfaHFDD78GI0rU/+CCBYWDzQ8KdK9lc2k2rs5q7GcOoeYdnA0e8qyU28CiCbOnt8gq6pf8cp7desXMlS94m+b7Q+LryeeoW9Xzy0qg3Kd2liXSWxSfg7WwJKJqaHXOTo9cvc8uF4s3njpOFP7+PyJSIbxu4MEnVAldFibsD9cUH2vqIrPDAI3VRkUCrLw+72jz0DE1nIr7THpbGBZCQNpclInZEhwo1Z3a0aR9sDPdfIkcvkDhp0FYFsB8iJZp40E5vU0NeQVan5EQKBgQD3ruDbgipe9XA4BG0Nk8oPKxTv5Ws8hyQznPULx00nJFzOlKm5tPNdetxGqIxXwD25A/fX7waNXrnD+srFULQEPov2J2YEkVXIMYr55sFB/GyWnp20mZ8jGxIxJrelRUS7wEj+X8HEQZ8zYh5QWZtM9pYdNWvRni6+Zaw6sjk2QwKBgQCPApshPU8iEzQ0mxsFVuLBwFECMISUC9E78rZCBwhfzZO90Np1/vM1lBz4DKo+yj+iaL3PoBk/oM3slSEjNXkaxMtXW3Xji0KwvEGiTcAF/phSOdPA0GiLCVkuCSNwaB5zMbwXOKokhZ129n9FHFsBKOHyG4ppvyCCq8WubTLTRwKBgBGlPtXEnzomqDkFb7OA7OtJuXMh2BL22chnjTmqmD+KoqYBB1p4nsHxJ9JM3bq+smcE9lpG6COYNQnWSy3lgv2uaxA9XFJE/GSHywvkua32ytGsOTYNn73DhvdDd9/pYG0z1L9ymP5fYnUpr1kwGRVRl/46uRS0/UWJPszrt5G7AoGAYcvlbCCyCdpaHBNpsggNv3OVskqdj2RZbIZ5LsQCbQl3nBnEG2cCyMQF33dBwPir9ZNtIhwYyBNeXwhIhfxeWfj0GUQ5ZSbpr1S+CZDj3PWTEr/q1/NtWo9FBh9Htent0NbVCog8igc5fI3q0McoUK21fcxpphEG2XVAFR0l4acCgYA+CWZDamvQ19XXJd1UTTWSwq6rlLXt/RPKNZZwsBYH/jdiZboB7JIDbQ+3H6LQ+ZBVc0JMWn5lcUh0VdtvzCdcn8C4XZO+g55OrdpC6u+0ygpWu3dpC/Ri3h2cGbzgLBnQP/aZyQM7+zugjwBM3zMJT8LXq5eyHdjtscGGVUHfkQ==";

	/**
	 * 公钥，解密用
	 */
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAil01D2ptzv7GqooJslsHJpiFq4z7NFoCIEQ+T4Jqzi6UZG2kUkxtW1Gf4WXaqcfAJJx+IITPbzEmz1TPXj1ZFXl9Asb8hAql7XyoaqmGkUNsyrDCOLQ1L8VayFPsXUpXoCUwwffXS6LXpFLvUMlLOIKIqb7l9I6tT3cOiwQs29DaVdvf/HMeEgRAzIS5O7A8kCyjBa7vepcbvFe6Wm8F81JdOmA4ipPTkVcOncMm8fvz4HDjleIH9i7e7YPvOaYcF8FWSI3VUMEwx8SX9L530UUGXYJVu/uqRTeFO8z7HNCbJ3g97wHUC3TXWK+2qNIZiLsOuk8t+kWq5cD9ta1FlQIDAQAB";


	@Override
	public JwtBody signatureToken(String token) {

		Jws<Claims> claimsJws = authToken(token);

		return new JwtBody(claimsJws.getBody());
	}

	@Override
	public String generateToken(Object object, Date expireAt, String subject) {
		//建立jwt
		JwtBuilder jwtBuilder = Jwts.builder();

		//设置jwt的body
		subject = subject != null ? subject: UUID.randomUUID().toString();
		Map<String,Object> objectMap = new HashMap<>();
		if(Objects.nonNull(object)){
			objectMap.put("data",object);
		}
		jwtBuilder.setClaims(objectMap).setSubject(subject);

		//设置过期时间-时间设置要放在最后，否则设置了body之后会把时间盖掉，无法获取到时间
		if(Objects.isNull(expireAt)){
			log.info("没有设置过期时间,自动设置过期时间三十分钟");
			long currentTime = System.currentTimeMillis();
			currentTime += 30*60*1000;
			Date newDate = new Date(currentTime);
			jwtBuilder.setExpiration(newDate);
		}else{
			//设置过期时间
			jwtBuilder.setExpiration(expireAt);
		}
		//生成加密jwt
		return jwtBuilder.signWith(SignatureAlgorithm.RS256,privateKeyFromBase64()).compact();
	}



	/**
	 * 解析token
	 * @param token token
	 * @return claims
	 */
	private Jws<Claims> authToken(String token){
		if(null == token){
			log.info("本次请求token不存在");
			throw new TokenException(TokenReturnCode.RESOLVE_FAILED);
		}
		Jws<Claims> claimsJws;
		//解析token
		try {
			//如果传来的token不对，会对异常进行捕获
			claimsJws = Jwts.parser().setSigningKey(publicKeyFromBase64()).parseClaimsJws(token);
		}catch (JwtException e){
			log.error("token:\"{}\" 不正确",token);
			log.error(e.getMessage());
			throw new TokenException(TokenReturnCode.RESOLVE_FAILED);
		}

		return claimsJws;
	}

	/**
	 * 公钥64位序列化
	 * @return PublicKey
	 */
	private static PublicKey publicKeyFromBase64() {
		try {
			byte[] keyBytes = Base64Utils.decodeFromString(PUBLIC_KEY);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_FAMILY_NAME);
			return keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	/**
	 * 私钥64位序列化
	 * @return PrivateKey
	 */
	private static PrivateKey privateKeyFromBase64() {
		try {
			byte[] keyBytes = Base64Utils.decodeFromString(PRIVATE_KEY);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_FAMILY_NAME);
			return keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			throw new IllegalArgumentException(ex);
		}
	}


	/**
	 * 注意：下面都是生成密钥对相关方法，除特殊情况外无需调用
	 * main 方法用于生成密钥对，配置密钥时使用
	 * 已经生成好，考虑后期添加入配置中，目前写成final，公私密钥必须同时生成
	 * @param args args
	 * @throws NoSuchAlgorithmException 解析异常
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyPair keyPair = generateKeyPair();
		String privateKey = base64Encode(keyPair.getPrivate());
		String publicKey = base64Encode(keyPair.getPublic());
		System.out.printf("Private Key: %s\nPublic Key: %s", privateKey, publicKey);
	}

	/**
	 * 生成密钥对
	 *
	 * @return KeyPair
	 * @throws NoSuchAlgorithmException 解析异常
	 */
	private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		// algorithm RSA
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_FAMILY_NAME);
		return keyPairGenerator.genKeyPair();
	}

	/**
	 * 把私钥转化成base64字符串
	 *
	 * @param key 密钥
	 * @return 序列化后得密钥
	 */
	private static String base64Encode(PrivateKey key) {
		return new String(Base64Utils.encode(key.getEncoded()));
	}

	/**
	 * 把公钥转化成base64字符串
	 * @param key 密钥
	 * @return 序列化后得密钥
	 */
	private static String base64Encode(PublicKey key) {
		return new String(Base64Utils.encode(key.getEncoded()));
	}


}
