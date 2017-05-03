package com.lsinf1225.groupe_s.ezmeal;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by alexandredewit on 24/04/17.
 */

public class Tab1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);


        ImageButton camBt = (ImageButton) v.findViewById(R.id.imageButtonFor2);
        camBt.setOnClickListener(listener);


        return v;
        //return inflater.inflate(R.layout.tab1, container, false);
    }



    ImageButton.OnClickListener listener = new ImageButton.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(getActivity(), ClassicReceipt.class);
            startActivity(intent);
        }
    };

}
