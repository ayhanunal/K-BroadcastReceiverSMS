package com.deadlock.broadcastreceiversmsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val b = intent?.extras
        if(b != null){

            val pdusObj = b.get("pdus") as Array<Any>
            for (i in pdusObj.indices){
                //indexleri verir

                val message : SmsMessage

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    val format = b.getString("format")
                    message = SmsMessage.createFromPdu(pdusObj[i] as ByteArray, format)
                }else{
                    message = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                }

                val telNo = message.displayOriginatingAddress
                val responseMessage = message.displayMessageBody

                Toast.makeText(context, "$telNo - $responseMessage", Toast.LENGTH_SHORT).show()

            }

        }



    }

}