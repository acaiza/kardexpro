package ec.com.ibs.kardexpro.service.impl;

import com.querydsl.core.QueryException;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.repository.IArticuloRepository;
import ec.com.ibs.kardexpro.service.IArticuloService;
import ec.com.ibs.kardexpro.service.util.AdministracionKardexUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Slf4j
@Service
public class ArticuloService implements IArticuloService {

    @Autowired
    private IArticuloRepository articuloRepository;

    private static Logger logger = LoggerFactory.getLogger(ArticuloService.class);
    @Override
    public ArticuloEntity registrarArticulo(ArticuloEntity articuloEntity) {
        try {
            //1.0 Valida los campos requeridos del articulo
            AdministracionKardexUtil.validarArticuloEntity(articuloEntity);
            //2.0 Registra el articulo
            return articuloRepository.registrarArticulo(articuloEntity);
        } catch (KardexproException e) {
            logger.error("Error: Al registrar el articulo",e);
            throw e;
        } catch (HibernateException e) {
            logger.error("Error: Al registrar el articulo",e);
            throw new KardexproException("Error no se registro el art\u00EDculo", e);
        }
    }

    @Override
    public List<ArticuloCategoriaResponse> obtenerArticulos() {
        try {
            return articuloRepository.obtenerArticulos(Boolean.FALSE);
        } catch (QueryException e) {
            logger.error("Erro al obtener los articulos registrados",e);
            throw new KardexproException("Erro al obtener los art\u00EDculos registrados", e);
        }
    }
}
