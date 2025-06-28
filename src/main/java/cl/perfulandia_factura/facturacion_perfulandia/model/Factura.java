package cl.perfulandia_factura.facturacion_perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "factura")
@AllArgsConstructor
@Controller
@NoArgsConstructor
@Data
public class Factura {

    @Id
    @Column(name = "ID_FACTURA")
    private String idFactura;


    @Column(name = "FECHA", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "ID_CLIENTE", nullable = false)
    private String idCliente;

    @Column(name = "ID_VENTAS", nullable = false)
    private String idVenta;


    // Getters y Setters
}