package com.example.darkholds.lca;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class MainFragment extends Fragment {
    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            Fragment fragment = new SettingsFragment();
            trans.replace(R.id.fragment_container,  fragment, null);
            trans.commit();
        }
    };

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton btnStart = rootView.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(btnListener);
        return rootView;
    }
}
