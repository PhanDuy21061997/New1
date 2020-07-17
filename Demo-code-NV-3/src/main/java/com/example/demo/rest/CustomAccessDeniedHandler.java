package com.example.demo.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.demo.domain.ApiResponse;
import com.google.gson.Gson;
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
      throws IOException, ServletException {
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
   // response.getWriter().write("Access Denied!");
    String employeeJsonString = new Gson().toJson(new ApiResponse(0,"ERROR Permison"));
    PrintWriter out = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    out.print(employeeJsonString);
    out.flush(); 
  }
}