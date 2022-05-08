package com.baseProject.android.util.userAutentication;

import static com.baseProject.android.util.userAutentication.AuthenticationState.AUTHENTICATED;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.baseProject.android.data.remote.authenticator.TokenManager;
import com.baseProject.android.ui.ParentViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


/**
 * @author AbbasAsadi
 */
public class UserAuthenticationViewModel extends ParentViewModel {

    private final MutableLiveData<AuthenticationState> authenticationState = new MutableLiveData<>();

    @Inject
    public UserAuthenticationViewModel() {
        readUserToken();
    }


    private void readUserToken() {
        String userToken = TokenManager.getToken();
        if (TextUtils.isEmpty(userToken)) {
            authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        } else {
            authenticationState.setValue(AUTHENTICATED);
        }
    }


    public void changeUserAuthenticationState(@NotNull AuthenticationState state) {
        authenticationState.postValue(state);
    }


    public void resetAuthentication() {
        TokenManager.clearToken();
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    public MutableLiveData<AuthenticationState> getAuthenticationState() {
        return authenticationState;
    }

    public boolean userIsAuthenticated() {
        return authenticationState.getValue() != null && authenticationState.getValue() == AUTHENTICATED;
    }

}
