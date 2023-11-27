package com.ssafy.enjoytrip.user.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.enjoytrip.user.exception.UnAuthorizedException;
import com.ssafy.enjoytrip.user.mapper.AuthMapper;
import com.ssafy.enjoytrip.user.model.AuthRequestDto;
import com.ssafy.enjoytrip.user.model.UserResponseDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthMapper authMapper;

	public static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	private static final String SALT = "ssafySecret";
	
	private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 30; // 분단위
	private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 60; // 주단위

	@Override
	public UserResponseDto login(AuthRequestDto authRequestDto) throws SQLException {
			// 추후 BCryptPasswordEncoder 암호화 처리 필요
		return authMapper.login(authRequestDto);
	}


	@Override
	public void saveRefreshToken(String username, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("token", refreshToken);
		authMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String username) throws Exception {
		return authMapper.getRefreshToken(username);
	}

	@Override
	public void deleRefreshToken(String username) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		authMapper.deleteRefreshToken(map);
	}	

	
	
	@Override
	public <T> String createAccessToken(String key, T data) {
		return create(key, data, "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES);
	}

	@Override
	public <T> String createRefreshToken(String key, T data) {
		return create(key, data, "refresh-token", 1000 * 60 * 3* REFRESH_TOKEN_EXPIRE_MINUTES);
	}

	/**
	 * key : Claim에 셋팅될 key 값
	 * data : Claim에 셋팅 될 data 값
	 * subject : payload에 sub의 value로 들어갈 subject값
	 * expire : 토큰 유효기간 설정을 위한 값
	 * jwt 토큰의 구성 : header + payload + signature
	 */
	@Override
	public <T> String create(String key, T data, String subject, long expire) {
		// Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration), 
		// 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
		Claims claims = Jwts.claims()
				// 토큰 제목 설정 ex) access-token, refresh-token
				.setSubject(subject)
				// 생성일 설정
				.setIssuedAt(new Date()) 
				// 만료일 설정 (유효기간)
				.setExpiration(new Date(System.currentTimeMillis() + expire)); 
		
		// 저장할 data의 key, value
		claims.put(key, data); 
		
		String jwt = Jwts.builder()
				// Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅.
				.setHeaderParam("typ", "JWT")
				.setClaims(claims)
				// Signature 설정 : secret key를 활용한 암호화.
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact(); // 직렬화 처리.
		
		return jwt;
	}

	// Signature 설정에 들어갈 key 생성.
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			logger.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		return (String) this.get("user").get("userid");
	}

}
