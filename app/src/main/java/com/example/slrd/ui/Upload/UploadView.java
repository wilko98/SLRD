package com.example.slrd.ui.Upload;

import android.content.Intent;

import com.example.slrd.common.BaseView;

public interface UploadView extends BaseView {

    void showSuccess();
    void showFilesToSelect(Intent intent, String title);

    void showToast(String s);
}
