package io.projectZ.orchestrator.infrastructure.adapter.out.restClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/10/2026 - 6:41 PM
*/

import org.springframework.web.client.RestClient;

public class OpenFireRestClient {

    public RestClient restClient = RestClient.builder().baseUrl("http://130.185.121.173:9090").build();
    public boolean userExist(String username){
        try {
            restClient.get().uri("/plugins/restapi/v1/users/{username}",username)
                    .header("Authorization" ,"ztwx3EjQ3oHPIJ4T")
                    .header("accept", "application/json")
                    .retrieve().body(String.class);
        }catch (Exception e){
            System.out.println("checking user existence failed "+e);
        }
        return true;
    }
}

