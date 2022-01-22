package ec.com.ibs.kardexpro.model.vo.request;

import ec.com.ibs.kardexpro.model.DetalleCompraEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CompraVO implements Serializable {
    private static final long serialVersionUID = 2876508814338849113L;
    private Integer idTipoPago;

    private String username;

    private List<DetalleCompraVO> detalleCompra;
}
