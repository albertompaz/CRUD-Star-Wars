/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */


// Implementando as funções que nosso CRUD realizará
package com.alberto.starwarsapi.crudsw.services;

import java.util.List;

import com.alberto.starwarsapi.crudsw.documents.Planeta;

public interface PlanetaService {

	List<Planeta> listarTodos();
	
	Planeta listarPorId(String id);
	
	List<Planeta> listarPorNome(String nome);
	
	Planeta cadastrar(Planeta planeta);
	
	Planeta atualizar(Planeta planeta);
	
	void remover (String id);
	
}
