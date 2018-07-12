var Brewer = Brewer || {};

Brewer.Autocomplete = (function(){
	
	function Autocomplete(){
		this.inputNomeOuSku = $('.js-autocomplete-nome-sku');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function(){	
		var options = {
		    url: function(skuOuNome){
			 return this.inputNomeOuSku.data('url') + '?skuOuNome=' + skuOuNome;
			}.bind(this),
		    getValue: 'nome',
		    requestDelay: 300,
		    minCharNumber: 3,
		    ajaxSettings:{
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list: {
				onChooseEvent: onItemSelecionado.bind(this)
			}		
		};
		
		this.inputNomeOuSku.easyAutocomplete(options);
	}
	
	function onItemSelecionado(){
		this.emitter.trigger('item-selecionado', this.inputNomeOuSku.getSelectedItemData());
		this.inputNomeOuSku.val('');
		this.inputNomeOuSku.focus();
	}
	
	function template(nome, cerveja){
		cerveja.valorFormatado = Brewer.formatarMoeda(cerveja.valor);
		return this.template(cerveja);
	}
	
	
	return Autocomplete;
	

})();