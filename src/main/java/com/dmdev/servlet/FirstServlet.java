package com.dmdev.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Stream;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramValue = req.getParameter("param");
		Map<String,String[]> parameterMap = req.getParameterMap();
		System.out.println();	
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("token", "12345");
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = headerNames.nextElement();
			System.out.println(req.getHeader(header));
		}
		try (PrintWriter writer = resp.getWriter()) {
			writer.write("Привет с первого сервлета");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (BufferedReader reader = req.getReader(); 
			Stream<String>	lines = reader.lines()) {
			lines.forEach(System.out::println);
		};
		
	}

	@Override
	public void destroy() {
		super.destroy();
	}
	
}
