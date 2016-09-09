plugin cordova ConectaRedeCard

Para usa-lo coloque os arquivos e pastas do projeto dentro da pasta plugins/cordova-plugin-conectaredecard/</br>
Assim será gerado uma variável ConectaRedeCard.</br>
Para chamar o pagamento use ConectaRedeCard.lancaPagamento(valor, idReferencia, tipodePagamento, nrParcelas, callbackSucesso, callbackFalha);</br>
Valor deve ser mandado de forma sem ponto ou virgula (EX: R$ 7,00 deve ser enviado 700).</br>
O idReferencia deve ser informado um valor para informar qual a origem da solicitação geralmente utilizado o ID da venda efetuada.</br>
O tipo de pagamento pode ser (passe o ENUM em forma de String. Ex: 'CREDITO_A_VISTA'):
  -  CREDITO_A_VISTA("à vista")</br>
  -  CREDITO_PARCELADO("parcelado")</br>
  -  CREDITO_PARCELADO_EMISSOR("parcelado emissor")</br>
  -  DEBITO("à vista")</br>
  -  VOUCHER("à vista")</br>

O numero de parcelas deve conter o numero de parcelas no caso de uma venda pacelada ou 1 quando não parcelada.</br>
O callbackSucesso retorna um JSON com as informações do pagamento.</br>
O callbackFalha retorna um JSON com a falha.
