package ec.com.ibs.kardexpro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Objeto usuario.
 * @author Andres
 *
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "SCKUSUARIO")
public class UsuarioEntity extends AuditoriaBaseEntity implements Serializable {

	private static final long serialVersionUID = 5451079102515301085L;

	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "NOMBREUSUARIO", unique = true)
    private String nombreUsuario;

    @Column
    private String clave;


    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRolEntity> rolesUsuario;
}
