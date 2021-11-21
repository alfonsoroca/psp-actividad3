package es.actividad3_rest_server.persistencia;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import es.actividad3_rest_server.modelo.entidad.Videojuego;

/**
 * DAO (Data Access Object), objeto que se encarga de hacer las consultas a los
 * datos guardados en una BBDD trabajando con una lista de objetos cargada en
 * memoria según requerimiento.
 * 
 * Mediante la anotacion @Component, creamos un único objeto de la clase
 * DaoVideojuego dentro del contexto de Spring.
 */
@Component
public class DaoVideojuego {

	ArrayList<Videojuego> listaJuegos;

	// Constructor de la clase DaoVideojuego que crea 5 objetos de la clase
	// Videojuego y los añade a la lista según requerimiento
	public DaoVideojuego() {

		System.out.println("Creando la lista de videojuegos y cargando 5 videojuegos");

		listaJuegos = new ArrayList<Videojuego>();

		// Creamos los 5 videojuegos
		Videojuego v1 = new Videojuego("ark", "Arkanoid", "Taito", 7);
		Videojuego v2 = new Videojuego("nem", "Nemesis", "Konami", 9);
		Videojuego v3 = new Videojuego("lor", "Lorna", "Toposoft", 6);
		Videojuego v4 = new Videojuego("coz", "Cozumel", "Aventuras AD", 5);
		Videojuego v5 = new Videojuego("dsp", "Desperado", "Toposoft", 10);

		// Añadimos los videojuegos a la lista
		listaJuegos.add(v1);
		listaJuegos.add(v2);
		listaJuegos.add(v3);
		listaJuegos.add(v4);
		listaJuegos.add(v5);
	}

	/**
	 * Método que devuelve todos los videojuegos de la lista.
	 * 
	 * @return Objeto ArrayList con todos los videojuegos.
	 */
	public ArrayList<Videojuego> lista() {
		System.out.println("Enviada lista con todos los videojuegos");
		return listaJuegos;
	}

	/**
	 * Método que busca un videojuego por su id.
	 * 
	 * @param id Identificador del videojuego que queremos buscar.
	 * @return Devuelve el videojuego con el id pasado por parámetro o null en el
	 *         caso de que no exista un objeto videojuego con ese id..
	 */
	public Videojuego getById(String id) {
		Videojuego videoJuego = null;
		for (Videojuego vj : listaJuegos) {
			if (vj.getId().equalsIgnoreCase(id)) {
				videoJuego = vj;
				// Los identificadores los consideramos únicos, cuando encuentra el videojuego
				// con el identificador facilitado como argumento se acaba el bucle.
				break;
			}
		}
		if (videoJuego != null) {
			System.out.println("Datos del " + videoJuego);
		} else {
			System.out.println("No existe ningún videojuego con id: " + id);
		}
		return videoJuego;
	}

	/**
	 * Método que elimina un videojuego por su id.
	 * 
	 * @param id Identificador del videojuego que queremos eliminar.
	 * @return Objeto videojuego eliminado o null en el caso de que no exista un
	 *         objeto videojuego con ese id.
	 */
	public Videojuego delete(String id) {
		for (Videojuego vj : listaJuegos) {
			if (vj.getId().equalsIgnoreCase(id)) {
				System.out.println("Se va a eliminar el " + vj.toString());
				listaJuegos.remove(vj);
				return vj;
			}
		}
		System.out.println("El videojuego no existe y no puede ser eliminado ;p");
		return null;
	}

	/**
	 * Método que actualiza los datos de un videojuego.
	 * 
	 * @param v Objeto videojuego que queremos modificar.
	 * @return Objeto videojuego modificado o null en el caso de que no exista un
	 *         objeto videojuego con ese id.
	 */
	public Videojuego update(Videojuego v) {
		for (Videojuego vj : listaJuegos) {
			if (vj.getId().equalsIgnoreCase(v.getId())) {
				Videojuego vjAux = vj;
				vjAux.setNombre(v.getNombre());
				vjAux.setEmpresa(v.getEmpresa());
				vjAux.setNota(v.getNota());
				System.out.println("Se ha actualizado el " + vjAux);
				return vjAux;
			}
		}
		System.out.println("No se puede actualizar el videojuego porque no existe ;P");
		return null;
	}

	/**
	 * Método que añade un videojuego a la lista de videojuegos si no existe su id o
	 * su nombre.
	 * 
	 * @param v Objeto videojuego que queremos añadir a la lista.
	 * @return El objeto videojuego añadido a la lista o null si ya existe un objeto
	 *         videojuego son ese id.
	 */
	public Videojuego add(Videojuego v) {
		// Variable que controla la existencia de los nomebres e id de los videojuegos.
		boolean idNombreExiste = false;
		for (Videojuego vj : listaJuegos) {
			if (vj.getId().equalsIgnoreCase(v.getId()) || vj.getNombre().equalsIgnoreCase(v.getNombre())) {
				idNombreExiste = true;
				break;
			}
		}
		// Con el condicional controlamos las acciones en función de si existe o no el
		// id.
		if (idNombreExiste == false) {
			listaJuegos.add(v);
			System.out.println("Añadido a la lista el " + v);
			return v;
		}
		System.out.println(
				"Ya existe un videojuego con ese nombre o referencia.\nNo se puede añadir a la lista de videojuegos.");
		return null;
	}
}