package cordova.plugin.conectaredecard;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.NumberFormat;

import rede.smartrede.sdk.Intents;
import rede.smartrede.sdk.Payment;

/**
 * Created by gelatti on 10/08/16.
 */
public class ConectaRedeCard extends CordovaPlugin {

    private static final String TAG = "RedeCard";
    private static final String LAUNCH_PAYMENT = "launchPayment";
    private CallbackContext callbackContext;
    private JSONArray executeArgs;

    public static final int UNKNOWN_ERROR = 0;
    public static final int INVALID_ARGUMENT_ERROR = 1;
    public static final int TIMEOUT_ERROR = 2;
    public static final int PENDING_OPERATION_ERROR = 3;
    public static final int IO_ERROR = 4;
    public static final int NOT_SUPPORTED_ERROR = 5;
    public static final int OPERATION_CANCELLED_ERROR = 6;
    public static final int PERMISSION_DENIED_ERROR = 20;
    public static final int COLLECT_PAYMENT_REQUEST = 54321;

    /**
     * Executa a solicitacao e retorna PluginResult.
     *
     * @param action            A ação que vai ser executada.
     * @param args              Array de JSON arguments para o plugin.
     * @param callbackContext   O contexto de callback que será retornado para o javascript.
     * @return                  Verdadeira se a ação é valida caso contrario falso.
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.executeArgs = args;
        JSONObject arg_object = args.getJSONObject(0);
        if (LAUNCH_PAYMENT.equals(action)) {
            Long amount = arg_object.getLong("amount");
            String referenceId = arg_object.getString("referenceId");
            String currencyCode = "BRL";
            Payment payment = new Payment();
            payment.setReferenceId(referenceId);
            payment.setCurrency(currencyCode);
            payment.setAmount(amount);
            try {
                Intent collectPaymentIntent = new Intent(Intents.ACTION_COLLECT_PAYMENT);
                collectPaymentIntent.putExtra(Intents.INTENT_EXTRAS_PAYMENT, payment);
                this.cordova.startActivityForResult(this, collectPaymentIntent, COLLECT_PAYMENT_REQUEST);
            } catch (ActivityNotFoundException ex) {
                Log.e(TAG, "Pagamento RedeCard não encontrado, você instalou o app da RedeCard?", ex);
                this.callbackContext.error(getErrorString());
            }
        }
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == COLLECT_PAYMENT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data!=null) {
                    Payment payment = data.getParcelableExtra(Intents.INTENT_EXTRAS_PAYMENT);
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Type paymentType = new TypeToken<Payment>(){}.getType();
                    String paymentResult = gson.toJson(payment, paymentType);
                    this.callbackContext.success(paymentResult);
                    return;
                }else{
                    callbackContext.error(getNoReturn());
                    return;
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                callbackContext.error(getErrorString());
                return;
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, UNKNOWN_ERROR));
        }
    }

    private String getErrorString(){
        return "{\n" +
               "    \"status\": \"TRANSACTION_CANCELLED\"\n" +
               "}";
    }

    private String getNoReturn() {
        return "{\n" +
               "    \"status\": \"TRANSACTION_NO_RETURN\"\n" +
               "}";
    }
}
