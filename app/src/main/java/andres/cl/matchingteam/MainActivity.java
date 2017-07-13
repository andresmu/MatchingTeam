package andres.cl.matchingteam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import andres.cl.matchingteam.data.CurrentUser;
import andres.cl.matchingteam.data.Nodes;
import andres.cl.matchingteam.models.Team;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText editText = (EditText) findViewById(R.id.nameEt);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = new CurrentUser().getCurrent();
                String nameTeam = editText.getText().toString();


                if (nameTeam.trim().length() > 0){
                    DatabaseReference teams = new Nodes().teams();

                    Team team = new Team();
                    team.setName(nameTeam);
                    String keyP = teams.push().getKey();
                    team.setUid(currentUser.getUid());
                    team.setUserName(currentUser.getDisplayName());
                    team.setUserMail(currentUser.getEmail());
                    team.setKey(keyP);

                    teams.child(keyP).setValue(team);
                    editText.setText("");
                    Toast.makeText(MainActivity.this, "Equipo Creado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Agrega tu equipo por favor", Toast.LENGTH_SHORT).show();
                }


                //FirebaseDatabase.getInstance().getReference().child("test1").setValue("value1");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

}
