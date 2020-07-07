package com.example.demo.rest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.demo.domain.ApiResponse;
import com.google.gson.Gson;
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
   // response.getWriter().write("Unauthorized");
  // response.getClass().cast(new ApiResponse(0,"LOGIN"));
    String employeeJsonString = new Gson().toJson(new ApiResponse(0,"LOGIN"));
    PrintWriter out = response.getWriter();
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    out.print(employeeJsonString);
    out.flush();  
  }
}