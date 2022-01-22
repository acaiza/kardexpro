package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.DetalleCompraEntity;

import java.util.List;

/**
 * Calse para manejar la persistencia del detalle de la compra.
 */
public interface IDetalleCompraRepository {

    /**
     * Metodo para registrar el detalle de la compra
     * @param detallesCompra
     */
    void registrarDetalleCompra(List<DetalleCompraEntity> detallesCompra);

    /**
     * Obtiene el listado de compras del usuario.
     * @param userName
     * @return
     */
    List<DetalleCompraEntity> obtenerDetallesComprasPorUsuario(String userName);
}
