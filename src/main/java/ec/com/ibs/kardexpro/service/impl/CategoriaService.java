package ec.com.ibs.kardexpro.service.impl;

import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.CategoriaEntity;
import ec.com.ibs.kardexpro.repository.ICategoriaRepository;
import ec.com.ibs.kardexpro.service.ICategoriaService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public void registrarCategoria(CategoriaEntity categoriaEntity) {
        try {
            if (categoriaEntity == null) {
                throw new KardexproException("No existen datos de categor\u00EDa para registrar.");
            }
            this.categoriaRepository.registrarCategoria(categoriaEntity);
        } catch (HibernateException e) {
            throw new KardexproException("Error al registrar la categor\u00EDa", e);
        }
    }

    @Override
    public List<CategoriaEntity> obtenerCategorias() {
        return this.categoriaRepository.obtenerCategorias();
    }

}
