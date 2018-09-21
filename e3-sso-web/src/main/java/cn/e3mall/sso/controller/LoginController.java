package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LoginService;
import cn.e3mall.sso.service.TokenService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private TokenService tokenService;
	
	@Value("${TOEKN_KEY}")
	private String TOEKN_KEY;
	
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model){
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username,String password,
			HttpServletRequest request, HttpServletResponse response){
		E3Result e3Result = loginService.userLogin(username, password);
		//判断是否登录成功 如果登陆成功 需要把token写入cookie
		if(e3Result.getStatus() == 200){
			String token = e3Result.getData().toString();
			//token(sessionId)关闭浏览器即失效 不用转码
			CookieUtils.setCookie(request, response, TOEKN_KEY, token);
		}
		//返回结果 
		return e3Result;
		
	}
	@RequestMapping(value="/user/logout/{token}")
	public String logout(@PathVariable String token){
		tokenService.deleteUserByToken(token);
		return "login";
		
	}
	
}
