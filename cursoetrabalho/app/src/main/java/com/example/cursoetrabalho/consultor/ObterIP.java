package com.example.cursoetrabalho.consultor;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ObterIP {

    public static String obterEnderecoIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();

            // Formatar o endereço IP
            String ip = formatIpAddress(ipAddress);
            Log.d("NetworkUtils", "Endereço IP: " + ip);
            return ip;
        } else {
            Log.e("NetworkUtils", "WifiManager é nulo");
            return null;
        }
    }

    private static String formatIpAddress(int ipAddress) {
        return (ipAddress & 0xFF) + "." +
                ((ipAddress >> 8) & 0xFF) + "." +
                ((ipAddress >> 16) & 0xFF) + "." +
                (ipAddress >> 24 & 0xFF);
    }
}
