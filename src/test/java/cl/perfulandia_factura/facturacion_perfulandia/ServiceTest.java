package cl.perfulandia_factura.facturacion_perfulandia;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.perfulandia_factura.facturacion_perfulandia.model.Factura;
import cl.perfulandia_factura.facturacion_perfulandia.services.FacturaService;
@SpringBootTest
public class ServiceTest {

     @Autowired
    private FacturaService facturaservice;

    @Test
    public void testGenerarFactura() {

        // Crear factura de prueba
        Factura factura = new Factura();
        factura.setIdFactura("F123");
        factura.setFechaEmision(LocalDate.now());
        factura.setMetodoPago("Efectivo");
        factura.setIdCliente("TEST_04");
        factura.setIdVenta("V123");

        // Llamar al servicio directamente (asumiendo que tienes facturaService)
        Factura facturaGuardada = facturaservice.generarFactura(factura);

        // Validar que el valor guardado coincida
        assertEquals("F123", facturaGuardada.getIdFactura(), "El ID de factura no es el esperado");
        assertEquals("TEST_04", facturaGuardada.getIdCliente(), "El ID cliente no es el esperado");
        assertEquals("V123", facturaGuardada.getIdVenta(), "El ID venta no es el esperado");
    }

    @Test
    public void testObtenerFacturas() {
        // Llama al servicio para obtener todas
        List<Factura> facturas = facturaservice.obtenerTodas();

        // Valida que la lista no sea vacía y contenga al menos las 2 facturas
        assertNotNull(facturas, "La lista no debe ser nula");
        assertTrue(facturas.size() >= 2, "Debe contener al menos dos facturas");

        // Opcional: verifica que las facturas insertadas estén en la lista
        assertTrue(facturas.stream().anyMatch(f -> "F1".equals(f.getIdFactura())));
        assertTrue(facturas.stream().anyMatch(f -> "F2".equals(f.getIdFactura())));
    }

    @Test
    public void testObtenerFacturaExistente() {

        String idFacturaExistente = "F12"; //

        Factura factura = facturaservice.obtenerPorId(idFacturaExistente).orElse(null);

        assertNotNull(factura, "La factura debería existir en la base de datos");
        assertEquals(idFacturaExistente, factura.getIdFactura(), "El ID no coincide");
    }

    @Test
    public void testObtenerFacturaPorVenta() {
        // ⚠️ Usa un idVenta que ya exista en tu base de datos
        String idVentaExistente = "V1"; // cámbialo por un ID real

        Factura factura = facturaservice.obtenerPorVenta(idVentaExistente);

        assertNotNull(factura, "La factura asociada a la venta debería existir");
        assertEquals(idVentaExistente, factura.getIdVenta(), "El ID de venta no coincide");

        System.out.println("Factura encontrada: ID=" + factura.getIdFactura() +
                ", Cliente=" + factura.getIdCliente() +
                ", Fecha=" + factura.getFechaEmision() +
                ", M. Pago=" + factura.getMetodoPago());

    }


}
