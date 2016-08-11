plugin cordova ConectaRedeCard

Para usa-lo coloque os arquivos e pastas do projeto dentro da pasta plugins/cordova-plugin-conectaredecard</br>
Assim será gerado uma variável ConectaRedeCard.</br>
Para chamar o pagamento use ConectaRedeCard.lancaPagamento(valor, idReferencia, callbackSucesso, callbackFalha)</br>
O callbackSucesso retorna um JSON com as informações do pagamento.</br>
O callbackFalha retorna um JSON com a falha.</br>
