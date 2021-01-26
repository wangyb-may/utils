package com.wyb.util.model.dto;

import io.jsonwebtoken.Claims;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 创建时间：2021/1/22 18:18
 *
 * @author wyb
 */
@Data
public class JwtBody {

	private String subject;

	private Object object;

	public JwtBody(Claims claims){
		if(Objects.nonNull(claims)){
			this.subject = claims.getSubject();
			Map map = new HashMap<>(claims);
			map.remove("sub");
			this.object = map.get("data");
		}
	}
}
