package andres.cl.matchingteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import andres.cl.matchingteam.R;
import andres.cl.matchingteam.data.CurrentUser;
import andres.cl.matchingteam.data.Nodes;
import andres.cl.matchingteam.models.Team;

/**
 * Created by Andrés on 06-07-2017.
 */

public class TeamsAdapters extends FirebaseRecyclerAdapter<Team, TeamsAdapters.TeamHolder>{

    private TeamsListener listener;

    public TeamsAdapters(TeamsListener listener) {
        super(Team.class, R.layout.list_item_team, TeamHolder.class, new Nodes().teams());
        this.listener=listener;
    }

    @Override
    protected void populateViewHolder(final TeamHolder viewHolder, Team model, int position) {
        TextView textView = (TextView) viewHolder.itemView;
        if (new CurrentUser().getCurrent().getUid().equals(model.getUid())){
            String uName = new CurrentUser().getCurrent().getDisplayName();
            textView.setText(model.getName() + " de: " + uName);
        }else {
            textView.setText(model.getName() + " de: " + model.getUserName());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team teamAux = getItem(viewHolder.getAdapterPosition());
                listener.clicked(teamAux);
            }
        });

    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();
        listener.ready();
    }

    public static class TeamHolder extends RecyclerView.ViewHolder{

        public TeamHolder(View itemView) {
            super(itemView);
        }
    }
}
