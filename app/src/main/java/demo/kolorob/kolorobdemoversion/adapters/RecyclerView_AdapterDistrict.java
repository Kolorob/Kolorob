package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.model.District;



/**
 * Created by shamima.brishti on 2/11/18.
 */

public class RecyclerView_AdapterDistrict extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<District> districtList;
    private Context context;

    public RecyclerView_AdapterDistrict(Context context,
                                        ArrayList<District> arrayList) {
        this.context = context;
        this.districtList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != districtList ? districtList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final District district = districtList.get(position);

        holder.title.setText(district.getDistrict_bn());

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.data_loading_area, viewGroup, false);

        return new RecyclerViewHolder(mainGroup);

    }
}