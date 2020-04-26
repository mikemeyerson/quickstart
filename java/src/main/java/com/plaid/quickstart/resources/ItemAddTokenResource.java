package com.plaid.quickstart.resources;

import java.io.IOException;
import java.util.Date;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.ItemAddTokenCreateRequest;
import com.plaid.client.response.ItemAddTokenCreateResponse;
import com.plaid.quickstart.QuickstartApplication;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import retrofit2.Response;

@Path("/create_item_add_token")
@Produces(MediaType.APPLICATION_JSON)
public class ItemAddTokenResource {
  private PlaidClient plaidClient;

  public ItemAddTokenResource(PlaidClient plaidClient) {
    this.plaidClient = plaidClient;
  }

  @POST
  public ItemAddTokenCreateResponse createItemAddToken() throws IOException {
    String clientUserId = Long.toString((new Date()).getTime());

    Response<ItemAddTokenCreateResponse> itemAddTokenResponse = 
      plaidClient.service()
      .itemAddTokenCreate(
          new ItemAddTokenCreateRequest(
            new ItemAddTokenCreateRequest.User(clientUserId)
          )
      )
      .execute();
    
    return itemAddTokenResponse.body();
  }
}
