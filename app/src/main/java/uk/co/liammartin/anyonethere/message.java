package uk.co.liammartin.anyonethere;

/**
 * Each user will have its own instance of this class
 */

class message {
    String sender;
    String receiver;
    String message;
    /**
     * Constructor for making a new user instance
     *
     * @param sender    the sender of the message
     * @param receiver    the receiver of the message
     * @param message    the message sent
     */
    message(String sender,String receiver,String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;

    }
}