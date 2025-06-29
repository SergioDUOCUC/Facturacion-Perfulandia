package cl.perfulandia_factura.facturacion_perfulandia.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "factura")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Entidad que representa una factura generada por una venta")
public class Factura {

    @Id
    @Column(name = "ID_FACTURA")
    @Schema(description = "Identificador único de la factura", example = "F1")
    private String idFactura;

    @Column(name = "FECHA", nullable = false)
    @Schema(description = "Fecha de emisión de la factura", example = "2025-06-29")
    private LocalDate fechaEmision;

    @Column(name = "metodo_pago", nullable = false)
    @Schema(description = "Método de pago utilizado", example = "Tarjeta de Crédito")
    private String metodoPago;

    @Column(name = "ID_CLIENTE", nullable = false)
    @Schema(description = "ID del cliente asociado a la factura", example = "CL1")
    private String idCliente;

    @Column(name = "ID_VENTAS", nullable = false)
    @Schema(description = "ID de la venta asociada a la factura", example = "V1")
    private String idVenta;
}
