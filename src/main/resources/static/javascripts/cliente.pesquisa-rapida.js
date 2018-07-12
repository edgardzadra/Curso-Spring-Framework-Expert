var Brewer = Brewer || {};

Brewer.PesquisaRapida = (function(){
	
	function PesquisaRapida(){
		this.modalPesquisaRapida = $('#pesquisaRapidaCliente');
		this.campoBuscaNomeCliente = $('#nomeClienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-cliente-btn');
		this.containerTabelaPesquisa = $('#containerPesquisaRapidaCliente');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapida.prototype.iniciar = function(){
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidoClicado.bind(this));
		this.modalPesquisaRapida.on('shown.bs.modal', onModalShow.bind(this));
	}
	
	function onModalShow(){
		this.campoBuscaNomeCliente.focus();
	}
	
	
	function onPesquisaRapidoClicado(event){
		event.preventDefault();
		
		$.ajax({
			url: this.modalPesquisaRapida.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.campoBuscaNomeCliente.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});		
	}
	
	
	function onPesquisaConcluida(evento){
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(evento);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPesquisaRapida = new Brewer.TabelaPesquisaRapida(this.modalPesquisaRapida);
		tabelaPesquisaRapida.iniciar();
	}
	
	    function onErroPesquisa(){
	    	this.mensagemErro.removeClass('hidden');
	    }
	    


	return PesquisaRapida;

})();

Brewer.TabelaPesquisaRapida = (function(){
	
	function TabelaPesquisaRapida(modal){
		this.modalCliente = modal;
		this.cliente = $('.js-tabela-cliente-rapido');
	}
	
	TabelaPesquisaRapida.prototype.iniciar = function(){
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento){
		this.modalCliente.modal('hide');
		var clienteSelecionado = $(evento.currentTarget);
		$('#nomeCliente').val(clienteSelecionado.data('nome'));
		$('#codigoCliente').val(clienteSelecionado.data('codigo'));
	}
	

	return TabelaPesquisaRapida;

})();

$(function(){
	var pesquisaRapida = new Brewer.PesquisaRapida;
	pesquisaRapida.iniciar();
});