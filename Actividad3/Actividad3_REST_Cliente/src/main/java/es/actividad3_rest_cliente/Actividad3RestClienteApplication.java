package es.actividad3_rest_cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.actividad3_rest_cliente.entidad.Videojuego;
import es.actividad3_rest_cliente.servicio.ServicioProxyVideojuego;

@SpringBootApplication
public class Actividad3RestClienteApplication implements CommandLineRunner {

	// Inyectamos todos los objetos que necesitamos para acceder a nuestro
	// ServicioRest y el ServicioProxyVideojuego
	@Autowired
	private ServicioProxyVideojuego spv;

	// Inyectamos el propio ocntexto de Spring.
	@Autowired
	private ApplicationContext context;
	
	// Damos de alta un objeto RestTemplate para la comunicación con el servidor
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Atributos de un videojuego e inicialización de Scanner
	String id, nombre, empresa;
	int nota;
	Scanner sc = new Scanner(System.in);

	// Metodo main que lanza la aplicacion llamando al método run
	public static void main(String[] args) {
		System.out.println("Application Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Actividad3RestClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Variable que controla la opción seleccionada en el menú
		String opcion = "";

		// Con un bucle do-while controlamos el menu y su funcionalidad
		do {
			// Menú del cliente REST
			System.out.println("\nCliente REST -> Elige la opción que deseas...");
			System.out.println("Cliente REST -> 	(1) Dar de alta un videojuego");
			System.out.println("Cliente REST -> 	(2) Dar de baja un videojuego por ID");
			System.out.println("Cliente REST -> 	(3) Modificar un videojuego por ID");
			System.out.println("Cliente REST -> 	(4) Obtener un videojuego por ID");
			System.out.println("Cliente REST -> 	(5) Obtener todos los videojuegos");
			System.out.println("Cliente REST -> 	(6) Salir");
			opcion = sc.nextLine();

			// Con un switch controlamos las elecciones del cliente
			switch (opcion) {
			case "1":
				// El cliente elige la opción 1 llamamos al método alta.
				alta();
				break;
			case "2":
				// El cliente elige la opción 2 llamamos al método borrar.
				borrar();
				break;
			case "3":
				// El cliente elige la opción 3 llamamos al método modificar.
				modificar();
				break;
			case "4":
				// El cliente elige la opción 4 llamamos al método vjuegoId.
				vjuegoId();
				break;
			case "5":
				// El cliente elige la opción 5 llamamos al método lista.
				lista();
				break;
			case "6":
				// El cliente elige la opción 5 salimos del bucle do-while.
				break;
			default:
				// Si el cliente no elige una opción controlada le advertimos.
				System.out.println("Cliente REST -> Debes elegir una opción del 1 al 6");
			}
		} while (!opcion.equalsIgnoreCase("6"));

		System.out.println("Has elegido salir de la aplicación......");
		salir();
	}

	// Métodos que determinan la funcionalidad que elige el cliente a través del
	// menú
	private void alta() {

		// Alta de videojuego
		// Solicitamos los datos del videojuego a añadir e invocamos al método add() de
		// ServicioProxyVideojuego. Con el if controlamos el retorno del método add() y
		// mostramos información por pantalla.
		System.out.println("Cliente REST -> Vas a proceder al alta de un nuevo videojuego");
		System.out.println("Cliente REST ->		Introduce el id del videojuego...");
		id = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce el nombre del videojuego...");
		nombre = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce la empresa del videojuego...");
		empresa = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce la nota del videojuego...");
		nota = Integer.parseInt(sc.nextLine());
		Videojuego vjAdd = spv.add(new Videojuego(id, nombre, empresa, nota));

		if (vjAdd != null) {
			System.out.println("Cliente REST -> Se ha añadido al servidor el " + vjAdd);
		} else {
			System.out.println("Cliente REST -> No se ha podido añadir al servidor el videojuego");
		}
	}

	private void borrar() {

		// Borrado de videojuego
		// Solicitamos el id del videojuego a borrar e invocamos al método delete() de
		// ServicioProxyVideojuego. Con el if controlamos el retorno del método delete()
		// y mostramos información por pantalla.
		System.out.println("Cliente REST -> Vas a proceder a eliminar videojuego");
		System.out.println("Cliente REST -> 	Introduce el id del videojuego...");
		id = sc.nextLine();
		boolean vjDelete = spv.delete(id);

		if (vjDelete != false) {
			System.out.println("Cliente REST ->	Se ha eliminado del servidor el videojuego con id " + id);
		} else {
			System.out.println("Cliente REST ->	No existe en el servidor el videojuego con el id " + id);
		}
	}

	private void modificar() {

		// Modificación de videojuego
		// Solicitamos los datos del videojuego a modificar e invocamos al método
		// update() de ServicioProxyVideojuego. Con el if controlamos el retorno del
		// método update() y mostramos información por pantalla.
		System.out.println("Cliente REST -> Vas a proceder a modificar un videojuego");
		System.out.println("Cliente REST ->		Introduce el id del videojuego...");
		id = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce el nombre del videojuego...");
		nombre = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce la empresa del videojuego...");
		empresa = sc.nextLine();
		System.out.println("Cliente REST ->		Introduce la nota del videojuego...");
		nota = Integer.parseInt(sc.nextLine());
		boolean vjUpdate = spv.update(new Videojuego(id, nombre, empresa, nota));

		if (vjUpdate != false) {
			System.out.println("Cliente REST ->	Se ha modificado en el servidor el videojuego con id " + id);
		} else {
			System.out.println("Cliente REST ->	No existe en el servidor el videojuego con el id " + id);
		}
	}

	private void vjuegoId() {

		// Solicitar videojuego por id
		// Solicitamos el id del videojuego a buscar e invocamos al método
		// getVideojuegoById() de ServicioProxyVideojuego. Con el if controlamos el
		// retorno del método getVideojuegoById() y mostramos información por pantalla.
		System.out.println("Cliente REST -> Vas a proceder a solicitar un videojuego");
		System.out.println("Cliente REST ->		Introduce el id del videojuego...");
		id = sc.nextLine();
		Videojuego vjGetById = spv.getVideojuegoById(id);

		if (vjGetById != null) {
			System.out.println("Cliente REST -> Has solicitado el " + vjGetById);
		} else {
			System.out.println("Cliente REST ->	No existe el videojuego con id " + id);
		}
	}

	private void lista() {

		// Obtener lista de videojuegos
		List<Videojuego> list = spv.list();
		for (Videojuego v : list) {
			System.out.println(v);
		}
	}

	private void salir() {

		// Cerramos el Scanner
		sc.close();
		// Paramos el servidor
		SpringApplication.exit(context, () -> 0);
	}
}