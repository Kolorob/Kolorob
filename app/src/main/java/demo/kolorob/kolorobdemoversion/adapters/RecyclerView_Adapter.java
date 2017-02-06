package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.interfaces.ChildItemClickListener;
import demo.kolorob.kolorobdemoversion.interfaces.RecyclerViewHolder;
import demo.kolorob.kolorobdemoversion.model.DataModel;

/**
 * Created by israt.jahan on 1/31/2017.
 */

public class RecyclerView_Adapter extends
        RecyclerView.Adapter<RecyclerViewHolder>  {// Recyclerview will extend to
    // recyclerview adapter
    private ArrayList<AreaHolder> arrayList;
    private ArrayList<DataModel> arrayList2;
    private Context context;
    int selected_position = 0;
    private ChildItemClickListener listener;
    public RecyclerView_Adapter(Context context,
                                ArrayList<AreaHolder> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        final AreaHolder model = arrayList.get(position);




        // bitmap
        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder
        // setting title

        mainHolder.title.setText(model.getWardname());
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
                R.layout.data_loading_item, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;

    }


}