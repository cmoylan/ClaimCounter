/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author chrismoylan
 */
public class ClaimData {
    private String urlString = "https://localhost/counter_data";
    private JSONObject counterDataJSON;

    public ClaimData() {
        //updateData();
    }

    public Integer fetch(String key) {
        Integer count = 0;

        if (counterDataJSON != null) {
            Object value = counterDataJSON.get(key);

            if (value != null) {
                // TODO: this seems a little rediculous
                count = Integer.valueOf(value.toString());
            }
        }

        return count;
    }

    public void updateData() {
        // TODO: handle HTTPS
        JSONObject oldData = counterDataJSON;

        // --- Ugly hack to trust unsigned SSL certs. --- //
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        try {
            // fuck java, in ruby this would be:
            //      conn.trustUntrusted(true)
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {}

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
              return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /// --- END Ugly hack to trust unsigned SSL certs. --- //

        try {
            URL url = new URL(urlString);
            //URLConnection conn = url.openConnection();
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            String s = dis.readLine();
            is.close();
            //System.out.printf(s);

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            counterDataJSON = (JSONObject) obj;
        }
        catch (Exception e) {
            e.printStackTrace();
            // Restore counterDataJSON from the last good version.
            counterDataJSON = oldData;
            // TODO: probably should do more

        }
    }
}
