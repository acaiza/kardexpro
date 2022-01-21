package ec.com.ibs.kardexpro.security.filters;

import ec.com.ibs.kardexpro.security.util.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(JwtTokenUtil.HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(JwtTokenUtil.HEADER_AUTHORIZACION_KEY);
		if (token == null) {
			return null;
		}
		token = token.replace(JwtTokenUtil.TOKEN_BEARER_PREFIX, "");
		String userName = JwtTokenUtil.getUsernameFromToken(token);
		List<GrantedAuthority> rolesUsuario = JwtTokenUtil.getUserRoles(token);
		if (userName == null) {
			return null;
		}
		return new UsernamePasswordAuthenticationToken(userName, null, rolesUsuario);
	}
}
