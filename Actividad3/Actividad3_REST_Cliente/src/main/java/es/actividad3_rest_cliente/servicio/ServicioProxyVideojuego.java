package es.actividad3_rest_cliente.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.actividad3_rest_cliente.entidad.Videojuego;

// Damos de alta un objeto de tipo ServicioProxyVideojuego dentro del contexto de Spring
@Service
public class ServicioProxyVideojuego {

	// La URL del servidor del servicio REST de videojuegos
	public static final String URL = "http://localhost:8080/videojuegos/";

	// Inyección del objeto de tipo RestTemplate para hacer las peticiones HTTP al
	// servicio REST
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Metodo para obtener un videojuego del servicio REST a partir de un id. Si el
	 * id no existe arroja una excepción y obtenemos el código de respuesta.
	 * 
	 * @param id del videojuego que queremos obtener.
	 * @return devuelve el videojuego con el id pasado como argumento, null en caso
	 *         de que el videojuego no exista lo que devolverá un código 404.
	 */
	public Videojuego getVideojuegoById(String id) {
		try {
			// Creamos la URL pasándo el id del videojuego
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs = re.getStatusCode();
			if (hs == HttpStatus.OK) {
				// Si recibimos un código OK, obtenemos el videojuego que se encuentra en el
				// body
				System.out.println("getVideojuegoById -> Codigo de respuesta " + re.getStatusCode());
				return re.getBody();
			} else {
				System.out.println("getVideojuegoById -> Codigo de respuesta " + re.getStatusCode());
				System.out.println("No existe el videojuego");
				return null;
			}
		} catch (HttpClientErrorException e) {
			System.out.println("getVideojuegoById -> NO se ha encontrado el videojuego con id: " + id);
			System.out.println("getVideojuegoById -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	/**
	 * Metodo para dar de alta un videojuego en el servicio REST.
	 * 
	 * @param v el videojuego que queremos dar de alta.
	 * @return devuelve el videojuego que se da de alta en el servicio REST. Null si
	 *         no se puede dar de alta.
	 */
	public Videojuego add(Videojuego v) {
		try {
			// Creamos la URL pasándo el id del videojuego que queremos crear
			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL + v.getId(), v, Videojuego.class);
			System.out.println("add -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("add -> NO se ha dado de alta el " + v);
			System.out.println("add -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}

	/**
	 * 
	 * Método para modificar un videojuego en el servicio REST
	 * 
	 * @param v el videojuego que queremos modificar y cuyo id debe estar asociado a
	 *          un videojuego existente.
	 * @return true en caso de que se haya podido modificar el videojuego y false en
	 *         caso contrario.
	 */
	public boolean update(Videojuego v) {
		try {
			restTemplate.put(URL + v.getId(), v, Videojuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("update -> NO se ha modificado el " + v);
			System.out.println("update -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	/**
	 * 
	 * Método para borrar un videojuego en el servicio REST
	 * 
	 * @param id del videojuego que queremos borrar.
	 * @return true en caso de que se haya podido borrar el videojuego, false en
	 *         caso contrario.
	 */
	public boolean delete(String id) {
		try {
			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("delete -> NO se ha borrado el videojuego con id: " + id);
			System.out.println("delete -> Codigo de respuesta: " + e.getStatusCode());
			return false;
		}
	}

	/**
	 * Metodo para devolver todos los videojuegos almacenados en el servicio REST.
	 * 
	 * @return el listado de los videojuegos o null en caso de algun error con el
	 *         servicio REST.
	 */
	public List<Videojuego> list() {

		try {
			ResponseEntity<Videojuego[]> re = restTemplate.getForEntity(URL, Videojuego[].class);
			Videojuego[] arrayVideojuegos = re.getBody();
			System.out.println("list -> Codigo de respuesta: " + re.getStatusCode());
			return Arrays.asList(arrayVideojuegos);
		} catch (HttpClientErrorException e) {
			System.out.println("list -> Error al obtener la lista de videojuegos");
			System.out.println("list -> Codigo de respuesta: " + e.getStatusCode());
			return null;
		}
	}
}