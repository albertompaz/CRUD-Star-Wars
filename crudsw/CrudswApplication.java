/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */


// Classe que ira inicializar o Back-End

package com.alberto.starwarsapi.crudsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudswApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudswApplication.class, args);
	}

}
