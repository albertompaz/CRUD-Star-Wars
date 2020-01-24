/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */

package com.alberto.starwarsapi.crudsw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alberto.starwarsapi.crudsw.documents.Planeta;
import com.alberto.starwarsapi.crudsw.events.Evento;
import com.alberto.starwarsapi.crudsw.services.PlanetaService;

//Classe para aonde tem as funções HTTP de Get, Post, Put, Delete

//Para o controller funciona eu preciso da anotação @RestController
//Isso faz com que o Spring disponibilize um controller do tipo rest
@RestController
@RequestMapping(path = "/api/planetas")
public class PlanetaController {

	@Autowired
	private PlanetaService planetaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	// Get para retornar todos os planetas cadastratods
	@GetMapping // não ira definir um mapping pois assumira esse map como o padrao pra listar
	public ResponseEntity<List<Planeta>> listarTodos() {
		return ResponseEntity.ok(this.planetaService.listarTodos());
	}
	
	// Get para realizar uma busca por id de um planeta
	@GetMapping(path = "/id/{id}") // {id} siginifica um id dinamico e que sera passado pela url
	public ResponseEntity<Planeta> listarPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(this.planetaService.listarPorId(id));
	}
	
	// Get para realizar uma busca por nome de um planeta
	@GetMapping(path = "/name/{nome}")
	public ResponseEntity<List<Planeta>> listarPorNome(@PathVariable(name = "nome") String nome) {
		return ResponseEntity.ok(this.planetaService.listarPorNome(nome));
	}
	
	// Post para cadastrar um planeta
	@PostMapping
	public ResponseEntity<Planeta> cadastrar(@RequestBody Planeta planeta, HttpServletResponse response) {
		Planeta planetaSalvo = planetaService.cadastrar(planeta);
		// Vendo se achou algum planeta
		if (planetaSalvo != null) {
			publisher.publishEvent(new Evento(this, response, planeta.getId()));
			return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);
		} else {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	// Put para realizar a alteração de um planeta pelo id
	@PutMapping (path = "/{id}")
	public ResponseEntity<Planeta> atualizar(@PathVariable(name = "id") String id ,@RequestBody Planeta planeta) {
		planeta.setId(id);
		return ResponseEntity.ok(this.planetaService.atualizar(planeta));
	}
	
	// Delete para deletar um planeta pelo id
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Integer> remover(@PathVariable(name = "id") String id) {
		this.planetaService.remover(id);
		return ResponseEntity.ok(1);
	}	
	
}
