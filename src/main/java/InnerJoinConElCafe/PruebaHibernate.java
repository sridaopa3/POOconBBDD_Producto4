package InnerJoinConElCafe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import InnerJoinConElCafe.modelo.Articulo;

public class PruebaHibernate {
    public static void main(String[] args) {
  
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulo.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
           
            System.out.println("Buscando artículo con código 1...");
            Articulo miArticulo = session.get(Articulo.class, 1);

            if (miArticulo != null) {
                System.out.println("CONEXIÓN ESTABLECIDA");
                System.out.println("Articulo encontrado: " + miArticulo.getDescripcion());
            } else {
                System.out.println("Conexión ok, pero no se encuentra el artículo 1.");
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}