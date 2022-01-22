package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.QArticuloEntity;
import ec.com.ibs.kardexpro.model.QCategoriaEntity;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.repository.IArticuloRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Clase para implementar la persistencia del articulo.
 */
@Repository
public class ArticuloRepository extends BaseModel implements IArticuloRepository {

    @Override
    public ArticuloEntity registrarArticulo(ArticuloEntity articuloEntity) {
        articuloEntity.setFechaRegistro(Date.from(Instant.now()));
        articuloEntity.setEstado(Boolean.TRUE);
        ema.persist(articuloEntity);
        return articuloEntity;
    }

    @Override
    public List<ArticuloCategoriaResponse> obtenerArticulos(final Boolean obtieneStock) {
        final QArticuloEntity qArticuloEntity = QArticuloEntity.articuloEntity;
        final QCategoriaEntity qCategoriaEntity = QCategoriaEntity.categoriaEntity;
        JPAQuery<ArticuloCategoriaResponse> query = new JPAQuery<>(ema);

        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(qArticuloEntity.estado.eq(Boolean.TRUE))
                .and(qCategoriaEntity.estado.eq(Boolean.TRUE));
        if(BooleanUtils.isTrue(obtieneStock)) {
            predicate.and(qArticuloEntity.existencia.gt(0));
        }

        return query.select(Projections.bean(ArticuloCategoriaResponse.class,
                        qArticuloEntity.idArticulo,
                        qArticuloEntity.idCategoria,
                        qArticuloEntity.codigoBarras,
                        qArticuloEntity.descripcion,
                        qArticuloEntity.precio,
                        qArticuloEntity.existencia,
                        qArticuloEntity.categoriaEntity.descripcion.as("descripcionCategoria")
        )).from(qArticuloEntity)
                .innerJoin(qArticuloEntity.categoriaEntity, qCategoriaEntity)
                .where(predicate)
                .orderBy(qArticuloEntity.fechaRegistro.desc())
                .fetch();
    }

    @Override
    public void actualizaExistencia(ArticuloEntity articulo) {
        QArticuloEntity qArticuloEntity = QArticuloEntity.articuloEntity;
        JPAUpdateClause update = new JPAUpdateClause(ema, qArticuloEntity);
        update.set(qArticuloEntity.existencia, articulo.getExistencia())
                .where(qArticuloEntity.idArticulo.eq(articulo.getIdArticulo()))
                .execute();
    }


}
