package Main;


import controller.PagoServicioController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.dao.ClienteDAO;
import model.dao.ServicioLuzDAO;
import model.entity.Cliente;
import model.entity.ServicioLuz;
import view.PagoServicioView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rober
 */
public class MainPrincipal {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("servicioluz");
        EntityManager em = emf.createEntityManager();

        PagoServicioController controller = new PagoServicioController(em);

        cargarDatosIniciales(em);
        
        new PagoServicioView(controller).setVisible(true);
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
