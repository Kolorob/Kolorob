package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.model.Ward;

/**
 * Created by israt.jahan on 1/31/2017.
 */

public class RecyclerView_AdapterWard extends RecyclerView.Adapter<RecyclerViewHolder>  {

    private ArrayList<Ward> wardList;
    private Context context;

    public RecyclerView_AdapterWard(Context context,
                                    ArrayList<Ward> arrayList) {
        this.context = context;
        this.wardList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != wardList ? wardList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final Ward ward = wardList.get(position);

        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder


        // bitmap

        // setting title
        mainHolder.title.setText(ward.getWard_bn());
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