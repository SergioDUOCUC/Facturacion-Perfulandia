package cl.perfulandia_factura.facturacion_perfulandia.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.perfulandia_factura.facturacion_perfulandia.assembler.FacturaModelAssembler;
import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import cl.perfulandia_factura.facturacion_perfulandia.services.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaModelAssembler assembler;

    @Operation(summary = "Generar una nueva factura", description = "Crea y guarda una nueva factura en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Factura generada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos de entrada", content = @Content)
    })
    @PostMapping
    public ResponseEntity<EntityModel<Factura>> generarFactura(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la factura a generar", required = true, content = @Content(schema = @Schema(implementation = Factura.class))) Factura factura) {
        Factura creada = facturaService.generarFactura(factura);
        EntityModel<Factura> facturaModel = assembler.toModel(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaModel);
    }

    @Operation(summary = "Obtener todas las facturas", description = "Lista todas las facturas registradas")
    @ApiResponse(responseCode = "200", description = "Facturas obtenidas exitosamente", content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Factura>>> obtenerFacturas() {
        List<Factura> facturas = facturaService.obtenerTodas();
        if (facturas.isEmpty()) {
            return ResponseEntity.ok(CollectionModel.empty());
        }
        List<EntityModel<Factura>> facturaModels = facturas.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(facturaModels,
                linkTo(methodOn(FacturaController.class).obtenerFacturas()).withSelfRel()));
    }

    @Operation(summary = "Obtener factura por ID", description = "Busca una factura específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada", content = @Content(schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Factura>> obtenerFactura(
            @Parameter(description = "ID de la factura a buscar", example = "F123") @PathVariable String id) {
        return facturaService.obtenerPorId(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener factura por ID de venta", description = "Busca una factura asociada a una venta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada", content = @Content(schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró factura para la venta", content = @Content)
    })
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<EntityModel<Factura>> obtenerPorVenta(
            @Parameter(description = "ID de la venta asociada a la factura", example = "V789") @PathVariable String idVenta) {
        Factura factura = facturaService.obtenerPorVenta(idVenta);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Factura> facturaModel = assembler.toModel(factura);
        return ResponseEntity.ok(facturaModel);
    }
}
