package tidapp.trimble.home;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import tidapp.trimble.Login.LoginResponse;
import tidapp.trimble.TIDApplication;
import tidapp.trimble.network.NetworkService;
import tidapp.trimble.utils.Util;

/**
 * Presenter class for home view
 * Created by Shashadhar on 28-Dec-17.
 */

public class HomePresenter implements HomeContract.Presenter {

    //region member variables

    /**
     * Used for logging
     */
    private static String TAG = HomePresenter.class.getSimpleName();

    /**
     * Used to store service reference
     */
    private NetworkService service;

    /**
     * Used to store view reference
     */
    private HomeContract.View mHomeView;
    //endregion member variables


    //region constructor

    /**
     * Constructor to create presenter
     *
     * @param networkService service reference
     * @param view           View for the presenter
     */
    @SuppressWarnings("WeakerAccess")
    public HomePresenter(NetworkService networkService, HomeContract.View view) {
        service = networkService;
        mHomeView = view;
        mHomeView.setPresenter(this);
    }
    //endregion constructor


    //region interface HomeContract.Presenter method implementation

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
    @SuppressWarnings("SpellCheckingInspection")
    public void unsubscribe() {
        mHomeView = null;
    }

    /**
     * Retrieves the refresh token
     *
     * @param refreshToken refresh token
     */
    @Override
    public void refreshAccessToken(String refreshToken) {
        try {
            Map<String, String> headers = new HashMap<>();
            String clientSecret = TIDApplication.applicationID + ":" + TIDApplication.clientSecret;
            String base64EncodedAuthorization = "Basic " + Util.toBase64String(clientSecret);
            headers.put("Authorization", base64EncodedAuthorization);
            headers.put("Content-Type", "application/x-www-form-urlencoded");
            headers.put("Accept", "application/json");


            service.getAPI().refreshToken(headers, "refresh_token", "openid", refreshToken, "http://Google.com", "trimble.com")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((new Observer<LoginResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            // TODO: 29-Jan-18 manage disposable object
                            Log.e(TAG, "onSubscribe");
                        }

                        @Override
                        public void onNext(LoginResponse loginResponse) {
                            // TODO: 29-Jan-18 save session
                            Log.e(TAG, loginResponse.toString());
                            if (null != mHomeView) {
                                mHomeView.hideProgressDialog();
                                mHomeView.setUserInfo(loginResponse);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.e("TAG", "Error");
                            if (null != mHomeView) {
                                mHomeView.hideProgressDialog();
                                mHomeView.showMessage("We got some error in refreshing the token.");
                            }
                        }

                        @Override
                        public void onComplete() {
                            Log.e("TAG", "Complete");
                        }
                    }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs out user
     *
     * @param accessToken access token of the user
     */
    @Override
    public void logoutUser(@NonNull String accessToken) {
        try {
            Map<String, String> headers = new HashMap<>();
            String clientSecret = TIDApplication.applicationID + ":" + TIDApplication.clientSecret;
            String base64EncodedAuthorization = "Basic " + Util.toBase64String(clientSecret);
            headers.put("Authorization", base64EncodedAuthorization);
            headers.put("Content-Type", "application/x-www-form-urlencoded");

            service.getAPI().userLogout(headers, "access_token", accessToken)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((new Observer<Response<Void>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e("TAG", "onSubscribe");
                        }

                        @Override
                        public void onNext(Response<Void> response) {
                            if (null != mHomeView) {
                                mHomeView.hideProgressDialog();
                                mHomeView.launchLoginActivity();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            Log.e("TAG", "Error");
                            if (null != mHomeView) {
                                mHomeView.hideProgressDialog();
                                mHomeView.showMessage("We got some error in log out.");
                            }
                        }

                        @Override
                        public void onComplete() {
                            Log.e("TAG", "Complete");
                        }
                    }));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion interface HomeContract.Presenter method implementation

}
