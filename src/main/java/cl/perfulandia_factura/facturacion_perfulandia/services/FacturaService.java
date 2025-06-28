package cl.perfulandia_factura.facturacion_perfulandia.services;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import cl.perfulandia_factura.facturacion_perfulandia.repository.FacturaRepository;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;


    public Factura generarFactura(@NotNull Factura factura) {
        return facturaRepository.save(factura);
    }

    public List<Factura> obtenerTodas() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> obtenerPorId(String id) {
        return facturaRepository.findById(String.valueOf(id));
    }

    public Factura obtenerPorVenta(String idVenta) {
        return facturaRepository.findByIdVenta(idVenta);
    }


}
