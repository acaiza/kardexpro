package ec.com.ibs.kardexpro.service;

import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;

import java.util.List;

public interface IArticuloService {
    /**
     * Metodo para registrar el articulo.
     * @param articuloEntity
     * @return
     */
    ArticuloEntity registrarArticulo(ArticuloEntity articuloEntity);

    /**
     * Metodo para obtener los articulos registrados.
     * @return
     */
    List<ArticuloCategoriaResponse> obtenerArticulos();
}
