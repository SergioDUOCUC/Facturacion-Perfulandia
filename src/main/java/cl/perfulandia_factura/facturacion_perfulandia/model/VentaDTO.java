package cl.perfulandia_factura.facturacion_perfulandia.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO que representa un resumen de una venta con su ID y total")
public class VentaDTO {

    @Schema(description = "Identificador único de la venta", example = "V1")
    private String id;

    @Schema(description = "Monto total de la venta", example = "15990")
    private Integer total;

    public VentaDTO() {}
}
