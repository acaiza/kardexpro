package ec.com.ibs.kardexpro.service.impl;

import com.querydsl.core.QueryException;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.vo.request.DetalleCompraVO;
import ec.com.ibs.kardexpro.repository.ICompraRepository;
import ec.com.ibs.kardexpro.repository.IDetalleCompraRepository;
import ec.com.ibs.kardexpro.repository.IPersonaRepository;
import ec.com.ibs.kardexpro.service.util.AdministracionKardexUtil;
import ec.com.ibs.kardexpro.model.CompraEntity;
import ec.com.ibs.kardexpro.model.DetalleCompraEntity;
import ec.com.ibs.kardexpro.model.vo.request.CompraVO;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.repository.IArticuloRepository;
import ec.com.ibs.kardexpro.service.ICompraService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class CompraService implements ICompraService {

    @Autowired
    private IArticuloRepository articuloRepository;

    @Autowired
    private IPersonaRepository personaRepository;

    @Autowired
    private ICompraRepository compraRepository;

    @Autowired
    private IDetalleCompraRepository  detalleCompraRepository;

    @Override
    public List<ArticuloCategoriaResponse> obtenerArticulosConStock() {
        return articuloRepository.obtenerArticulos(Boolean.TRUE);
    }

    @Override
    public void realizarCompra(CompraVO compraVO) {
        try {
            //1.0 Valida datos requeridos para la compra
            AdministracionKardexUtil.validarRealizarCompra(compraVO);
            //2.0 Registrar la compra
            CompraEntity compraEntity = this.registrarCompra(compraVO);
            //3.0 Registrar los detalles de la compra
            this.registrarDettalleCompra(compraVO, compraEntity.getIdCompra());
            //4.0 Actualizar la existencia(stock) de los articulos
            actualizarExistenciaArticulos(compraVO);
        } catch (KardexproException e){
            throw new KardexproException("Error al registrar la compra", e);
        }
    }

    private Integer obtenerIdPersonaPorNombreUsuario(String userName) {
        try {
            return personaRepository.obtenerIdPersonaPorNombreUsuario(userName);
        } catch (QueryException e) {
            throw new KardexproException("Error al obtener el id de la persona", e);
        }
    }

    /**
     * Registrar la compra.
     * @param compraVO
     * @return
     */
    private CompraEntity registrarCompra(CompraVO compraVO) {
        //1.0 Obtener y validar la persona que realiza la compra
        Integer idPersona = obtenerIdPersonaPorNombreUsuario(compraVO.getUsername());
        CompraEntity nuevaCompra = CompraEntity.builder()
                .codigoCompra(AdministracionKardexUtil.generarCodigoCompra())
                .idPersona(idPersona)
                .totalCompra(AdministracionKardexUtil.calcularTotalCompra(compraVO))
                .idTipoPago(compraVO.getIdTipoPago())
                .build();
        this.compraRepository.registraCompra(nuevaCompra);
        return nuevaCompra;
    }

    /**
     * Metodo para registrar los detalles.
     * @param compraVO
     * @param codigoCompra
     */
    private void registrarDettalleCompra(CompraVO compraVO, Integer codigoCompra) {
        List<DetalleCompraEntity> detalles = new ArrayList<>(compraVO.getDetalleCompra().size());
        compraVO.getDetalleCompra().forEach(detalle -> detalles.add(DetalleCompraEntity
                .builder()
                .idCompra(codigoCompra)
                .cantidad(detalle.getCantidad())
                .idArticulo(detalle.getIdArticulo())
                .build())
        );

        this.detalleCompraRepository.registrarDetalleCompra(detalles);
    }

    private void actualizarExistenciaArticulos(CompraVO compraVO) {
        List<ArticuloEntity> articulos = new ArrayList<>();
        compraVO.getDetalleCompra().forEach(detalle -> articulos.add(ArticuloEntity
                .builder()
                .idArticulo(detalle.getIdArticulo())
                .existencia(detalle.getExistencia() - detalle.getCantidad())
                .build()));
        articulos.forEach(this::actualizaExistencia);
    }

    public void actualizaExistencia(ArticuloEntity articulo) {
        try {
            this.articuloRepository.actualizaExistencia(articulo);
        } catch (HibernateException e) {
            throw new KardexproException("Error al actualizar la existencia", e);
        }
    }
}
