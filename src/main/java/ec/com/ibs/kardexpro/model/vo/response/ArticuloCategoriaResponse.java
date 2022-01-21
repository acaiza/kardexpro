package ec.com.ibs.kardexpro.model.vo.response;

import lombok.Data;

@Data
public class ArticuloCategoriaResponse {

    private Long idArticulo;
    private String codigoBarras;
    private String descripcion;
    private Double precio;
    private Integer existencia;
    private String descripcionCategoria;
}
