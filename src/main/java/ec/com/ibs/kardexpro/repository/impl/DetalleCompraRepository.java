package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.*;
import ec.com.ibs.kardexpro.repository.IDetalleCompraRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public class DetalleCompraRepository extends BaseModel implements IDetalleCompraRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public void registrarDetalleCompra(List<DetalleCompraEntity> detallesCompra) {
        ema.clear();
        detallesCompra.stream().forEach(detalleCompra-> {
            detalleCompra.setFechaRegistro(new Date());
            detalleCompra.setEstado(Boolean.TRUE);
        });
        detallesCompra.forEach(ema::persist);
        ema.flush();
        ema.clear();
    }

    @Override
    public List<DetalleCompraEntity> obtenerDetallesComprasPorUsuario(String userName) {
        QCompraEntity qCompraEntity = QCompraEntity.compraEntity;
        QDetalleCompraEntity qDetalleCompraEntity = QDetalleCompraEntity.detalleCompraEntity;
        QPersonaEntity qPersonaEntity = QPersonaEntity.personaEntity;
        QUsuarioEntity qUsuarioEntity = QUsuarioEntity.usuarioEntity;
        QArticuloEntity qArticuloEntity = QArticuloEntity.articuloEntity;
        QCategoriaEntity qCategoriaEntity = QCategoriaEntity.categoriaEntity;
        QTipoPagoEntity qTipoPagoEntity = QTipoPagoEntity.tipoPagoEntity;

        JPAQuery<DetalleCompraEntity> query = new JPAQuery<>(ema);
        return query.from(qDetalleCompraEntity)
                .innerJoin(qDetalleCompraEntity.compra, qCompraEntity)
                .innerJoin(qCompraEntity.persona, qPersonaEntity)
                .innerJoin(qPersonaEntity.usuario, qUsuarioEntity)
                .innerJoin(qCompraEntity.tipoPago, qTipoPagoEntity)
                .innerJoin(qDetalleCompraEntity.articulo, qArticuloEntity)
                .innerJoin(qArticuloEntity.categoriaEntity, qCategoriaEntity)
                .where(qUsuarioEntity.nombreUsuario.eq(userName),
                        qDetalleCompraEntity.estado.eq(Boolean.TRUE),
                        qCompraEntity.estado.eq(Boolean.TRUE),
                        qArticuloEntity.estado.eq(Boolean.TRUE))
                .select(Projections.bean(DetalleCompraEntity.class,
                        qDetalleCompraEntity.idDetalleCompra,
                        qDetalleCompraEntity.idCompra,
                        qDetalleCompraEntity.cantidad,
                        Projections.bean(CompraEntity.class,
                                        qCompraEntity.codigoCompra,
                                        qCompraEntity.idCompra,
                                        qCompraEntity.totalCompra,
                                        qCompraEntity.fechaRegistro,
                                        Projections.bean(TipoPagoEntity.class,
                                                        qTipoPagoEntity.descripcionTipoPago)
                                                .as(qCompraEntity.tipoPago),
                                        Projections.bean(PersonaEntity.class,
                                                        qPersonaEntity.nombres,
                                                        qPersonaEntity.apellidos)
                                                .as(qCompraEntity.persona))
                                .as(qDetalleCompraEntity.compra),
                        Projections.bean(ArticuloEntity.class,
                                        qArticuloEntity.codigoBarras,
                                        qArticuloEntity.descripcion,
                                        Projections.bean(CategoriaEntity.class,
                                                        qCategoriaEntity.descripcion)
                                                .as(qArticuloEntity.categoriaEntity))
                                .as(qDetalleCompraEntity.articulo)))
                .fetch();
    }
}
