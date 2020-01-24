/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */

package com.alberto.starwarsapi.crudsw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alberto.starwarsapi.crudsw.documents.Planeta;

/*
 * Precisa-se definir 2 parametros: 
 * 1º - A qual documento ele se referenciara: Cliente
 * 2º - Qual o tipo do nosso ID, nesse caso tipo String, pois o mongo trabalha com
 * hash como id que é de numeros e letras
 */
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	// Função de buscar por nome
	List<Planeta> findByNome(String nome);
	
}
