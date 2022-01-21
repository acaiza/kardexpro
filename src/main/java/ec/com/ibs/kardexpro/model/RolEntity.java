package ec.com.ibs.kardexpro.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SCKROL")
@Getter
@Setter
public class RolEntity extends AuditoriaBaseEntity implements Serializable {

	private static final long serialVersionUID = 5941826674793217365L;

	@Id
	@Column(name = "IDROL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;

	@Column(name = "NOMBREROL")
	private String nombreRol;

}
