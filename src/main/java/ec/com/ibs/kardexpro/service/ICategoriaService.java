package ec.com.ibs.kardexpro.service;


import ec.com.ibs.kardexpro.model.CategoriaEntity;

import java.util.List;

public interface ICategoriaService {

    void registrarCategoria(CategoriaEntity categoriaEntity);

     List<CategoriaEntity> obtenerCategorias();
}
