package tidapp.trimble.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import tidapp.trimble.Login.LoginResponse;

/**
 * all the Service calls to use for the retrofit requests.
 * Created by sdas1 on 28-Dec-17.
 */

public interface NetworkAPI {

    /**
     * Test method to check user login
     * @return call<LoginResponse>
     */
    @GET(".")
    Call<LoginResponse> loginUser();

    /**
     * Login user with TID
     * @param headers request headers
     * @param grantType grant type
     * @param userName user name
     * @param password password
     * @return observable
     */
    @SuppressWarnings("SameParameterValue")
    @FormUrlEncoded
    @POST("oauth2/token")
    Observable<LoginResponse> userLogin(@HeaderMap Map<String, String> headers, @Field("grant_type") String grantType, @Field("username") String userName, @Field("password") String password);

    /**
     * Get access token from refresh token
     * @param headers request headers
     * @param grantType grant type
     * @param scope scope
     * @param refreshToken refresh token
     * @param redirectUri redirect uri
     * @param tenantDomain tenant domain
     * @return observable
     */
    @SuppressWarnings("SameParameterValue")
    @FormUrlEncoded
    @POST("token")
    Observable<LoginResponse> refreshToken(@HeaderMap Map<String, String> headers, @Field("grant_type") String grantType,
                                           @Field("scope") String scope, @Field("refresh_token") String refreshToken,
                                           @Field("redirect_uri") String redirectUri, @Field("tenantDomain") String tenantDomain);

}
