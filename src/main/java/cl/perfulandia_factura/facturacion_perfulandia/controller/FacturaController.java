package cl.perfulandia_factura.facturacion_perfulandia.controller;

import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import cl.perfulandia_factura.facturacion_perfulandia.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public Factura generarFactura(@RequestBody Factura factura) {
        return facturaService.generarFactura(factura);
    }

    @GetMapping //Listo
    public List<Factura> obtenerFacturas() {
        return facturaService.obtenerTodas();
    }

    @GetMapping("/{id}") //Listo
    public Factura obtenerFactura(@PathVariable String id) {
        return facturaService.obtenerPorId(id).orElse(null);
    }

    @GetMapping("/venta/{idVenta}")
    public Factura obtenerPorVenta(@PathVariable String idVenta) {
        return facturaService.obtenerPorVenta(idVenta);
    }

}
