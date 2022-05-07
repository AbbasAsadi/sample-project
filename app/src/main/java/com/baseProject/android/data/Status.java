package com.baseProject.android.data;

import androidx.annotation.Keep;

/**
 * The enum Status.
 *
 * @author Abbas Asadi
 * The status of API response
 */
@Keep
public enum Status {
    /**
     * Success status.
     */
    SUCCESS,
    /**
     * Network Http Error status.
     */
    ERROR,
    /**
     * Loading state.
     */
    LOADING,
    /**
     * Empty response status.
     */
    EMPTY,
    /**
     * Server error status.
     * This error generated with server
     */
    SERVER_ERROR,
    /**
     * Server connection error status.
     */
    CONNECTION_ERROR
}
