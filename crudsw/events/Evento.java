/* Programa realizar um CRUD com uma API do StarWars, aonde se pegara a quantidade de 
 * aparições em filme ao se informar o nome de um planeta.
 * Programador: Alberto Paz
 * Data: 21/12/2019 */

package com.alberto.starwarsapi.crudsw.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class Evento extends ApplicationEvent {

	static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private String id;

	public Evento(Object source, HttpServletResponse response, String id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getId() {
		return id;
	}
	
}
