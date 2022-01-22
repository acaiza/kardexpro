package ec.com.ibs.kardexpro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "SCKPERSONA")
public class PersonaEntity extends AuditoriaBaseEntity implements Serializable {
    private static final long serialVersionUID = 8018495304960633708L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;

    @Column(name = "IDUSUARIO")
    private Integer idUsuario;

    @Column(unique = true)
    private String cedula;

    @Column
    private String nombres;

    @Column
    private String apellidos;

    @Column
    private String mail;

    @Column
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO", insertable = false, updatable = false)
    private UsuarioEntity usuario;
}
