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
@Table(name = "SCKTIPOPAGO")
public class TipoPagoEntity extends AuditoriaBaseEntity implements Serializable {
    private static final long serialVersionUID = 2238093657414833118L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPago;

    @Column(name = "DESCRIPCIONTIPOPAGO")
    private String descripcionTipoPago;
}
