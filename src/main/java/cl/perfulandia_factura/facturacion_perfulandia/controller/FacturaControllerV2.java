package cl.perfulandia_factura.facturacion_perfulandia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia_factura.facturacion_perfulandia.assembler.FacturaModelAssembler;
import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import cl.perfulandia_factura.facturacion_perfulandia.services.FacturaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import cl.perfulandia_factura.facturacion_perfulandia.assembler.FacturaModelAssembler;

@RestController
@RequestMapping("/api/v2/facturas")
public class FacturaControllerV2 {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaModelAssembler assembler;

    @GetMapping // Obetener todas las facturas
    public CollectionModel<EntityModel<Factura>> obtenerFacturas() {
        List<Factura> facturas = facturaService.obtenerTodas();
        List<EntityModel<Factura>> modelos = facturas.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(modelos,
                linkTo(methodOn(FacturaControllerV2.class).obtenerFacturas()).withSelfRel());
    }

    @GetMapping("/{id}") // Obtener factura por ID
    public EntityModel<Factura> obtenerFactura(@PathVariable String id) {
        Factura factura = facturaService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada: " + id));
        return assembler.toModel(factura);
    }

    @GetMapping("/venta/{idVenta}") //venta id venta
    public EntityModel<Factura> obtenerPorVenta(@PathVariable String idVenta) {
        Factura factura = facturaService.obtenerPorVenta(idVenta);
        return assembler.toModel(factura);
    }

}
