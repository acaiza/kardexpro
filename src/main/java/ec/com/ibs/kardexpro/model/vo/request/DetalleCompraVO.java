package ec.com.ibs.kardexpro.model.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DetalleCompraVO implements Serializable {

    private static final long serialVersionUID = -6588400093998912468L;

    private Integer idArticulo;

    private Integer cantidad;

    private Double precio;

    private Integer existencia;
}
