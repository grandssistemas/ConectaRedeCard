var ConectaRedeCard = {
	lancaPagamento: function (amount, referenceId, successCallback, errorCallback){
		cordova.exec(successCallback,
			errorCallback,
			'ConectaRedeCard',
			'lancaPagamento',
			[{
				"amount": amount,
				"referenceId": referenceId
			}]);
	}
}

module.exports = ConectaRedeCard;