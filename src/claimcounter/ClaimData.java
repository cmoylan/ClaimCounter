/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

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
            System.out.print(is);
            
        } catch (Exception blah) {
            
        }
    }
    
    public String getClaimCount() {
        return "";
    }
    
}
