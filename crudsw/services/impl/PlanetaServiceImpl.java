/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */

package com.alberto.starwarsapi.crudsw.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.alberto.starwarsapi.crudsw.documents.Planeta;
import com.alberto.starwarsapi.crudsw.repository.PlanetaRepository;
import com.alberto.starwarsapi.crudsw.services.PlanetaService;

//Por ser um serviço precisamos marcar a classe com a anotação service
@Service
public class PlanetaServiceImpl implements PlanetaService {

	// Criando uma instancia do repository disponivel para nós
	@Autowired
	private PlanetaRepository planetaRepository;
	
	// Criando uma string com a url pra buscar o planeta, faltando só o nome
	private static final String SWAPI_URL = "https://swapi.co/api/planets/?search=";
	
	@Override
	public List<Planeta> listarTodos() {
		return this.planetaRepository.findAll();
	}

	@Override
	public Planeta listarPorId(String id) {
		return this.planetaRepository.findById(id).orElse(null);
	}

	@Override
	public List<Planeta> listarPorNome(String nome) {
		return this.planetaRepository.findByNome(nome);
	}
	
	@Override
	public Planeta cadastrar(Planeta planeta) {
		Integer qtd = this.obterQtdAparicoes(planeta.getNome());
		
		// Se não tiver aparições do planeta ira retornar null
		if (qtd == null) {
			return null;
		}
		
		// se Tiver aparições irá por no objeto o número de aparições
		planeta.setQtdAparicoes(qtd);
		return this.planetaRepository.save(planeta);
	}

	private Integer obterQtdAparicoes(String name) {
		
		// Criando um objeto StringBuilder
		StringBuilder completeUrl = new StringBuilder();
		// adicionando o nome do planeta digitado a nossa String de busca na API
		completeUrl.append(SWAPI_URL).append(name);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		
		Object object;
		try {
			object = restTemplate.exchange(completeUrl.toString(), HttpMethod.GET,
					new HttpEntity<String>("parameters", headers), Object.class);
		} catch (Exception e) {
			return null;
		}
		
		Gson gson = new Gson();
		JsonArray results = gson.fromJson(gson.toJson(object), JsonObject.class).getAsJsonObject("body")
				.getAsJsonArray("results");

		JsonElement correctResult = null;

		for (JsonElement e : results) {
			if (e.getAsJsonObject().get("name").getAsString().equalsIgnoreCase(name)) {
				correctResult = e;
			}
		}

		if (correctResult == null) {
			return 0;
		} else {
			return correctResult.getAsJsonObject().getAsJsonArray("films").size();
		}
	}
	
	/*O Spring automaticamente identifica, se tem o id ele atualiza os dados, se não
	 * tem ele cria um novo objeto
	 * */
	@Override
	public Planeta atualizar(Planeta planeta) {
		return this.planetaRepository.save(planeta);
	}

	@Override
	public void remover(String id) {
		this.planetaRepository.deleteById(id);
	}

}
