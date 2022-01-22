package ec.com.ibs.kardexpro.service.util;

import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.vo.request.CompraVO;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Clase util para kardex.
 */
public final class AdministracionKardexUtil {
    public AdministracionKardexUtil(){

    }

    /**
     * Validar datos requeridos para registrar el articulo.
     * @param articuloEntity
     */
    public static void validarArticuloEntity(ArticuloEntity articuloEntity){
        if (articuloEntity == null) {
            throw new KardexproException("No existe art\u00EDculo para registrar.");
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

    /**
     * Valida datos necesarios para la compra.
     * @param compraVO
     */
    public static void validarRealizarCompra(CompraVO compraVO){
        if (compraVO == null) {
            throw new KardexproException("No existe datos de la compra para registrar.");
        }
        if (compraVO.getIdTipoPago() == null) {
            throw new KardexproException("No existe el tipo de pago.");
        }
        if (compraVO.getDetalleCompra() == null) {
            throw new KardexproException("No existen art\u00EDculos seleccionados para realizar la compra.");
        }
        if (compraVO.getUsername() == null && compraVO.getUsername().trim().isEmpty()) {
            throw new KardexproException("No se logro obtener el usuario de la compra.");
        }
    }

    public static String generarCodigoCompra() {
        Random random = new Random();
        IntStream intStream = random.ints(7, 1, 7);
        StringBuilder codigo = new StringBuilder();
        intStream.forEach(codigo::append);
        return codigo.toString();
    }

    /**
     * Se calcula la cantidad y el precio total
     * @param compraVO
     * @return
     */
    public static Double calcularTotalCompra(CompraVO compraVO) {
        //Se suma los bultos auotorizados
        return compraVO.getDetalleCompra().stream()
                .mapToDouble(articulo -> articulo.getPrecio() * articulo.getCantidad())
                .sum();
    }

}
