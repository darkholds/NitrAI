package com.example.darkholds.lca;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final RadioButton rbTrans = rootView.findViewById(R.id.rbTrans);
        final RadioButton rbDWS = rootView.findViewById(R.id.rbDWS);
        final RadioButton rbDry = rootView.findViewById(R.id.rbDrySeason);
        final RadioButton rbWet = rootView.findViewById(R.id.rbWetSeason);
        final EditText txtLandArea = rootView.findViewById(R.id.txtLandArea);
        ImageButton btnGather = rootView.findViewById(R.id.btnGather);
        btnGather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtLandArea.getText().toString().equals("")){
                    if(rbTrans.isChecked())
                        Globals.plantMethod=0; //transplanted
                    else if(rbDWS.isChecked())
                        Globals.plantMethod=1; //direct wet seeded

                    if(rbDry.isChecked())
                        Globals.season=0; //dry
                    else if(rbWet.isChecked())
                        Globals.season=1; //wet season

                    Globals.landArea = Double.parseDouble(txtLandArea.getText().toString());

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction trans = manager.beginTransaction();
                    Fragment fragment = new SamplesFragment();
                    trans.replace(R.id.fragment_container, fragment, null);
                    trans.commit();
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(),"Please fill-in land area", Toast.LENGTH_LONG ).show();
            }
        });
        return rootView;
    }
}
