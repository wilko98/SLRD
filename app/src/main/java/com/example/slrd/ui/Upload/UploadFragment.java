package com.example.slrd.ui.Upload;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.slrd.R;
import com.example.slrd.common.BasePresenter;
import com.example.slrd.common.PresenterFragment;
import com.example.slrd.common.RefreshOwner;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends PresenterFragment implements UploadView {


    private static final int RESULT_LOAD_IMAGE = 1;

    public static Fragment newInstance() {
        return new UploadFragment();
    }
    private Button mBtnUpload;
    private Button mBtnPause;
    private Button mBtnStop;
    private StorageReference mStorageRef;
    @InjectPresenter
    UploadPresenter mUploadPresenter;

    @ProvidePresenter
    UploadPresenter providePresenter() {
        return new UploadPresenter(this);
    }
    @Override
    protected UploadPresenter getPresenter() {
        return mUploadPresenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("import");
        return inflater.inflate(R.layout.fr_import,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        mBtnUpload = v.findViewById(R.id.uploadButton);
        mBtnStop = v.findViewById(R.id.stopButton);
        mBtnPause = v.findViewById(R.id.pauseButton);
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUploadPresenter = new UploadPresenter(this);
        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUploadPresenter.upload();
            }
        });

    }

    @Override
    public void showSuccess() {
        Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFilesToSelect(Intent intent, String title) {
        startActivityForResult(Intent.createChooser(intent,title),RESULT_LOAD_IMAGE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {

            Uri fileUri = data.getData();
            mUploadPresenter.uploadToFirebase(fileUri, mStorageRef);
        }
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
    }
}
