package ec.com.ibs.kardexpro.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SCKUSUARIOROL")
@Getter
@Setter
public class UsuarioRolEntity extends AuditoriaBaseEntity implements Serializable {

	private static final long serialVersionUID = -3748218313850586342L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuarioRol;

	@Column(name = "IDUSUARIO")
	private Integer idUsuario;

	@Column(name = "IDROL")
	private Integer idRol;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDROL", referencedColumnName = "IDROL", insertable = false, updatable = false)
	private RolEntity rol;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO", insertable = false, updatable = false)
	private UsuarioEntity usuario;

}
