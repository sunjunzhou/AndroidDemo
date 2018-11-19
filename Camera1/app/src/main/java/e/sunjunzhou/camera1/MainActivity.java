package e.sunjunzhou.camera1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.imageView);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1) {
          if(resultCode==RESULT_OK){
              String sdStatus= Environment.getExternalStorageState();
              if(!sdStatus.equals(Environment.MEDIA_MOUNTED)){
                  Log.v("TestFile","SD卡无效");
                  return;
              }
              Bundle bundle=data.getExtras();
              Bitmap bitmap=(Bitmap)bundle.get("data");
              FileOutputStream b=null;
              File file=new File("/sdcard/myImage/");
              file.mkdirs();
              String fileName="/sdcard/myImage/111.jpg";
              try{
                  b=new FileOutputStream(fileName);
                  bitmap.compress(Bitmap.CompressFormat.JPEG,100,b);
              }catch (FileNotFoundException e){
                  e.printStackTrace();
              }finally {
                  try {
                      b.flush();
                  }catch (IOException e){
                      e.printStackTrace();
                  }
          }
          image.setImageBitmap(bitmap);
        }
        }
    }
}
