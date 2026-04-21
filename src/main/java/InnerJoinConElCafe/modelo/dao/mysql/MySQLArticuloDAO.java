package InnerJoinConElCafe.modelo.dao.mysql;

import InnerJoinConElCafe.modelo.Articulo;
import InnerJoinConElCafe.modelo.dao.ArticuloDAO;
//import InnerJoinConElCafe.modelo.dao.ConexionBD;
//import java.sql.*;
//import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySQLArticuloDAO implements ArticuloDAO {

    //private final String GET_ALL = "SELECT * FROM articulos";
    //private final String DELETE = "DELETE FROM articulos WHERE codigo = ?";
  
    //Aplicación SessionFactory para funcionamiento de Hibernate
    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Articulo.class)
            .buildSessionFactory();

    @Override

    //Método insertar articulo c/ Hibernate
    public void insertar(Articulo a) throws Exception {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(a); 
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al insertar con Hibernate: " + e.getMessage());
        }
    }

    //Método listar articulos c/ Hibernate
    @Override
    public List<Articulo> obtenerTodos() throws Exception {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Articulo", Articulo.class).getResultList();
        } catch (Exception e) {
            throw new Exception("Error al consultar con Hibernate: " + e.getMessage());
        }
    }

    //Método eliminar articulo c/Hibernate
    @Override
    public void eliminar(Articulo a) throws Exception {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Articulo aBorrar = session.get(Articulo.class, a.getCodigo());
            if (aBorrar != null) {
                session.remove(aBorrar);
            }
            
            session.getTransaction().commit();
        }
    }

    @Override public void modificar(Articulo t) throws Exception { 
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.merge(t); 
            session.getTransaction().commit();
        }
    }
    
    @Override public Articulo obtener(Integer id) throws Exception { 
        try (Session session = factory.openSession()) {
            return session.get(Articulo.class, id);
        }
    }
}


    /**
    public void insertar(Articulo a) throws Exception {
        Connection conn = null;
        try {
            conn = ConexionBD.conectar();
            // 1. Desactivamos el autoCommit
            conn.setAutoCommit(false); 

            // PROCEDIMIENTO ALMACENADO
            // 2. Llamamos al procedimiento almacenado (CallableStatement)
            String sql = "{call insertarArticulo(?, ?, ?, ?)}";
            try (CallableStatement cstmt = conn.prepareCall(sql)) {
                cstmt.setString(1, a.getDescripcion());
                cstmt.setDouble(2, a.getPrecioVenta());
                cstmt.setDouble(3, a.getGastosEnvio());
                cstmt.setInt(4, a.getTiempoPreparacion());
            
                cstmt.executeUpdate();
            } 

            // TRANSACCIONES
            // 3. Si todo sale correcto, hacemos el commit 
            conn.commit(); 
        
        } catch (Exception e) {
            // 4. Si ocurre algun tipo de error, hacemos rollback para evitar problemas
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

  
    public List<Articulo> obtenerTodos() throws Exception {
        List<Articulo> lista = new ArrayList<>();
        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stat = conn.prepareStatement(GET_ALL);
            ResultSet rs = stat.executeQuery()) {
            
            while (rs.next()) {
                // Usamos el constructor sin ID
                Articulo a = new Articulo(
                    rs.getString("descripcion"),
                    rs.getDouble("precioVenta"),
                    rs.getDouble("gastosEnvio"),
                    rs.getInt("tiempoPreparacion")
                );
                // Seteamos el ID manualmente tras crearlo
                a.setCodigo(rs.getInt("codigo"));
                lista.add(a);
            }
        }
        return lista;
    }
    @Override
    public void eliminar(Articulo a) throws Exception {
        try (Connection conn = ConexionBD.conectar();
            PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getCodigo());
            stat.executeUpdate();
        }
    }

    public void modificar(Articulo t) throws Exception { /* Próxima fase */ 
    //public Articulo obtener(Integer id) throws Exception { return null; }
