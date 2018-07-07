package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.model.CityCorporation;

/**
 * Created by shamima.yasmin on 8/24/2017.
 */

public class RecyclerView_AdapterCityCorporation extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<CityCorporation> ccList;
    private Context context;

    public RecyclerView_AdapterCityCorporation(Context context,
                                               ArrayList<CityCorporation> arrayList) {
        this.context = context;
        this.ccList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != ccList ? ccList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final CityCorporation cc = ccList.get(position);

        holder.title.setText(cc.getCityCorporation_bn());

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.data_loading_area, viewGroup, false);

        return new RecyclerViewHolder(mainGroup);

    }
}