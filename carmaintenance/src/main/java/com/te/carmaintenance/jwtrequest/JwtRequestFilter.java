package com.te.carmaintenance.jwtrequest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.carmaintenance.bean.Admin;
import com.te.carmaintenance.jwtutil.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null;
		String adminUsername = null;
		String jwtToken = request.getHeader("Authorization");
//		log.info("===> " + jwtToken);
		if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
//			String[] split = jwtToken.split(" ");
			token = jwtToken.substring(7);
//			token = split[1]; // jwtToken.substring(7);
			System.out.println("====> " + token);
			log.info(token);
			adminUsername = jwtUtil.extractUsername(token);

		}
		if (adminUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails admin = detailsService.loadUserByUsername(adminUsername);
			if (jwtUtil.validateToken(token, admin)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						admin.getUsername(), admin.getPassword(), admin.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}


		}
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "4200");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Auth-Token, Content-Type, Accept, X-Requested-With, remember-me");
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return new AntPathMatcher().match("/login", request.getServletPath());
	}
	
	
	
	
	
	
	
	
	

}
