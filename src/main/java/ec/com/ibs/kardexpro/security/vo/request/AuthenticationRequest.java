package ec.com.ibs.kardexpro.security.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -7138124275262046848L;

	private String username;

    private String password;

}
