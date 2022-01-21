package ec.com.ibs.kardexpro.model;

import lombok.*;

import javax.persistence.*;

/**
 * Clase de categoria del articulo.
 * @author Andres
 *
 */


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCKCATEGORIA")
public class CategoriaEntity extends AuditoriaBaseEntity{

    private static final long serialVersionUID = 1073863886050667282L;
    /**
     * Clave principal de la tabla de catgoria
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    /**
     * Descripcion de la categoria
     */
    @Column
    private String descripcion;
}
