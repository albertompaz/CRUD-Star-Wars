/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */


// Classe para inforar os campos que nosso objeto terá

package com.alberto.starwarsapi.crudsw.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document // para definir anotações dentro da classe planeta
public class Planeta {

	// todo documento no mongo deve ter um id, e ele precisa ser anotado com @Id
	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private int qtdAparicoes;
	
	// definindo um construtor padrão por boa pratica
	public Planeta() {
	}

	// Get e Set dos campos
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getQtdAparicoes() {
		return qtdAparicoes;
	}

	public void setQtdAparicoes(int qtdAparicoes) {
		this.qtdAparicoes = qtdAparicoes;
	}
	
	
	
}
