package ec.com.ibs.kardexpro.repository;

import ec.com.ibs.kardexpro.model.CategoriaEntity;

import java.util.List;

/**
 * Clase para manejar la persistencia de la categoria
 */

public interface ICategoriaRepository {

    /**
     * Metodo para registrar la categoria de un articulo.
     * @param categoriaEntity
     */
    void registrarCategoria(CategoriaEntity categoriaEntity);

    /**
     * Metodo para obtener las categorias de un articulo
     * @return
     */
    List<CategoriaEntity> obtenerCategorias();
}
