/**
 *
 */
package ec.com.ibs.kardexpro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

/**
 * Clase de articulo.
 * @author Andres
 *
 */


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCKARTICULO")
public class ArticuloEntity extends AuditoriaBaseEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticulo;

    @Column(name = "CODIGOBARRAS", nullable = false)
    private String codigoBarras;

    @Column(name = "IDCATEGORIA")
    private Integer idCategoria;

    @Column
    private String descripcion;

    @Column
    private Double precio;

    @Column
    private Integer existencia;


    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORIA", referencedColumnName = "IDCATEGORIA", insertable = false, updatable = false)
    private CategoriaEntity categoriaEntity;
}
