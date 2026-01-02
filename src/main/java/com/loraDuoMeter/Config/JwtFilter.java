package com.loraDuoMeter.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.loraDuoMeter.Service.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            
        	String token = authHeader.substring(7);

        	try {
                Claims claims = jwtUtil.validateToken(token);
                
                String username = claims.getSubject();

                UserDetails user = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
        		
			}	catch (ExpiredJwtException e) {
				System.out.println(e.getMessage());
	            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            res.setContentType("application/json");
	            res.getWriter().write("{\"error\":\"Access token has been expired, please do login again!\"}");
	            return;

	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	        	res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            res.setContentType("application/json");
	            res.getWriter().write("{\"error\":\"Invalid token\"}");
	            return;
			}
        }

        chain.doFilter(req, res);
    }
}

