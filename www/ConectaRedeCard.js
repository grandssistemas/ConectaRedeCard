var ConectaRedeCard = {
	lancaPagamento: function (amount, referenceId, paymentType, installments, successCallback, errorCallback){
		cordova.exec(successCallback,
			errorCallback,
			'ConectaRedeCard',
			'lancaPagamento',
			[{
				"amount": amount,
				"referenceId": referenceId,
				"paymentType": paymentType, 
				"installments": installments
			}]);
	}
}

module.exports = ConectaRedeCard;