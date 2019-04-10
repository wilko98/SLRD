package com.example.slrd.ui.Upload;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.slrd.common.BasePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


@InjectViewState
public class UploadPresenter extends BasePresenter<UploadView> {
    protected final UploadView uploadView;

    public UploadPresenter(UploadView uploadView) {
        this.uploadView = uploadView;
    }

    public void upload() {
        try {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            uploadView.showFilesToSelect(intent,"select file");
//            uploadView.showSuccess();
        }catch (Exception e){
            uploadView.showError();
        }
    }

    public void uploadToFirebase(Uri fileUri, StorageReference mStorageRef) {
        final StorageReference riversRef = mStorageRef.child("files/newFile.jpg");

        riversRef.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                        uploadView.showToast("file uploaded!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        uploadView.showToast("ERROR");
                    }
                });

    }
}