/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.andar.shiro;

import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;

/**
 *
 * @author andfoy
 */
public class StormpathUtils 
{
    private static Client client;
    public static String href = "https://api.stormpath.com/v1/applications/22NAHVhkM1U4hGRxiVnt5U";
    
    public static Client getClient()
    {
        if(client == null)
        {
            ApiKey apiKey = ApiKeys.builder().setId(System.getenv("STORMPATH_API_KEY_ID")).setSecret(System.getenv("STORMPATH_API_KEY_SECRET")).build();
            client = Clients.builder().setApiKey(apiKey).build();
        }
        return client;
    }
    
}
