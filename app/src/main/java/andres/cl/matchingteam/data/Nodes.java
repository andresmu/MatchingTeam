package andres.cl.matchingteam.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Andrés on 06-07-2017.
 */

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference teams() {
        return root.child("teams");
    }

    public DatabaseReference teamPlayer(String teamKey) {
        return root.child("teamPlayers").child(teamKey);
    }
}
