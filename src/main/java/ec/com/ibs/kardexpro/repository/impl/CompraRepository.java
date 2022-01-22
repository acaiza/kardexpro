package ec.com.ibs.kardexpro.repository.impl;

import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.CompraEntity;
import ec.com.ibs.kardexpro.repository.ICompraRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;

/**
 * Clase para manejar la persistencia de las compras.
 */
@Repository
public class CompraRepository extends BaseModel implements ICompraRepository {

    @Override
    public void registraCompra(CompraEntity compraEntity) {
        compraEntity.setFechaRegistro(Date.from(Instant.now()));
        compraEntity.setEstado(Boolean.TRUE);
        ema.persist(compraEntity);
    }

}
