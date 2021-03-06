package com.xinyuan.haze.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xinyuan.haze.common.utils.ValidateCodeUtils;
import com.xinyuan.haze.security.shiro.ValidateCodeAuthenticationFilter;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，

 * 真正登录的POST请求由Filter完成,
 * 
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return "login";
		}
		return "redirect:";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login";
	}

	/**
	 * 生成验证码
	 * 
	 * @return byte[]
	 * @throws IOException 
	 */
	@RequestMapping("/validateCode")
	public ResponseEntity<byte[]> validateCode(HttpSession session) throws IOException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String validateCode = ValidateCodeUtils.getCode(70, 28, 4, outputStream).toLowerCase();
		
		session.setAttribute(ValidateCodeAuthenticationFilter.DEFAULT_VALIDATE_CODE_PARAM,validateCode);
		byte[] bs = outputStream.toByteArray();
		outputStream.close();
		
		return new ResponseEntity<byte[]>(bs,headers, HttpStatus.OK);
	}
}
