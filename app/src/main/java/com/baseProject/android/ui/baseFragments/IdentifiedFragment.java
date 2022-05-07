package com.baseProject.android.ui.baseFragments;


import static com.baseProject.android.ui.userAccount.userAutentication.AuthenticationState.AUTHENTICATED;
import static com.baseProject.android.ui.userAccount.userAutentication.AuthenticationState.UNAUTHENTICATED;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.baseProject.android.data.DataWrapper;
import com.baseProject.android.data.publicModel.exception.TokenNotVerifiedException;
import com.baseProject.android.data.remote.authenticator.TokenManager;
import com.baseProject.android.ui.login.activity.LoginActivity;
import com.baseProject.android.ui.userAccount.userAutentication.UserAuthenticationViewModel;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author Abbas Asadi
 */
public abstract class IdentifiedFragment extends BaseFragment {

    protected UserAuthenticationViewModel userAuthenticationViewModel;

    public IdentifiedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userAuthenticationViewModel = new ViewModelProvider(this, viewModelFactory).get(UserAuthenticationViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeAuthenticationStatus();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }


    private void subscribeAuthenticationStatus() {
        userAuthenticationViewModel.getAuthenticationState().observe(getViewLifecycleOwner(), authenticationState -> {
            if (authenticationState == AUTHENTICATED) {
                onAuthenticated();
            } else if (authenticationState == UNAUTHENTICATED) {
                onUnAuthenticated();
            }
        });
    }

    /**
     * called when user is in authenticated state in each fragment
     */
    public abstract void onAuthenticated();

    /**
     * called when user is in unAuthenticated state in each fragment
     */
    public abstract void onUnAuthenticated();

    /**
     * check server response and handle some specific exceptions and
     * do some specific action to response of these exceptions
     *
     * @param dataWrapper : response of server
     * @return false if we have some exception returned from server
     */
    protected boolean checkResponse(DataWrapper<?> dataWrapper) {
        if (dataWrapper.throwable instanceof TokenNotVerifiedException) {

            TokenManager.clearToken();
            userAuthenticationViewModel.changeUserAuthenticationState(UNAUTHENTICATED);
            return false;
        }
        return true;
    }

    /**
     * redirect user to login activity with intent
     *
     * @param context
     */
    public void redirectToLogin(Context context) {
        TokenManager.clearToken();
        startActivity(new Intent(context, LoginActivity.class));
    }
}
