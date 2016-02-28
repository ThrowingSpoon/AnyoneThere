package uk.co.liammartin.anyonethere;

/**
 * Each message will have its own instance of this class
 */

class message {
    int sender;
    int receiver;
    String message;
    /**
     * Constructor for making a new user instance
     *
     * @param sender    the ID of the sender of the message
     * @param receiver  the ID of the receiver of the message
     * @param message   the message sent
     */
    message(int sender,int receiver,String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }
}