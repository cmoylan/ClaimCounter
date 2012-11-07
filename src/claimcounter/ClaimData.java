/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author chrismoylan
 */
public class ClaimData {
    private String urlString = "http://localhost:3000/counter_data";
    private JSONObject counterDataJSON;

    public ClaimData() {
        updateData();
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

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            String s = dis.readLine();
            is.close();

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            counterDataJSON = (JSONObject) obj;
        }
        catch (Exception blah) {
            // Restore counterDataJSON from the last good version.
            counterDataJSON = oldData;
            // TODO: probably should do more

        }
    }
}
