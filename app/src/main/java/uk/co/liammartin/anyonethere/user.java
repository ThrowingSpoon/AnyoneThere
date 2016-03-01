package uk.co.liammartin.anyonethere;

/**
 * Each user will have its own instance of this class
 */

class user {
    String username;
    String user_id;

    /**
     * Constructor for making a new user instance
     *
     * @param username the username of the user in range
     */
    user(String username, String user_id) {
        this.username = username;
        this.user_id = user_id;
    }
}