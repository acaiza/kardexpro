package ec.com.ibs.kardexpro.controller;

import ec.com.ibs.kardexpro.controller.util.TipoRespuesta;
import ec.com.ibs.kardexpro.exception.KardexproException;
import ec.com.ibs.kardexpro.model.TipoPagoEntity;
import ec.com.ibs.kardexpro.service.ITipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope(value = "request")
@RequestMapping(value = "/tipopago")
public class TipoPagoController {
    private static final String RESTYP = "restyp";
    private static final String MES = "mes";

    @Autowired
    private ITipoPagoService tipoPagoService;

    @GetMapping(value = "/obtenerTiposPago", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TipoPagoEntity>> obtenerTiposPago() {
        HttpHeaders responseHeaders = new HttpHeaders();
        List<TipoPagoEntity> tiposPago = null;
        try {
            tiposPago = this.tipoPagoService.obtenerTiposDePago();
            responseHeaders.set(RESTYP, TipoRespuesta.SUCCESS.toString());
        } catch (KardexproException e) {
            responseHeaders.set(RESTYP, TipoRespuesta.ERROR.toString());
            responseHeaders.set(MES, e.getMessage());
        }
        return ResponseEntity.ok().headers(responseHeaders).body(tiposPago);
    }
}
