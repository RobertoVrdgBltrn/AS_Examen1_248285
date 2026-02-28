
import controller.PagoServicioController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.dao.ClienteDAO;
import model.dao.ServicioLuzDAO;
import model.entity.Cliente;
import model.entity.ServicioLuz;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rober
 */
public class Main {

    public static void main(String[] args) {

        // 1️⃣ Crear EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicioluz");

        // 2️⃣ Crear EntityManager
        EntityManager em = emf.createEntityManager();

        // 3️⃣ Insertar datos de prueba
        cargarDatosIniciales(em);

        // 4️⃣ Crear controlador
        PagoServicioController controller = new PagoServicioController(em);

        // 5️⃣ Probar búsqueda dinámica
        System.out.println("=== Buscando clientes con '100' ===");
        List<Cliente> clientes = controller.buscarClientes("100");

        for (Cliente c : clientes) {
            System.out.println(c);
        }

        // 6️⃣ Probar flujo completo si hay cliente
        if (!clientes.isEmpty()) {

            Cliente cliente = clientes.get(0);

            System.out.println("\nCliente seleccionado: " + cliente.getNombre());

            var servicio = controller.obtenerServicio(cliente);

            System.out.println("Consumo: " + servicio.getConsumoKwh());
            System.out.println("Adeudo: $" + servicio.getAdeudo());

            System.out.println("\nProcesando pago...");
            controller.procesarPago(cliente, "1234567890123456");

            servicio = controller.obtenerServicio(cliente);
            System.out.println("Nuevo adeudo: $" + servicio.getAdeudo());
        }

        // 7️⃣ Cerrar recursos
        em.close();
        emf.close();
    }

    private static void cargarDatosIniciales(EntityManager em) {

        ClienteDAO clienteDAO = new ClienteDAO(em);
        ServicioLuzDAO servicioDAO = new ServicioLuzDAO(em);

        // Si ya existen clientes, no volver a insertar
        List<Cliente> existentes = em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();

        if (!existentes.isEmpty()) {
            return;
        }

        em.getTransaction().begin();

        Cliente c1 = new Cliente("Juan Perez", "1001");
        Cliente c2 = new Cliente("Maria Lopez", "1002");
        Cliente c3 = new Cliente("Carlos Ramirez", "2001");

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);

        ServicioLuz s1 = new ServicioLuz(350, 1200.50, c1);
        ServicioLuz s2 = new ServicioLuz(280, 980.75, c2);
        ServicioLuz s3 = new ServicioLuz(500, 2100.00, c3);

        em.persist(s1);
        em.persist(s2);
        em.persist(s3);

        em.getTransaction().commit();

        System.out.println("Datos iniciales cargados.");
    }
}
