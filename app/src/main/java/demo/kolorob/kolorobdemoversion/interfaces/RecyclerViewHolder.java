package demo.kolorob.kolorobdemoversion.interfaces;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by israt.jahan on 1/31/2017.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder  {
    // View holder for gridview recycler view as we used in listview
    public TextView title;
   public CardView mCardView;





    public RecyclerViewHolder(View view) {
        super(view);
        // Find all views ids

        this.title = (TextView) view
                .findViewById(R.id.title);
        this.mCardView = (CardView) view.findViewById(R.id.card_view);


    }



}