package andres.cl.matchingteam.login;

import andres.cl.matchingteam.data.CurrentUser;

/**
 * Created by Andrés on 04-07-2017.
 */

public class LoginValidation {

    private LoginCallback callback;

    public LoginValidation(LoginCallback callback) {
        this.callback = callback;
    }

    public void validate() {
        if (new CurrentUser().getCurrent() != null) {
            callback.logged();
        } else {
            callback.singIn();
        }
    }
}
