var Brewer = Brewer || {};

Brewer.MaskMoney = (function(){
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({decimal:',', thousands:'.'});
		this.plain.maskMoney({ precision: 0, thousands:'.'});
	}	
	
	return MaskMoney;

})();

Brewer.MaskPhone = (function(){
	
	function MaskPhone(){
		this.phoneNumber = $('.js-phone_number');
	}
	
	MaskPhone.prototype.enable = function(){
		var maskBehavior = function (val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		var options = {
		  clearIfNotMatch: true,
		  onKeyPress: function(val, e, field, options) {
		      field.mask(maskBehavior.apply({}, arguments), options);
		      }
		};
		
		this.phoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhone;
	
})();

Brewer.MaskCep = (function(){
	
	function MaskCep(){
		this.inputCep = $('.js-cep');
	}
	
	MaskCep.prototype.iniciar = function(){
		var mask = '00.000-000';
		var options = {
				clearIfNotMatch: true
		};
		
		this.inputCep.mask(mask, options);
	}
	
	return MaskCep;
	
})();

Brewer.MaskDate = (function(){
	
	function MaskDate(){
		this.dataNascimento = $('.js-date');
	}
	
	MaskDate.prototype.enable = function(){
		this.dataNascimento.mask('00/00/0000');
		this.dataNascimento.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	
	return MaskDate;
})();

Brewer.Security = (function(){
	function Security(){
		this.token = $('input[name=_csrf]').val();
		this.tokenNome = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function(){
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.tokenNome, this.token);
		}.bind(this));
	}
	
	return Security;	

})();

//numeral.locale('pt-br');

//Brewer.recuperarValor = function(valorFormatado) {
//	return numeral(valorFormatado);
//}

//Brewer.formatarMoeda = function(valor){
//	return numeral(valor).format('0,0.00');
//}

numeral.language('pt-br');

Brewer.formatarMoeda = function(valor) {
	return numeral(valor).format('0,0.00');
}

Brewer.recuperarValor = function(valorFormatado) {
	return numeral().unformat(valorFormatado);
}

$(function(){
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
	
	var maskPhone = new Brewer.MaskPhone();
	maskPhone.enable();
	
	var maskCep = new Brewer.MaskCep();
	maskCep.iniciar();
	
	var maskDate = new Brewer.MaskDate();
	maskDate.enable();
	
	var security = new Brewer.Security();
	security.enable();
});