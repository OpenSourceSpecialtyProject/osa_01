<<<<<<< HEAD
package  com.example.osa_01.data;

import com.example.osa_01.data.model.LoggedInUser;
=======
package com.example.osa_01.data;
>>>>>>> 488c00360dd9aaaf8aaa774bd99c7398a661d616


import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new com.example.osa_01.data.Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new com.example.osa_01.data.Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}