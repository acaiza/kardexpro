package ec.com.ibs.kardexpro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase base de campos de auditoria auditoria
 * @author Andres
 *
 */
@MappedSuperclass
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class AuditoriaBaseEntity implements Serializable {


    private static final long serialVersionUID = 9059995324353001854L;

    @Column(name = "ESTADO")
    protected boolean estado;

    /**
     * Fecha de registro
     */
    @Column(name = "FECHAREGISTRO")
    protected Date fechaRegistro;
    /**
     * Ip del usuario que realizo la ultima actualizacion.
     */
    @Column(name = "IPREGISTRO")
    private String ipRegistro;

    /**
     * Ip del usuario que realizo la ultima actualizacion.
     */
    @Column(name = "IPMODIFICACION")
    private String ipModificacion;

    /**
     * Campo para el control de versiones
     */
    @Column(name = "VERSION")
    private Integer version;
}
