package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.model.DataModel2;

/**
 * Created by israt.jahan on 1/31/2017.
 */

public class RecycleView_AdapterWardArea extends RecyclerView.Adapter<RecyclerViewHolder>  {

    private ArrayList<DataModel2> arrayList;
    private Context context;

    public RecycleView_AdapterWardArea(Context context,
                                       ArrayList<DataModel2> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final DataModel2 model = arrayList.get(position);

        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder


        // bitmap

        // setting title
        mainHolder.title.setText(model.getWardarea());
    /*    mainHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new Intent
                Toast.makeText(context,"title " +model.getAreanamebn(),Toast.LENGTH_LONG).show();
            }
        });
*/


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.data_loading_area, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;

    }


}