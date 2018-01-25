
package tidapp.trimble.Login;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Class used to store login response
 * <p>
 * Created by Shashadhar on 28-Dec-17.
 */

@SuppressWarnings("unused")
public class LoginResponse implements Parcelable {

    //region member variables

    /**
     * Used to store access token
     */
    @SerializedName("access_token")
    private String mAccessToken;

    /**
     * Used to store expire time
     */
    @SerializedName("expires_in")
    private Long mExpiresIn;

    /**
     * Used to store refresh token
     */
    @SerializedName("refresh_token")
    private String mRefreshToken;

    /**
     * Used to token type
     */
    @SerializedName("token_type")
    private String mTokenType;

    /**
     * Used to store email
     */
    private String email;

    /**
     * Used to store password
     */
    private String password;
    //endregion member variables


    //region properties getter/setter

    /**
     * Get email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set password
     *
     * @param password password to set
     */
    @SuppressWarnings("WeakerAccess")
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get access token
     *
     * @return access token
     */
    public String getAccessToken() {
        return mAccessToken;
    }

    /**
     * set access token
     *
     * @param accessToken access token
     */
    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    /**
     * get expire value
     *
     * @return long expire value
     */
    public Long getExpiresIn() {
        return mExpiresIn;
    }

    /**
     * set expire value
     *
     * @param expiresIn expire
     */
    public void setExpiresIn(Long expiresIn) {
        mExpiresIn = expiresIn;
    }

    /**
     * get refresh token
     *
     * @return refresh token
     */
    public String getRefreshToken() {
        return mRefreshToken;
    }

    /**
     * set refresh token
     *
     * @param refreshToken refresh token
     */
    @SuppressWarnings("WeakerAccess")
    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }

    /**
     * get token type
     *
     * @return token type
     */
    public String getTokenType() {
        return mTokenType;
    }

    /**
     * set token type
     *
     * @param tokenType token type
     */
    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }
    //endregion properties getter/setter


    //region constructor

    public LoginResponse() {
    }
    //endregion constructor


    //region parcelable interface
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * flatten this object in to a Parcel.
     *
     * @param dest: The Parcel in which the object should be written.
     * @param flags int: Additional flags about how the object should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
     */
    @SuppressWarnings("WeakerAccess")
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAccessToken);
        dest.writeValue(this.mExpiresIn);
        dest.writeString(this.mRefreshToken);
        dest.writeString(this.mTokenType);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    /**
     * Get the object from parcel
     *
     * @param in The Parcel from which the object should be written.
     */
    @SuppressWarnings("WeakerAccess")
    protected LoginResponse(Parcel in) {
        this.mAccessToken = in.readString();
        this.mExpiresIn = (Long) in.readValue(Long.class.getClassLoader());
        this.mRefreshToken = in.readString();
        this.mTokenType = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    /**
     * Interface that must be implemented and provided as a public CREATOR field that generates instances of your Parcelable class from a Parcel.
     */
    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel source) {
            return new LoginResponse(source);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };
    //endregion parcelable interface
}
