package com.exercise.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercise.course.service.JwtUserDetailsService;
import com.exercise.course.util.JwtTokenUtil;

/**
 * Helper controller to get the token and expose the swagger documentation
 * @author Luis San Martin
 *
 */
@Controller
@RequestMapping
public class RootController {

	@Value("${com.exercise.course.username}")
	private String username;

	@Value("${com.exercise.course.passphrase}")
	private String passphrase;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping("/swagger")
	public String swaggerUi() {
		return "redirect:/swagger-ui.html";
	}

	/**
	 * This mappings generates a new token
	 * @return
	 */
	@GetMapping("/token")
	public ResponseEntity<String> getToken() {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, passphrase));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
}