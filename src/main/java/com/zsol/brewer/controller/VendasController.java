package com.zsol.brewer.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zsol.brewer.controller.validator.VendaValidator;
import com.zsol.brewer.model.Cerveja;
import com.zsol.brewer.model.Venda;
import com.zsol.brewer.repository.Cervejas;
import com.zsol.brewer.security.UsuarioSistema;
import com.zsol.brewer.service.CadastroVendaService;
import com.zsol.brewer.session.TabelaItensSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private TabelaItensSession tabelaItens;
	
	@Autowired
	private CadastroVendaService vendaService;
	
	@Autowired
	private VendaValidator vendaValidator;
	
	@InitBinder
	public void iniciarValidador(WebDataBinder binder){
		binder.setValidator(vendaValidator);
	}
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Venda venda){
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		
		if(StringUtils.isEmpty(venda.getUuid())){
			venda.setUuid(UUID.randomUUID().toString());			
		}
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotal", venda.getValorTotal());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema){
		venda.adicionarItens(tabelaItens.getItens(venda.getUuid()));
		venda.calcularValorTotal();
		
		vendaValidator.validate(venda, result);
		if(result.hasErrors()){
			return novo(venda);
		}
		
		venda.setUsuario(usuarioSistema.getUsuario());
		
		vendaService.salvar(venda);
		attributes.addFlashAttribute("mensagem", "Venda cadastrada com sucesso!");
		return new ModelAndView("redirect:/vendas/novo");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(String uuid, Long codigoCerveja){
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, cerveja, 1);
		return mvTabelaItens(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeCerveja(@PathVariable("codigoCerveja") Cerveja cerveja, Integer quantidade, String uuid){
		tabelaItens.alterQuantidadeItemCadastrado(uuid, cerveja, quantidade);
		
		return mvTabelaItens(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView deletarCerveja(@PathVariable("codigoCerveja") Cerveja cerveja, @PathVariable String uuid){
		tabelaItens.removerCerveja(uuid, cerveja);
		
		return mvTabelaItens(uuid);
	}
	

	private ModelAndView mvTabelaItens(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		
		return mv;
	}
}
