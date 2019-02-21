package com.example.slrd.common;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Vladislav Falzan.
 */

public interface BaseView extends MvpView {

    void showRefresh();

    void hideRefresh();

    void showError();
}
