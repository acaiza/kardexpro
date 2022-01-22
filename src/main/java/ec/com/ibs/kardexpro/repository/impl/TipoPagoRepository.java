package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.QTipoPagoEntity;
import ec.com.ibs.kardexpro.model.TipoPagoEntity;
import ec.com.ibs.kardexpro.repository.ITipoPagoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoPagoRepository extends BaseModel implements ITipoPagoRepository {
    @Override
    public List<TipoPagoEntity> obtenerTiposDePago() {
        QTipoPagoEntity qTipoPagoEntity = QTipoPagoEntity.tipoPagoEntity;
        JPAQuery<TipoPagoEntity> query = new JPAQuery<>(ema);
        return query.from(qTipoPagoEntity)
                .where(qTipoPagoEntity.estado.eq(Boolean.TRUE))
                .fetch();
    }
}
