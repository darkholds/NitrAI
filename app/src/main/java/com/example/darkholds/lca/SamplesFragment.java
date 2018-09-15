package com.example.darkholds.lca;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SamplesFragment extends Fragment {
    View.OnClickListener captureListener;
    View.OnClickListener analyzeListener;
    final int CAMERA_CAPTURE =1;
    View rootView;

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;

    int sampleCounter=0;

    public SamplesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        captureListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sampleCounter<10){
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (captureIntent.resolveActivity(getActivity().getPackageManager()) != null)
                        startActivityForResult(captureIntent,CAMERA_CAPTURE);
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(),"10 samples already captured. Proceed to analysis",Toast.LENGTH_LONG).show();
            }
        };

        analyzeListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sampleCounter<10)
                    Toast.makeText(getActivity().getApplicationContext(),"Not enough samples to analyze",Toast.LENGTH_LONG).show();
                else {
                    Bitmap[] sample = new Bitmap[Globals.numberOfSamples];

                    for(int i=0;i<Globals.numberOfSamples;i++){
                        switch(i){
                            case 0:
                                sample[0]=((BitmapDrawable) img1.getDrawable()).getBitmap();
                                break;
                            case 1:
                                sample[1]=((BitmapDrawable) img2.getDrawable()).getBitmap();
                                break;
                            case 2:
                                sample[2]=((BitmapDrawable) img3.getDrawable()).getBitmap();
                                break;
                            case 3:
                                sample[3]=((BitmapDrawable) img4.getDrawable()).getBitmap();
                                break;
                            case 4:
                                sample[4]=((BitmapDrawable) img5.getDrawable()).getBitmap();
                                break;
                            case 5:
                                sample[5]=((BitmapDrawable) img6.getDrawable()).getBitmap();
                                break;
                            case 6:
                                sample[6]=((BitmapDrawable) img7.getDrawable()).getBitmap();
                                break;
                            case 7:
                                sample[7]=((BitmapDrawable) img8.getDrawable()).getBitmap();
                                break;
                            case 8:
                                sample[8]=((BitmapDrawable) img9.getDrawable()).getBitmap();
                                break;
                            case 9:
                                sample[9]=((BitmapDrawable) img10.getDrawable()).getBitmap();
                                break;
                        }

                    }
                    ImagePro proc = new ImagePro(sample);
                    int x = proc.getAverageColor();
                    FLogic fl = new FLogic(x);
                    fl.processColor();
                    Globals.reading=fl.getReadingValue();

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction trans = manager.beginTransaction();
                    Fragment fragment = new ResultFragment();
                    trans.replace(R.id.fragment_container, fragment, null);
                    trans.commit();
                }

                //Toast.makeText(getActivity().getApplicationContext(), Color.red(x) + ", " + Color.green(x) + ", " + Color.blue(x),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity().getApplicationContext(),Globals.reading+"",Toast.LENGTH_LONG).show();
                //int x = proc.processSample(sample[0]);
                // Toast.makeText(getActivity().getApplicationContext(), Color.argb(0,44,86,47) + "",Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =inflater.inflate(R.layout.fragment_samples, container, false);
        ImageButton btnCapture = rootView.findViewById(R.id.btnCapture);
        ImageButton btnAnalyze = rootView.findViewById(R.id.btnAnalyze);

        img1 = rootView.findViewById(R.id.image1);
        img2 = rootView.findViewById(R.id.image2);
        img3 = rootView.findViewById(R.id.image3);
        img4 = rootView.findViewById(R.id.image4);
        img5 = rootView.findViewById(R.id.image5);
        img6 = rootView.findViewById(R.id.image6);
        img7 = rootView.findViewById(R.id.image7);
        img8 = rootView.findViewById(R.id.image8);
        img9 = rootView.findViewById(R.id.image9);
        img10 = rootView.findViewById(R.id.image10);

        btnAnalyze.setOnClickListener(analyzeListener);
        btnCapture.setOnClickListener(captureListener);
        return rootView;
    }

     @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAMERA_CAPTURE && resultCode== Activity.RESULT_OK){
            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");

            //Matrix matrix = new Matrix();
            //matrix.postRotate(90);
            //imgBitmap = Bitmap.createBitmap(imgBitmap, 0, 0, imgBitmap.getWidth(), imgBitmap.getHeight(), matrix, true);
            sampleCounter++;

            switch (sampleCounter){
                case 1:
                    img1.setImageBitmap(imgBitmap);
                    break;
                case 2:
                    img2.setImageBitmap(imgBitmap);
                    break;
                case 3:
                    img3.setImageBitmap(imgBitmap);
                    break;
                case 4:
                    img4.setImageBitmap(imgBitmap);
                    break;
                case 5:
                    img5.setImageBitmap(imgBitmap);
                    break;
                case 6:
                    img6.setImageBitmap(imgBitmap);
                    break;
                case 7:
                    img7.setImageBitmap(imgBitmap);
                    break;
                case 8:
                    img8.setImageBitmap(imgBitmap);
                    break;
                case 9:
                    img9.setImageBitmap(imgBitmap);
                    break;
                case 10:
                    img10.setImageBitmap(imgBitmap);
                    break;
            }
        }
    }
}
