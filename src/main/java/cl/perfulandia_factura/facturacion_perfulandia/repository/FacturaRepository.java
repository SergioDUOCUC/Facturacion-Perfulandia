package cl.perfulandia_factura.facturacion_perfulandia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, String> {
    Factura findByIdVenta(String idVenta);

}

