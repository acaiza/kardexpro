package ec.com.ibs.kardexpro.controller;

import ec.com.ibs.kardexpro.controller.util.TipoRespuesta;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.CompraEntity;
import ec.com.ibs.kardexpro.model.vo.request.CompraVO;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Scope(value = "request")
@RequestMapping(value = "/compra")
/**
 * Clase para exponer las apis de la compra.
 */
public class CompraController {
    private static final String RESTYP = "restyp";
    private static final String MES = "mes";

    @Autowired
    private ICompraService compraService;

    /**
     * Metodo para obtener los articulos con existencia.
     * @return
     */
    @GetMapping(value = "/obtenerArticulosConStock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticuloCategoriaResponse>> obtenerArticulosConStock() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<ArticuloCategoriaResponse> articulos = null;
        try {
            articulos = this.compraService.obtenerArticulosConStock();
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(articulos);
    }

    /**
     * WS para realizar la compra.
     * @param compraVO
     * @return
     */
    @PostMapping(value = "/realizarCompra")
    public ResponseEntity<Void> realizarCompra(@RequestBody CompraVO compraVO) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            compraService.realizarCompra(compraVO);
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @GetMapping(value = "/obtenerComprasPorUsuario/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompraEntity>> obtenerPedidosPorUsuario(@PathVariable String userName) {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<CompraEntity> compras = Collections.emptyList();
        try {
            compras = compraService.obtenerComprasPorUsuario(userName);
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(compras);
    }
}
