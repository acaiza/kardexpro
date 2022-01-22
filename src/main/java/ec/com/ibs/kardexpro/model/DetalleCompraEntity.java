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
@Table(name = "SCKCOMPRADETALLE")
public class DetalleCompraEntity extends AuditoriaBaseEntity implements Serializable {

    private static final long serialVersionUID = 8082647278299984444L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleCompra;

    @Column
    private Integer idArticulo;

    @Column
    private Integer idCompra;

    @Column
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", insertable = false, updatable = false)
    private ArticuloEntity articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCompra", referencedColumnName = "idCompra", insertable = false, updatable = false)
    private CompraEntity compra;
}
