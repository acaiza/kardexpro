package ec.com.ibs.kardexpro.service.util;

import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import org.apache.commons.lang3.StringUtils;

public final class AdministracionKardexUtil {
    public AdministracionKardexUtil(){

    }

    public static void validarArticuloEntity(ArticuloEntity articuloEntity){
        if (articuloEntity == null) {
            throw new KardexproException("Los datos del art\u00EDculo son obligatorios para crear el registro.");
        }
        if(StringUtils.isEmpty(articuloEntity.getCodigoBarras())){
            throw new KardexproException("El c\u00F3digo de barras esta vac\u00EDo.");
        }
        if(StringUtils.isEmpty(articuloEntity.getDescripcion())){
            throw new KardexproException("La descripci\u00F3n del art\u00EDculo esta vac\u00EDa.");
        }
        if(articuloEntity.getPrecio() == null){
            throw new KardexproException("El precio esta vac\u00EDo.");
        }
        if(articuloEntity.getExistencia() == null){
            throw new KardexproException("La existencia esta vac\u00EDa.");
        }
        if(articuloEntity.getIdCategoria() == null){
            throw new KardexproException("La categor\u00EDa esta vac\u00EDa.");
        }
    }
}
