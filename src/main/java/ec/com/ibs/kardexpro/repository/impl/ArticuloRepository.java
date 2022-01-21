package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.QArticuloEntity;
import ec.com.ibs.kardexpro.model.QCategoriaEntity;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.repository.IArticuloRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

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
    public List<ArticuloCategoriaResponse> obtenerArticulos() {
        QArticuloEntity qArticuloEntity = QArticuloEntity.articuloEntity;
        QCategoriaEntity qCategoriaEntity = QCategoriaEntity.categoriaEntity;
        JPAQuery<ArticuloCategoriaResponse> query = new JPAQuery<>(ema);
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
                .where(qArticuloEntity.estado.eq(Boolean.TRUE))
                .orderBy(qArticuloEntity.fechaRegistro.desc())
                .fetch();
    }
}
