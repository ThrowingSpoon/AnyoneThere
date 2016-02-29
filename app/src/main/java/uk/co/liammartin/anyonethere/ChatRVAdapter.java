package uk.co.liammartin.anyonethere;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.ShoutViewHolder> {

    //List of type message to hold the data for each message
    List<message> messages;

    ChatRVAdapter(List<message> messages) {
        this.messages = messages;
    }

    public void updateData(ArrayList<message> data) {
        messages.clear();
        messages.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Called by the RecyclerView when it starts observing the adapter (we are using the
     * RVAdapter class)
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Called to give the RecyclerView a ViewHolder to represent a message item
     *
     * @param viewGroup The parent ViewGroup that the message ViewHolder will added to after
     *                  it is bound to an adapter position (adapter will contain each
     *                  individual message view, the TextViews and ImageView)
     * @param i         The view type
     * @return The ViewHolder for a message so that the RecyclerView to display messages
     */
    @Override
    public ShoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //This part shows us that we are inflating our msg_send_card xml file -------v
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.msg_send_card, viewGroup, false);
        Log.d("DEBUGGING", "OnCreateViewHolder Called with position " + i);
        return new ShoutViewHolder(v);
    }

    /**
     * Remove an item from the data List
     *
     * @param position the position from which to remove the item
     */
    public void delete(int position) {
        messages.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * This will be called by the RecyclerView to display the data, from the 'messages' object at the
     * specified position (at i)
     *
     * @param messageViewHolder The ViewHolder which should be updated to represent the contents of
     *                       the item at the given position in the data set
     * @param i              The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(final ShoutViewHolder messageViewHolder, final int i) {
        messageViewHolder.message.setText(messages.get(i).message);
    }

    /**
     * Overriding this method to point to which object to get the size from (in this case
     * we are using the messages object)
     *
     * @return The total number of items in the adapter
     */
    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ShoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Creating a CardView variable which will be each one of our messages
        CardView msg_send_card;

        //Creating variables for holding the items that will be in the CardViews
        TextView message;
        RecyclerView recyclerView;

        /**
         * Taking the View we passed it when inflating msg_send_card.xml and then finding
         * all of the individual views inside of it (the TextViews and ImageView at the moment)
         *
         * @param itemView the msg_send_card.xml inflated view (containing TextViews and ImageView)
         */
        ShoutViewHolder(View itemView) {
            super(itemView);
            msg_send_card = (CardView) itemView.findViewById(R.id.msg_send_card);
            message = (TextView) itemView.findViewById(R.id.message_send);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv);
            msg_send_card.setOnClickListener(this);
        }

        //Implementing a click action at the ViewHolder level using getAdapterPosition(); to find
        //the position of the element we want to do something with. we have already set an
        //onclick listener on the item we want to listen for the click (the whole message cardView)
        //and can do anything now!
        @Override
        public void onClick(View v) {
            if (getAdapterPosition() >= 0) {
                //Bundling the message and opening the chat screen using an intent
                Context current_context = v.getContext();
                String message = messages.get(getAdapterPosition()).message;
                Intent open_chat = new Intent(current_context, ChatScreen.class);
                open_chat.putExtra("message", message);
                current_context.startActivity(open_chat);
            }
        }
    }
}


