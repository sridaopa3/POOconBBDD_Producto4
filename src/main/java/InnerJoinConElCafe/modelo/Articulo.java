package InnerJoinConElCafe.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precioVenta")
    private double precioVenta;
    @Column(name = "gastosEnvio")
    private double gastosEnvio;
    @Column(name = "tiempoPreparacion")
    private int tiempoPreparacion;

      //Constructor vacío para que se retornen los datos con Hibernate
        public Articulo() {
    }

    public Articulo(String descripcion, double precioVenta, double gastosEnvio, int tiempoPreparacion) {
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + String.format("%.2f", precioVenta) +
                ", gastosEnvio=" + String.format("%.2f", gastosEnvio) +
                ", tiempoPreparacion=" + tiempoPreparacion +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }
}
