package com.example.darkholds.lca;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultFragment extends Fragment {
    View.OnClickListener exitListener;
    View.OnClickListener startListener;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exitListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        };

        startListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.landArea=0;
                Globals.plantMethod=0;
                Globals.reading=0;
                Globals.season=0;

                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                Fragment fragment = new MainFragment();
                trans.replace(R.id.fragment_container, fragment, null);
                trans.commit();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_result, container, false);
        ImageButton btnExit = rootView.findViewById(R.id.btnExit);
        ImageButton btnStartOver = rootView.findViewById(R.id.btnStartOver);
        TextView txtResult = rootView.findViewById(R.id.txtResult);

        btnExit.setOnClickListener(exitListener);
        btnStartOver.setOnClickListener(startListener);

        double nFert =0;
        double urea =0;
        if(Globals.season==0){
            nFert = Globals.landArea * 30;
            urea = Globals.landArea * 1.5;
        }
        else if(Globals.season==1){
            nFert = Globals.landArea * 23;
            urea = Globals.landArea * 1;
        }


        StringBuffer sb = new StringBuffer();
        sb.append("LCC result: " + Globals.reading);
        sb.append("\nRecommendations: ");
        if(Globals.plantMethod==0 && Globals.reading<4)
            sb.append("\nApply " + nFert + " kg of N and " +  urea + " bags of urea");
        else if(Globals.plantMethod==1 && Globals.reading<3)
            sb.append("\nApply " + nFert + " kg of N and " +  urea + " bags of urea");
        else if(Globals.plantMethod==0 && Globals.reading==4)
            sb.append("\n" + "No need to apply Nitrogen fertilizer.");
        else if(Globals.plantMethod==1 && Globals.reading==3)
            sb.append("\n" + "No need to apply Nitrogen fertilizer.");
        else if(Globals.plantMethod==1 && Globals.reading>3)
            sb.append("\n" + "Too much Nitrogen fertilizer. Use \n" + nFert + " kg of N and " +  urea + " bags of urea next time");
        else if(Globals.plantMethod==0  && Globals.reading>4)
            sb.append("\n" + "Too much Nitrogen fertilizer. Use \n" + nFert + " kg of N and " + urea + " bags of urea next time");
        else
            sb.append("\nBad samples");

        txtResult.setText(sb.toString());
        return rootView;
    }
}
