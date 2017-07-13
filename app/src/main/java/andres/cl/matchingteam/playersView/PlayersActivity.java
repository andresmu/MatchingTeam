package andres.cl.matchingteam.playersView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import andres.cl.matchingteam.R;
import andres.cl.matchingteam.data.CurrentUser;
import andres.cl.matchingteam.data.Nodes;
import andres.cl.matchingteam.models.Team;
import andres.cl.matchingteam.models.TeamPlayer;

import static andres.cl.matchingteam.R.id.fab2;

public class PlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Team team = (Team) getIntent().getSerializableExtra("team");
        getSupportActionBar().setTitle(team.getName());
        final String team_key = team.getKey();
        final String uid = team.getUid();
        final String name = team.getUserName();

        FloatingActionButton fab = (FloatingActionButton) findViewById(fab2);
        final EditText editText = (EditText) findViewById(R.id.playersEt);

        if (!new CurrentUser().uid().equals(uid)){
            //fab.setVisibility(View.GONE);
            fab.setEnabled(false);
            //editText.setVisibility(View.GONE);
            editText.setEnabled(false);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(team.getName());
            alertDialog.setMessage("Este no es un equipo tuyo, contacta a " + team.getUserName() + " a su correo: " + team.getUserMail() + " para jugar");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = new CurrentUser().getCurrent();

                String name = editText.getText().toString();

                if (name.trim().length() > 0){
                    DatabaseReference players = new Nodes().teamPlayer(team_key);
                    String key = players.push().getKey();
                    TeamPlayer teamPlayer = new TeamPlayer();
                    teamPlayer.setName(name);
                    teamPlayer.setKey(key);
                    teamPlayer.setUid(currentUser.getUid());
                    teamPlayer.setTeam_key(team_key);

                    players.child(key).setValue(teamPlayer);
                    editText.setText("");
                    Toast.makeText(PlayersActivity.this, "Jugador Creado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PlayersActivity.this, "Agrega tu jugador por favor", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
