Brewer.TabelaItens = (function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaItensVendaContainer = $('.js-tabela-itens-venda-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
		
		bindQuantidade.call(this);
		bindTabelaItem.call(this);
	}
	
	TabelaItens.prototype.valorTotal = function(){
		return this.tabelaItensVendaContainer.data('valor');
	}
	
	
	function onItemSelecionado(e, item){
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoCerveja: item.codigo,
				uuid: this.uuid
			}
		});
		
		resposta.done(onRenderizarItensTabelaVenda.bind(this));
		
	}
	
	function onRenderizarItensTabelaVenda(html){
		this.tabelaItensVendaContainer.html(html);
		
		bindQuantidade.call(this);
		var tabelaItem = bindTabelaItem.call(this);
			
		this.emitter.trigger('tabela-itens-atualizada', tabelaItem.data('valor-total'));
	}
	
	function onAlterarQuantidadeCerveja(evento){
		
		var input = $(evento.target);
		var quantidadeCerveja = input.val();
		if(quantidadeCerveja <= 0){
			input.val(1);
			quantidadeCerveja = 1;
		}
		
		
		var codigoCerveja = input.data('codigo-cerveja');
		
		
		var resposta = $.ajax({
			url: 'item/' + codigoCerveja,
			method: 'PUT',
			data: {
				quantidade: quantidadeCerveja,
				uuid: this.uuid
			}
		});
		
		resposta.done(onRenderizarItensTabelaVenda.bind(this));
		
	}
	
	function onDoubleClick(evento){
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExcluirItemTabela(evento){
		var input = $(evento.target);
		var codigoCerveja = input.data('codigo-cerveja');
		
		var resposta = $.ajax({
			url: 'item/'+ this.uuid + '/' + codigoCerveja,
			method: 'DELETE'
		});
		
		resposta.done(onRenderizarItensTabelaVenda.bind(this));
	}
	
	function bindQuantidade(){
		var quantidadeItemInput = $('.js-campo-alterar-quantidade-cerveja');		
		quantidadeItemInput.on('change', onAlterarQuantidadeCerveja.bind(this));
		quantidadeItemInput.maskMoney({precision:0, thousands: ''});
	}
	
	function bindTabelaItem(){
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);
		$('.js-botao-exclusao').on('click', onExcluirItemTabela.bind(this));
		
		return tabelaItem;
	}
	

	return TabelaItens;
	
})();

