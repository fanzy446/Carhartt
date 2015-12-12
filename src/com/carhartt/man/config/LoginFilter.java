package com.carhartt.man.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

/**
 * 类过滤器，用于过滤没有session的非法连接
 * 
 * @author zanebo
 * 
 */
public class LoginFilter extends HttpServlet implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) sRequest;

		HttpServletResponse response = (HttpServletResponse) sResponse;

		HttpSession session = request.getSession();

		String url = request.getServletPath();
		String contextPath = request.getContextPath();

		if (url.equals("")) {
			url += "/";
		}
		// 登录页面的元素不过滤
		if (url.contains("/js/")
				|| url.contains("/resources/")
				|| url.startsWith("/pages/source")
				|| url.startsWith("/pages/css")
				|| url.equals("/pages/jsp/user/login.jsp")
				|| url.equals("/pages/jsp/user/index.jsp")
				|| url.equals("/pages/jsp/user/searchList.jsp")
				|| url.equals("/pages/jsp/user/productDetail.jsp")
				|| url.equals("/pages/jsp/user/spurToIndex.jsp")
				|| url.equals("/pages/jsp/user/404.jsp")
				|| url.equals("/pages/jsp/user/register.jsp")
				|| url.equals("/PageShow/showIndex")
				|| url.equals("/UserBasic/userLogin")
				|| url.equals("/UserBasic/findBackPass")
				|| url.equals("/UserBasic/register")
				|| url.equals("/UserBasic/certificate")
				|| url.equals("/UserBasic/activate")
				|| url.equals("/UserBasic/sendEmail")
				|| url.equals("/UserConsuming/searchItems")
				|| url.equals("/UserConsuming/checkComDetail")
				|| url.equals("/UserConsuming/addShoppingCart")
				|| url.equals("/UserConsuming/addFavorites")
				|| url.equals("/UserConsuming/getComments")
				|| url.equals("/pages/jsp/man/ba/login.html")
				|| url.equals("/basicMan/UserLogin")
				|| url.equals("/basicMan/UserLogout.action")
				|| url.equals("/pages/jsp/man/ba/error.html")
		) {
			filterChain.doFilter(sRequest, sResponse);
			return;
		}
		// 如果username存在而session不存在
		if (session == null) {
			session = ServletActionContext.getRequest().getSession();
		}

		if ((url.startsWith("/") && !url.startsWith("/Carhartt/pages/jsp/user/index.jsp"))) {// 若访问后台资源
			// 过滤到login

			String userName = (String) session
					.getAttribute(CarharttConfig.USERNAME);

			if (userName == null) {
				// 转入管理员登陆页面
				if(url.startsWith("/pages/jsp/man/"))
				{
					System.out.println("管理员未登录,拦截:" + url);
					System.out.println(contextPath + "/pages/jsp/man/ba/login.html");
					response.sendRedirect(contextPath + "/pages/jsp/man/ba/login.html");
				}
				else if(url.startsWith("/pages/jsp/user/"))
				{
					System.out.println("用户未登录,拦截:" + url);
					System.out.println(contextPath + "/pages/jsp/user/index.jsp");
					response.sendRedirect(contextPath + "/pages/jsp/user/index.jsp");
				}
				else
				{
					System.out.println("未登录,拦截:" + url);
					System.out.println(contextPath + "/pages/jsp/user/index.jsp");
					response.sendRedirect(contextPath + "/pages/jsp/user/index.jsp");
				}
				return;
			}
		}
		filterChain.doFilter(sRequest, sResponse);
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
