package com.example.slrd.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.slrd.R;
import com.example.slrd.common.SingleFragmentActivity;
import com.example.slrd.ui.Upload.UploadFragment;

public class NavigationActivity extends SingleFragmentActivity {

    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }



    @Override
    protected Fragment getFragment() {
        return UploadFragment.newInstance();
    }
}
