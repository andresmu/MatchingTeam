package andres.cl.matchingteam.adapters;

import andres.cl.matchingteam.models.Team;

/**
 * Created by Andrés on 06-07-2017.
 */

public interface TeamsListener {
    void clicked(Team team);
    void ready();

}
