package andres.cl.matchingteam;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import andres.cl.matchingteam.data.CurrentUser;
import andres.cl.matchingteam.login.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {


    public DrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView nick = (TextView) view.findViewById(R.id.nickTv);
        nick.setText(new CurrentUser().name());

        TextView logout = (TextView) view.findViewById(R.id.logoutTv);
        //FloatingActionButton takePhoto = (FloatingActionButton) view.findViewById(R.id.camerafab);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(getActivity(), LoginActivity.class));
                                getActivity().finish();
                            }
                        });
            }
        });

    }
}
