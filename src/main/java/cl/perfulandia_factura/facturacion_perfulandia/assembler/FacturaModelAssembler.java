package cl.perfulandia_factura.facturacion_perfulandia.assembler;

import cl.perfulandia_factura.facturacion_perfulandia.controller.FacturaControllerV2;
import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FacturaModelAssembler implements RepresentationModelAssembler<Factura, EntityModel<Factura>> {

    @Override
    public EntityModel<Factura> toModel(Factura factura) {
        return EntityModel.of(factura,

                linkTo(methodOn(FacturaControllerV2.class).obtenerFactura(factura.getIdFactura())).withSelfRel(),

                linkTo(methodOn(FacturaControllerV2.class).obtenerFacturas()).withRel("todas"),

                linkTo(methodOn(FacturaControllerV2.class).obtenerPorVenta(factura.getIdVenta())).withRel("porVenta")

        );
    }

}
