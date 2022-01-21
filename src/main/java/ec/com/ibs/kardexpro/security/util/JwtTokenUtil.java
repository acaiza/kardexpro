package ec.com.ibs.kardexpro.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public final class JwtTokenUtil {

	private JwtTokenUtil() {
	}

	private static final Clock clock = DefaultClock.INSTANCE;

	public static final String SIGN = "ibsAndres";

	private static final Long expiration = 60000L;

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";

	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	public static final String USER_ROLES = "roles";

	public static String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public static List<GrantedAuthority> getUserRoles(String token) {
		final List<String> roles = getAllClaimsFromToken(token).get(USER_ROLES, List.class);
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SIGN).parseClaimsJws(token).getBody();
	}

	public static String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(USER_ROLES, crearListaStringRoles(userDetails));
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private static List<String> crearListaStringRoles(UserDetails userDetails) {
		return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
	}

	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SIGN).compact();
	}

	private static Date calculateExpirationDate(Date createdDate) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		log.info("caduca en: {}", dt.format(new Date(createdDate.getTime() + expiration * 90)));
		return new Date(createdDate.getTime() + expiration * 90);
	}

}
