package ec.com.ibs.kardexpro.controller;

import ec.com.ibs.kardexpro.controller.util.TipoRespuesta;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.ArticuloEntity;
import ec.com.ibs.kardexpro.model.vo.response.ArticuloCategoriaResponse;
import ec.com.ibs.kardexpro.service.IArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Scope(value = "request")
@RequestMapping(value = "/articulo")
public class ArticuloController {
    private static final String RESTYP = "restyp";
    private static final String MES = "mes";

    @Autowired
    private IArticuloService articuloService;

    @GetMapping(value = "/obtenerArticulos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticuloCategoriaResponse>> obtenerArticulos() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<ArticuloCategoriaResponse> articulos = null;
        try {
            articulos = this.articuloService.obtenerArticulos();
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(articulos);
    }

    @PostMapping(value = "/registrarArticulo")
    public ResponseEntity<Void> registrarArticulo(@RequestBody ArticuloEntity articulo) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            this.articuloService.registrarArticulo(articulo);
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
           throw e;
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }
}
