package com.sinan.mytest.client;

import com.google.gson.Gson;
import com.sinan.mytest.utils.AppConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * ClientConfig class configures rest adapter.
 *
 * @author Sinan Shoukath
 */
public class ClientConfig {
  public static ClientInterface createClientInterface() {
    RequestInterceptor requestInterceptor = new RequestInterceptor() {
      @Override public void intercept(RequestFacade request) {
        request.addHeader("X-Parse-Application-Id", AppConfig.PARSE_APPLICATION_ID);
        request.addHeader("X-Parse-REST-API-Key", AppConfig.REST_API_KEY);
      }
    };
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(AppConfig.PARSE_BASE_URL)
        .setRequestInterceptor(requestInterceptor)
        .setConverter(new GsonConverter(new Gson()))
        .setClient(new OkClient(new OkHttpClient()))
        .build();
    return restAdapter.create(ClientInterface.class);
  }
}
