package andres.cl.matchingteam.teamList;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import andres.cl.matchingteam.R;
import andres.cl.matchingteam.adapters.TeamsAdapters;
import andres.cl.matchingteam.adapters.TeamsListener;
import andres.cl.matchingteam.models.Team;
import andres.cl.matchingteam.playersView.PlayersActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment implements TeamsListener{


    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        TeamsAdapters adapters = new TeamsAdapters(this);
        recyclerView.setAdapter(adapters);


    }

    @Override
    public void clicked(Team team) {
        /*if (new CurrentUser().uid().equals(team.getUid())){*/
        Intent intent = new Intent(getActivity(), PlayersActivity.class);
        intent.putExtra("team",team);
        startActivity(intent);
        /*} else {
            Toast.makeText(getActivity(), "Este no es tu equipo", Toast.LENGTH_SHORT).show();
        }*/

    }

    @Override
    public void ready() {
        progressDialog.dismiss();
    }
}
