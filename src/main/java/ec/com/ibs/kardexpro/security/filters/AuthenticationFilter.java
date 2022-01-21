package ec.com.ibs.kardexpro.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.ibs.kardexpro.model.vo.UserDetailVO;
import ec.com.ibs.kardexpro.security.util.JwtTokenUtil;
import ec.com.ibs.kardexpro.security.vo.request.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthenticationRequest credenciales = new ObjectMapper().readValue(request.getInputStream(),
					AuthenticationRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credenciales.getUsername(), credenciales.getPassword()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		UserDetailVO userLogged = (UserDetailVO) auth.getPrincipal();
		String token = JwtTokenUtil.generateToken(userLogged);
		response.setHeader("Access-Control-Expose-Headers", JwtTokenUtil.HEADER_AUTHORIZACION_KEY);
		response.addHeader(JwtTokenUtil.HEADER_AUTHORIZACION_KEY, JwtTokenUtil.TOKEN_BEARER_PREFIX + " " + token);
	}

}
