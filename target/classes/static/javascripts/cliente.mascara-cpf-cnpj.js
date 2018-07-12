var Brewer = Brewer || {};

Brewer.MascaraCpfCnpj = (function(){
	
	function MascaraCpfCnpj(){
		this.radioTipoPessoa = $('.js-tipoPessoaRadio');
		this.labelCpfCnpj = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function(){
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
		var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
		if(tipoPessoaSelecionada){
			formatarCampo.call(this, $(tipoPessoaSelecionada))
		}
	}
	
	function onTipoPessoaAlterado(evento){
		var tipoPessoaSelecionada = $(evento.currentTarget);

		
		formatarCampo.call(this, tipoPessoaSelecionada);
		this.inputCpfCnpj.val('');
	}
	
	function formatarCampo(tipoPessoaSelecionada){
		var options = {
				clearIfNotMatch: true
		};	
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'), options);
		this.inputCpfCnpj.removeAttr('disabled');	
	}
	
	

	return MascaraCpfCnpj;
	
})();


$(function(){
	var mascaraCpfCnpj = new Brewer.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();

});