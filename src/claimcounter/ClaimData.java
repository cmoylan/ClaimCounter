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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author chrismoylan
 */
public class ClaimData {
    private String urlString = "http://localhost:3000/counter_data";

    public ClaimData() {        
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            String s = dis.readLine();
            //System.out.println(dis.readLine());
            is.close();
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            //obj['']
            //JSONArray array=(JSONArray)obj;
            //JSONObject obj2=(JSONObject)array.get(1);

            System.out.print(obj);
            //System.out.print(obj2.get("claim_count"));
            //JSONObject(s);
            //while ((s = dis.readLine()) != null) {
            //    System.out.println(s);
            //}
            
        } catch (Exception blah) {
            
        }
    }
    
    public String getClaimCount() {
        return "";
    }
    
}
