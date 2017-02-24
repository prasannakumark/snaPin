package com.snapin.snapins;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CroppingActivity extends AppCompatActivity {
    ImageView preCroppingImageView, postCroppingImageView;
    Intent CropIntent;
    File file;
    Uri uri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropping);
        preCroppingImageView =(ImageView) findViewById(R.id.first_image_view);
        postCroppingImageView =(ImageView)findViewById(R.id.second_image_view);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("picture");

        bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        try{
             file = new File(Environment.getExternalStorageDirectory(),
                    "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        }catch (Exception e){

        }

        OutputStream _out = null;
        try {
            _out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, _out);
            _out.close();
        }catch (Exception e){

        }

        uri = Uri.fromFile(file);

        preCroppingImageView.bringToFront();
        preCroppingImageView.setImageBitmap(bitmap);
        ImageCropFunction();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            if (data != null) {
                Bundle bundle = data.getExtras();
                bitmap = bundle.getParcelable("data");
                postCroppingImageView.bringToFront();
                preCroppingImageView.setImageBitmap(null);
                postCroppingImageView.setImageBitmap(bitmap);
            }
        }
    }

    public void CancelMethod(View view){
        preCroppingImageView.bringToFront();
        postCroppingImageView.setImageBitmap(null);
        ImageCropFunction();

    }

    public void SaveMethod(View view){
        Toast.makeText(getApplicationContext(),"Need to Save",Toast.LENGTH_SHORT).show();
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
           /* CropIntent.putExtra("outputX", 400);
            CropIntent.putExtra("outputY", 100);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 1);
            CropIntent.putExtra("scaleUpIfNeeded", true);*/
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {
        }
    }
}
