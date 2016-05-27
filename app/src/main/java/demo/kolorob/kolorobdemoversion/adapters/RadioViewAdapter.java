package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by HP on 5/25/2016.
 */
public class RadioViewAdapter extends BaseAdapter {

    Context mCtx;
    String[] mImg;
    LayoutInflater layoutInflater;
    RadioGroup rgp;
    private RadioButton mSelectedRB;
    private int mSelectedPosition = -1;

    public RadioViewAdapter(Context context, String[] textViewValues) {
        this.mCtx = context;
this.mImg=textViewValues;
        rgp = new RadioGroup(context);
        layoutInflater=(LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {

        return mImg.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        View view = convertView;
        Holder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.filtercomponent, null);
            holder = new Holder();

            holder.radioButton = (RadioButton) view
                    .findViewById(R.id.grid_item_label);
            view.setTag(holder);

        } else {

            holder = (Holder) view.getTag();
        }

        holder.radioButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ((position != mSelectedPosition && mSelectedRB != null)) {
                    mSelectedRB.setChecked(false);
                }

                mSelectedPosition = position;
                mSelectedRB = (RadioButton) v;
            }
        });

        if (mSelectedPosition != position) {
            holder.radioButton.setChecked(false);
        } else {
            holder.radioButton.setChecked(true);
            if (mSelectedRB != null && holder.radioButton != mSelectedRB) {
                mSelectedRB = holder.radioButton;
            }
        }

        return view;
    }
    private class Holder {


        RadioButton radioButton;

    }
}

