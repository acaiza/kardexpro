package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.CompraEntity;

/**
 * Clase para manejar la persistencia de la compra
 */
public interface ICompraRepository {
    /**
     * Metodo para registrar la compra
     * @param compraEntity
     */
    void registraCompra(CompraEntity compraEntity);

}
