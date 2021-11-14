package es.actividad3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Actividad3Application {

	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		SpringApplication.run(Actividad3Application.class, args);
		System.out.println("Servicio Rest -> Todo cargado!!!");
	}

}
