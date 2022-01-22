package ec.com.ibs.kardexpro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "SCKCOMPRA")
public class CompraEntity extends AuditoriaBaseEntity  implements Serializable {

    private static final long serialVersionUID = 6267656185362776266L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @Column
    private Integer idPersona;

    @Column
    private Integer idTipoPago;

    @Column
    private String codigoCompra;

    @Column
    private Double totalCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona", insertable = false, updatable = false)
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoPago", referencedColumnName = "idTipoPago", insertable = false, updatable = false)
    private TipoPagoEntity tipoPago;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompraEntity> detallesCompra;
}
