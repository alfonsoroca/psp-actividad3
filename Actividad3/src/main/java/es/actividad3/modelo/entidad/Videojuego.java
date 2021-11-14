package es.actividad3.modelo.entidad;

//Clase que define las características de la entidad con la que va a trabajar el servicio REST
public class Videojuego {

	String id, nombre, empresa;
	int nota;

	// Constructor de objetos videojuego
	public Videojuego(String id, String nombre, String empresa, int nota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.empresa = empresa;
		this.nota = nota;
	}

	// Getters y setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	// Sobrescritura del método toString()
	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", empresa=" + empresa + ", nota=" + nota + "]";
	}

}
