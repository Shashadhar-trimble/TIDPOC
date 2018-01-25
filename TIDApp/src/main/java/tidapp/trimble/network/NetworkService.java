package tidapp.trimble.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Network service class provides service for api calls
 * initializes Retrofit object
 * provides single instance
 *
 * Created by Shashadhar on 1/25/16.
 */

public class NetworkService{

    //region constants

    /**
     * TID base url value
     */
    private static String baseUrl ="https://identity-stg.trimble.com/";
    //endregion constants


    //region member variables

    /**
     * Static variable used to store instance
     */
    private static NetworkService instance=new NetworkService();

    /**
     * Used to store NetworkAPI reference
     */
    private NetworkAPI networkAPI;
    //endregion member variables


    //region constructor

    /**
     * No-arg constructor
     */
    private NetworkService(){
        this(baseUrl);
    }

    /**
     * Constructor takes base url as param
     * initializes retrofit instance
     * @param baseUrl base url for TID integration
     */
    private NetworkService(String baseUrl){
        try {
            OkHttpClient   okHttpClient = buildClient();
            Retrofit retrofit= new Retrofit.Builder()
                     .baseUrl(baseUrl)
                     .addConverterFactory(GsonConverterFactory.create())
                     .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .client(okHttpClient)
                     .build();
            networkAPI = retrofit.create(NetworkAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion constructor


    //region factory methods

    /**
     * returns the instance
     */
     public static NetworkService getInstance(){
         return instance;
     }
     //endregion factory methods


     //region getter
    /**
     * Method to return the API interface.
     * @return NetworkAPI
     */
    public NetworkAPI getAPI(){
        return  networkAPI;
    }
    //endregion getter


    //region private methods
    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     * @return http client
     */
    private OkHttpClient buildClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        try {
            // Do anything with response here
            //if we ant to grab a specific cookie or something
            builder.addInterceptor(chain -> chain.proceed(chain.request()));

            //this is where we will add whatever we want to our request headers.
            builder.addInterceptor(chain -> {
               // Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(chain.request());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  builder.build();
    }
    //endregion private methods

}
