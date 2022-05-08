package com.baseProject.android.ui.error;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.baseProject.android.R;
import com.baseProject.android.data.DataWrapper;
import com.baseProject.android.data.publicModel.exception.CustomRuntimeException;
import com.baseProject.android.databinding.FragmentErrorDialogBinding;
import com.baseProject.android.ui.ExtensionsKt;
import com.baseProject.android.util.BUS.RxBusKotlin;
import com.baseProject.android.util.MessageMap;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author Abbas Asadi
 * TODO: implement single responsibilty
 */
public class ErrorDialogFragment extends DialogFragment {

    private static final String TAG = ErrorDialogFragment.class.getSimpleName();
    private static final String LABEL_TAG = "label";
    private static final String MESSAGE_TAG = "message";
    private static final String ICON_TAG = "message";

    private FragmentErrorDialogBinding mBinding;
    private OnErrorActionListener listener;
    private DataWrapper dataWrapper;
    private WeakReference<FragmentManager> fragmentManagerWeakReference;
    private Disposable disposable;


    public ErrorDialogFragment() {
    }

    public static void showError(@NonNull FragmentManager fragmentManager,
                                 @NonNull DataWrapper dataWrapper,
                                 @NonNull OnErrorActionListener listener) {

        if (fragmentManager.findFragmentByTag(TAG) == null) {
            ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
            errorDialogFragment.listener = listener;
            errorDialogFragment.fragmentManagerWeakReference = new WeakReference<>(fragmentManager);
            errorDialogFragment.show(fragmentManager, TAG);
        }
        RxBusKotlin.INSTANCE.publish(dataWrapper);
    }

    public static void showError(@NonNull FragmentManager fragmentManager, @NonNull OnErrorActionListener listener,
                                 @NonNull String label, @NonNull String message, @NonNull Integer iconResource) {
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            Bundle bundle = new Bundle();
            bundle.putString(LABEL_TAG, label);
            bundle.putString(MESSAGE_TAG, message);
            bundle.putInt(ICON_TAG, iconResource);

            ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
            errorDialogFragment.listener = listener;
            errorDialogFragment.fragmentManagerWeakReference = new WeakReference<>(fragmentManager);
            errorDialogFragment.setArguments(bundle);

            errorDialogFragment.show(fragmentManager, TAG);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenErrorDialogStyle);
        disposable = RxBusKotlin.INSTANCE.subscribe(DataWrapper.class).subscribe((Consumer<Object>) message -> {
            if (message instanceof DataWrapper) {
                dataWrapper = (DataWrapper) message;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentErrorDialogBinding.inflate(inflater, container, false);
        mBinding.setAction(this);
        //init();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exceptionParser(dataWrapper);
    }

    void init() {
        if (getArguments() != null) {
            mBinding.setErrorTitle(getArguments().getString(LABEL_TAG));
            mBinding.setErrorMessage(getArguments().getString(MESSAGE_TAG));
            mBinding.setIconResource(getArguments().getInt(ICON_TAG));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        cancel();
    }

    public void onRetryButtonClick(View view) {
        ExtensionsKt.preventDoubleClick(view);
        retry();
    }

    public void onCancelButtonClick(View view) {
        ExtensionsKt.preventDoubleClick(view);
        cancel();
    }

    private void cancel() {
        this.dismiss();
        fragmentManagerWeakReference.get().beginTransaction().remove(this);
        listener.onErrorCancelButtonClick();
    }

    private void retry() {
        this.dismiss();
        fragmentManagerWeakReference.get().beginTransaction().remove(this);
        listener.onErrorRetryButtonClick();
    }

    //TODO: do a better think for this
    protected void exceptionParser(@NonNull DataWrapper dataWrapper) {
        if (dataWrapper.throwable != null) {
            try {
                CustomRuntimeException exception = (CustomRuntimeException) dataWrapper.throwable;
                mBinding.errorImage.setImageResource(R.drawable.ic_no_internet);
                mBinding.setErrorTitle(getResources().getString(exception.getLabel()));
            } catch (Exception e) {
                mBinding.errorImage.setImageResource(R.drawable.ic_no_internet);
                mBinding.setErrorTitle(getResources().getString(R.string.unknown_error));
            }
        } else if (dataWrapper.serverErrorCodes != null && dataWrapper.serverErrorCodes.size() > 0) {
            mBinding.errorImage.setImageResource(R.drawable.ic_no_internet);
            mBinding.setErrorTitle(MessageMap.getMessageString(dataWrapper.serverErrorCodes, getContext()));
        } else {
            mBinding.errorImage.setImageResource(R.drawable.ic_no_internet);
            mBinding.setErrorTitle(getResources().getString(R.string.unknown_error));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public interface OnErrorActionListener {

        void onErrorRetryButtonClick();

        void onErrorCancelButtonClick();
    }

}
