package ec.com.ibs.kardexpro.repository.impl;

import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.DetalleCompraEntity;
import ec.com.ibs.kardexpro.repository.IDetalleCompraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleCompraRepository extends BaseModel implements IDetalleCompraRepository {

    @Override
    public void registrarDetalleCompra(List<DetalleCompraEntity> detallesCompra) {
        ema.clear();
        detallesCompra.forEach(ema::persist);
        ema.flush();
        ema.clear();
    }
}
