package ec.com.ibs.kardexpro.controller;

import ec.com.ibs.kardexpro.controller.util.TipoRespuesta;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.CategoriaEntity;
import ec.com.ibs.kardexpro.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Scope(value = "request")
@RequestMapping(value = "/categoria")
public class CategoriaController {
    private static final String RESTYP = "restyp";
    private static final String MES = "mes";
    @Autowired
    private ICategoriaService categoriaService;

    @PostMapping(value = "/registrarCategoria")
    public ResponseEntity<Void> registrarCategoria(@RequestBody CategoriaEntity categoriaEntity) {
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            this.categoriaService.registrarCategoria(categoriaEntity);
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @GetMapping(value = "/obtenerCategorias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaEntity>> obtenerArticulos() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<CategoriaEntity> categorias = null;
        try {
            categorias = this.categoriaService.obtenerCategorias();
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(categorias);
    }
}
