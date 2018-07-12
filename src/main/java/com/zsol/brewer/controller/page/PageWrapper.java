package com.zsol.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriComponents;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20");
		this.uriComponents = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}

	public List<T> getConteudo() {
		return page.getContent();
	}

	public boolean isVazio() {
		return page.getContent().isEmpty();
	}

	public int getAtual() {
		return page.getNumber();
	}

	public boolean isPrimeiraPagina() {
		return page.isFirst();
	}

	public boolean isUltimaPagina() {
		return page.isLast();
	}

	public int getTotal() {
		return page.getTotalPages();
	}

	public String urlParaPagina(int pagina) {
		return uriComponents.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}

	public String ordenarPagina(String propriedade) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
				.fromUriString(uriComponents.build(true).encode().toUriString());

		String valorSort = String.format("%s,%s", propriedade, inverteDirecao(propriedade));
		
		return uriComponentsBuilder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	public String inverteDirecao(String propriedade){
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order != null){
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	public boolean descendente(String propriedade){
		return inverteDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade){
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if(order == null){
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

}
