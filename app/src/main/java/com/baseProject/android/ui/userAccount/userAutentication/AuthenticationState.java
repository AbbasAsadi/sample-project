package com.baseProject.android.ui.userAccount.userAutentication;

public enum AuthenticationState {

    UNAUTHENTICATED,        // Initial state, the user needs to authenticate
    AUTHENTICATED,          // The user has authenticated successfully
    INVALID_AUTHENTICATION, // Authentication failed
    AUTHENTICATION_REJECTED // when Authentication rejected by user
}
