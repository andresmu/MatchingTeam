package andres.cl.matchingteam.playersView;


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
import andres.cl.matchingteam.adapters.PlayersAdapters;
import andres.cl.matchingteam.models.Team;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment {


    private RecyclerView recyclerView;

    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Team team = (Team) getActivity().getIntent().getSerializableExtra("team");
        final String team_key = team.getKey();

        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        PlayersAdapters adapters = new PlayersAdapters(team_key);
        recyclerView.setAdapter(adapters);

    }
}
