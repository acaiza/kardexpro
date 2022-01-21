package ec.com.ibs.kardexpro.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailVO implements UserDetails {

	private static final long serialVersionUID = -223803579880594750L;

	private String username;

	private String password;

	private boolean estado;

	public void setUsername(String nombreUsuario) {
		this.username = nombreUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setRolesUsuario(Collection<? extends GrantedAuthority> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	private Collection<? extends GrantedAuthority> rolesUsuario;

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return rolesUsuario;
	}

	@Override
	public boolean isEnabled() {
		return estado;
	}

}
