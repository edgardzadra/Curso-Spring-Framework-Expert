var Brewer = Brewer || {};

Brewer.Multiselecao = (function(){
	
	function Multiselecao(){
		this.btnAcaoStatus = $('.js-status-btn');
		this.codigoStatusCheckbox = $('.js-selecao');
		this.codigoStatusCheckboxTodos = $('.js-selecao-todos');
	}
	
	Multiselecao.prototype.iniciar = function(){
		this.btnAcaoStatus.on('click', onAlterarStatus.bind(this));
		this.codigoStatusCheckboxTodos.on('click', onCheckTodosStatus.bind(this));
		this.codigoStatusCheckbox.on('click', onCheckStatus.bind(this));
	}
	
	function onAlterarStatus(event){
		var botaoClicado = $(event.currentTarget);
		var status = botaoClicado.data('status');
		
		var checkboxSelecionados = this.codigoStatusCheckbox.filter(':checked');
		var codigos = $.map(checkboxSelecionados, function(c){
			return $(c).data('codigo');
		});
		
		var url = this.btnAcaoStatus.data('url');
		
		if(codigos.length > 0){
			$.ajax({
				url: url,
				method: 'PUT',
				data: {
					codigos: codigos,
					status: status
				},
				success: function(){
					window.location.reload();
				}
			});
		}
	}
	
	function onCheckTodosStatus(){
		var status = this.codigoStatusCheckboxTodos.prop('checked');
		this.codigoStatusCheckbox.prop('checked', status);
		ativarDesativarBotaoStatus.call(this, status);
	}
	
	function onCheckStatus(){
		var statusCont = this.codigoStatusCheckbox.filter(':checked');
		this.codigoStatusCheckboxTodos.prop('checked', statusCont.length >= this.codigoStatusCheckbox.length);
		ativarDesativarBotaoStatus.call(this, statusCont.length);
	}
	
	function ativarDesativarBotaoStatus(ativar){
		ativar ? this.btnAcaoStatus.removeClass('disabled') : this.btnAcaoStatus.addClass('disabled');
		
	}
	
	
	return Multiselecao;

})();

$(function(){
	var multiselecao = new Brewer.Multiselecao();
	multiselecao.iniciar();

});