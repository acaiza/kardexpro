package ec.com.ibs.kardexpro.repository.impl;

import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.CompraEntity;
import ec.com.ibs.kardexpro.repository.ICompraRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CompraRepository extends BaseModel implements ICompraRepository {

    @Override
    public void registraCompra(CompraEntity compraEntity) {
        ema.persist(compraEntity);
    }
}
