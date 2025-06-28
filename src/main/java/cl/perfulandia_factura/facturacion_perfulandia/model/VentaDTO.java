package cl.perfulandia_factura.facturacion_perfulandia.model;


import lombok.Data;

@Data
public class VentaDTO {
    private String id;
    private Integer total;

    public VentaDTO() {}
}
