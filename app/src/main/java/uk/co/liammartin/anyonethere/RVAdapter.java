package uk.co.liammartin.anyonethere;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ShoutViewHolder> {

    class ShoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Creating a CardView variable which will be each one of our users
        CardView individual_review_card_view;
        //Creating variables for holding the items that will be in the CardViews
        TextView username;
        RecyclerView recyclerView;

        /**
         * Taking the View we passed it when inflating user_card.xml and then finding
         * all of the individual views inside of it (the TextViews and ImageView at the moment)
         *
         * @param itemView the user_card.xml inflated view (containing TextViews and ImageView)
         */
        ShoutViewHolder(View itemView) {
            super(itemView);
            individual_review_card_view = (CardView) itemView.findViewById(R.id.review_card_view);
            username = (TextView) itemView.findViewById(R.id.username);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv);
            //individual_review_card_view.setOnClickListener(this);
        }

        //Implementing a click action at the ViewHolder level using getAdapterPosition(); to find
        //the position of the element we want to do something with. we have already set an
        //onclick listener on the item we want to listen for the click (the whole user cardView)
        //and can do anything now!
        @Override
        public void onClick(View v) {
            if (getAdapterPosition() >= 0) {

                //Getting our current context
                Context current_context = v.getContext();

                notifyDataSetChanged();
            }
        }


    }

    List<user> users;

    public void updateData(ArrayList<user> data) {
        users.clear();
        users.addAll(data);
        notifyDataSetChanged();
    }

    RVAdapter(List<user> users) {
        this.users = users;
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
     * Called to give the RecyclerView a ViewHolder to represent a user item
     *
     * @param viewGroup The parent ViewGroup that the user ViewHolder will added to after
     *                  it is bound to an adapter position (adapter will contain each
     *                  individual user view, the TextViews and ImageView)
     * @param i         The view type
     * @return The ViewHolder for a user so that the RecyclerView to display users
     */
    @Override
    public ShoutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //This part shows us that we are inflating our user_card xml file -------v
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_card, viewGroup, false);
        Log.d("DEBUGGING", "OnCreateViewHolder Called with position " + i);
        return new ShoutViewHolder(v);
    }

    /**
     * Remove an item from the data List
     *
     * @param position the position from which to remove the item
     */
    public void delete(int position) {
        users.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * This will be called by the RecyclerView to display the data, from the 'users' object at the
     * specified position (at i)
     *
     * @param userViewHolder The ViewHolder which should be updated to represent the contents of
     *                        the item at the given position in the data set
     * @param i               The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(final ShoutViewHolder userViewHolder, final int i) {
        userViewHolder.username.setText(users.get(i).username);
    }

    /**
     * Overriding this method to point to which object to get the size from (in this case
     * we are using the users object)
     *
     * @return The total number of items in the adapter
     */
    @Override
    public int getItemCount() {
        return users.size();
    }
}


