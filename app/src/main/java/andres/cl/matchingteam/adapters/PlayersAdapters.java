package andres.cl.matchingteam.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import andres.cl.matchingteam.R;
import andres.cl.matchingteam.data.Nodes;
import andres.cl.matchingteam.models.TeamPlayer;

/**
 * Created by Andrés on 11-07-2017.
 */

public class PlayersAdapters extends FirebaseRecyclerAdapter<TeamPlayer, PlayersAdapters.PlayerHolder>{


    public PlayersAdapters(String teamKey) {
        super(TeamPlayer.class, R.layout.list_item_player, PlayerHolder.class, new Nodes().teamPlayer(teamKey));
    }

    @Override
    protected void populateViewHolder(PlayerHolder viewHolder, TeamPlayer model, int position) {
        TextView textView = (TextView) viewHolder.itemView;
        textView.setText(model.getName());
    }

    public static class PlayerHolder extends RecyclerView.ViewHolder{

        public PlayerHolder(View itemView) {
            super(itemView);
        }
    }
}
