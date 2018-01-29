package tidapp.trimble.Login;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tidapp.trimble.TIDApplication;
import tidapp.trimble.network.NetworkService;
import tidapp.trimble.utils.Util;

/**
 * Presenter class for Login
 * <p>
 * Created by Shashadhar on 28-Dec-17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    //region member variable

    /**
     * Used to store reference of Network service
     */
    private NetworkService service;

    /**
     * Used to keep reference of view
     */
    private LoginContract.View mLoginView;
    //endregion member variable


    //region constructor

    /**
     * Constructor to create presenter
     *
     * @param networkService service reference
     * @param view           View for the presenter
     */
    @SuppressWarnings("WeakerAccess")
    public LoginPresenter(NetworkService networkService, LoginContract.View view) {
        service = networkService;
        mLoginView = view;
        mLoginView.setPresenter(this);
    }
    //endregion constructor


    //region interface LoginContract.Presenter method implementation

    /**
     * Method to subscribe to presenter
     */
    @Override
    public void subscribe() {

    }

    /**
     * Method to unsubscribe from presenter
     */
    @Override
    public void unsubscribe() {
        mLoginView = null;
    }

    /**
     * Logs in user by calling TID api and uses observable
     *
     * @param email    email to login
     * @param password password
     */
    @Override
    public void loginUser(String email, String password) {
        try {
            Map<String, String> headers = new HashMap<>();
            String clientSecret = TIDApplication.applicationID + ":" + TIDApplication.clientSecret;
            String base64EncodedAuthorization = "Basic " + Util.toBase64String(clientSecret);
            headers.put("Authorization", base64EncodedAuthorization);
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put("Accept", "application/json");

            service.getAPI().userLogin(headers, "password", email, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((new Observer<LoginResponse>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            // TODO: 29-Jan-18 manage disposable object
                            Log.e("Log in response", "onSubscribe");
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            // TODO: 29-Jan-18 save session
                            Log.e("Log in response", loginResponse.toString());
                            if (null != mLoginView) {
                                mLoginView.hideProgressDialog();
                                loginResponse.setEmail(email);
                                loginResponse.setPassword(password);
                                mLoginView.launchHomeActivity(loginResponse);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.e("Error", "Login error");
                            if (null != mLoginView) {
                                mLoginView.hideProgressDialog();
                                mLoginView.showMessage("We got some error in signing in.");
                            }
                        }

                        @Override
                        public void onComplete() {
                            Log.e("OnComplete", "Subscriber completed");
                        }
                    }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs in user by calling TID api and uses call
     */
    @Override
    public void login() {
        try {
            Call<LoginResponse> call = service.getAPI().loginUser();
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion interface LoginContract.Presenter method implementation
}
