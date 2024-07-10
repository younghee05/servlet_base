package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello") // // ctrl + shift + o = ctrl + space 같은 느낌
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@Override    //     ( 자료형 변수명, 자료형 변수명) => 자료형이 두개가 들어간 상태 / req -> 요청, resp -> 응답
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		String name = "김영희";
////		int nums = 50;
////		int age = 20;
////		
////		resp.setContentType("text/html");
////		resp.setCharacterEncoding("utf-8");
////		
////		resp.getWriter().println(""
////				+ "<html>"
////				+ "<head>"
////				+ "<title>hello</title>"
////				+ "</head>"
////				+ "<body>"
////				+ "<h1>Hello Servlet!!!</h1>"
////				+ "</body>"
////				+ "</html>");
//		
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		System.out.println(name);
		System.out.println(age);
		
		req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
		
		
	}
	
	
}
