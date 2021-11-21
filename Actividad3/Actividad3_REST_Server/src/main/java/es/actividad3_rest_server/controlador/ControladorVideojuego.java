package es.actividad3_rest_server.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.actividad3_rest_server.modelo.entidad.Videojuego;
import es.actividad3_rest_server.persistencia.DaoVideojuego;

@RestController
public class ControladorVideojuego {

	// Inyectamos un objeto DaoVideojuego al objeto ControladorVideojuego en la
	// referencia daovideojuego.
	@Autowired
	private DaoVideojuego daoVideojuego;

	// Configuración de los diferentes endpoints del requerimiento.

	// POST
	// Dar de alta un videojuego nuevo cuando su id no coincide con ningún id de los
	// videojuegos almacenados.
	@PostMapping(path = "videojuegos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> postVideojuego(@PathVariable("id") String id, @RequestBody Videojuego v) {		
		System.out.println("Tramitando el alta del " + v);
		Videojuego nuevoVideojuego = daoVideojuego.add(v);		
		// Con el condicional controlamos los resultados de la creación del videojuego.
		if (nuevoVideojuego != null) {			
			return new ResponseEntity<Videojuego>(v, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Videojuego>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	// DELETE
	// Eliminación de un videojuego que identificamos pos su id el cual pasamos por
	// url al ser una clave primaria.
	@DeleteMapping(path = "videojuegos/{id}")
	public ResponseEntity<Videojuego> deleteVideojuego(@PathVariable String id) {		
		System.out.println("Videojuego a borrar con id " + id);
		Videojuego vDelete = daoVideojuego.delete(id);		
		if (vDelete != null) {			
			return new ResponseEntity<Videojuego>(vDelete, HttpStatus.OK);
		} else {			
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
		}
	}

	// PUT
	// Actualizar la información de un videojuego ya existente.
	@PutMapping(path = "videojuegos/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> putVideojuego(@PathVariable("id") String id, @RequestBody Videojuego v) {
		System.out.println("Videojuego a modificar con id " + id);
		System.out.println("Datos a modificar del " + v);
		Videojuego vUpdate = daoVideojuego.update(v);
		if (vUpdate != null) {			
			return new ResponseEntity<Videojuego>(v, HttpStatus.OK);
		} else {			
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
		}
	}

	// GET
	// Obtener un videojuego por id que al ser una clave primaria se debe pasar el
	// id como parte del path de la url.Vamos a devolver la información en formato
	// JSON.
	@GetMapping(path = "videojuegos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> getVideojuego(@PathVariable("id") String id) {
		System.out.println("Buscando el videojuego con id: " + id);
		Videojuego v = daoVideojuego.getById(id);
		// Con el condicional controlamos los resultados de la búsqueda.
		if (v != null) {
			return new ResponseEntity<Videojuego>(v, HttpStatus.OK);
		} else {			
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);			
		}
	}

	// GET
	// Obtener la lista de videojuegos completa. Vamos a devolver la información en
	// formato
	// JSON.
	@GetMapping(path = "videojuegos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Videojuego>> listaVideojuegos() {
		ArrayList<Videojuego> listaVideojuegos = daoVideojuego.lista();
		return new ResponseEntity<ArrayList<Videojuego>>(listaVideojuegos, HttpStatus.OK);
	}
}