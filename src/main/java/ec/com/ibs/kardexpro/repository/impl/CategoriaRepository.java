package ec.com.ibs.kardexpro.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import ec.com.ibs.kardexpro.model.BaseModel;
import ec.com.ibs.kardexpro.model.CategoriaEntity;
import ec.com.ibs.kardexpro.model.QCategoriaEntity;
import ec.com.ibs.kardexpro.repository.ICategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Calse para implementar la persitencia de la categoria.
 */
@Repository
public class CategoriaRepository extends BaseModel implements ICategoriaRepository {

    @Override
    public void registrarCategoria(CategoriaEntity categoriaEntity) {
        ema.persist(categoriaEntity);
    }

    @Override
    public List<CategoriaEntity> obtenerCategorias() {
        QCategoriaEntity categoriaEntity = QCategoriaEntity.categoriaEntity;
        JPAQuery<CategoriaEntity> query = new JPAQuery<>(ema);
        return query.from(categoriaEntity)
                .where(categoriaEntity.estado.eq(Boolean.TRUE))
                .fetch();
    }
}
