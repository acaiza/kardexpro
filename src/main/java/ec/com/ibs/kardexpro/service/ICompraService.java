package ec.com.ibs.kardexpro.service;

import ec.com.ibs.kardexpro.model.CompraEntity;
import ec.com.ibs.kardexpro.model.DetalleCompraEntity;
import ec.com.ibs.kardexpro.model.vo.request.CompraVO;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;

import java.util.List;

public interface ICompraService {
    /**
     * Metodo para obtener los articulos con stock.
     * @return
     */
    List<ArticuloCategoriaResponse> obtenerArticulosConStock();

    /**
     * Metodo para realizar la compra.
     * @param compraVO
     */
    void realizarCompra(CompraVO compraVO);

    /**
     * Obtiene la lista de pedidos del usuario.
     * @param userName
     * @return
     */
    List<CompraEntity> obtenerComprasPorUsuario(String userName);

}
