plugin cordova ConectaRedeCard

Para usa-lo coloque os arquivos e pastas do projeto dentro da pasta plugins/cordova-plugin-conectaredecard/
Assim será gerado uma variável ConectaRedeCard.
Para chamar o pagamento use ConectaRedeCard.lancaPagamento(valor, idReferencia, callbackSucesso, callbackFalha); 
O callbackSucesso retorna um JSON com as informações do pagamento.
O callbackFalha retorna um JSON com a falha.