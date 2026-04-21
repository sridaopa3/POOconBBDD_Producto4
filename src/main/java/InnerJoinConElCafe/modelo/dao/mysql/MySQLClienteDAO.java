package InnerJoinConElCafe.modelo.dao.mysql;

import InnerJoinConElCafe.modelo.Cliente;
import InnerJoinConElCafe.modelo.ClienteEstandar;
import InnerJoinConElCafe.modelo.ClientePremium;
//import InnerJoinConElCafe.modelo.ClienteEstandar;
//import InnerJoinConElCafe.modelo.ClientePremium;
import InnerJoinConElCafe.modelo.dao.ClienteDAO;
//import InnerJoinConElCafe.modelo.dao.ConexionBD;
//import java.sql.*;
//import java.util.ArrayList;
import java.util.List;
import InnerJoinConElCafe.modelo.Articulo; 
import InnerJoinConElCafe.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySQLClienteDAO implements ClienteDAO {

    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Articulo.class)
            .addAnnotatedClass(Cliente.class)
            .addAnnotatedClass(Pedido.class)
            .addAnnotatedClass(ClienteEstandar.class) 
            .addAnnotatedClass(ClientePremium.class)
            .buildSessionFactory();

    @Override
    public void insertar(Cliente c) throws Exception {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(c); 
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al insertar cliente con Hibernate: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> obtenerTodos() throws Exception {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Cliente", Cliente.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error al listar clientes con Hibernate: " + e.getMessage());
        }
    }

    @Override
    public Cliente obtener(String email) throws Exception {
        try (Session session = factory.openSession()) {
            return session.get(Cliente.class, email);
        }
    }

    @Override public void modificar(Cliente t) throws Exception {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        }
    }

    @Override public void eliminar(Cliente t) throws Exception {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Cliente cABorrar = session.get(Cliente.class, t.getEmail());
            if (cABorrar != null) session.remove(cABorrar);
            session.getTransaction().commit();
        }
    }

    /**private final String GET_ALL = "SELECT * FROM clientes";

    @Override
    public void insertar(Cliente c) throws Exception {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            conn.setAutoCommit(false); 

            String sql = "{call insertarCliente(?, ?, ?, ?, ?, ?, ?)}";
            try (CallableStatement cst = conn.prepareCall(sql)) {
                cst.setString(1, c.getNif());
                cst.setString(2, c.getNombre());
                cst.setString(3, c.getDomicilio());
                cst.setString(4, c.getEmail());
                cst.setString(5, (c instanceof ClientePremium) ? "Premium" : "Estandar");
                cst.setDouble(6, c.getCuotaAnual());
                cst.setDouble(7, c.getDescuentoEnvio());
            
                cst.executeUpdate();
            }

            conn.commit(); 
        } catch (Exception e) {
            if (conn != null) conn.rollback(); 
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    @Override
    public List<Cliente> obtenerTodos() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stat = conn.prepareStatement(GET_ALL);
             ResultSet rs = stat.executeQuery()) {
            while (rs.next()) {
                Cliente c;
                String tipo = rs.getString("tipo");
                if ("Premium".equals(tipo)) {
                    c = new ClientePremium(rs.getString("nombre"), rs.getString("domicilio"), rs.getString("nif"), rs.getString("email"));
                } else {
                    c = new ClienteEstandar(rs.getString("nombre"), rs.getString("domicilio"), rs.getString("nif"), rs.getString("email"));
                }
                lista.add(c);
            }
        }
        return lista;
    }

    @Override public void modificar(Cliente t) throws Exception {}
    @Override public void eliminar(Cliente t) throws Exception {}
    @Override public Cliente obtener(String id) throws Exception { return null; } */
}