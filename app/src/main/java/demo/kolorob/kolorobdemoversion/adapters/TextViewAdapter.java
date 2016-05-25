package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by HP on 5/25/2016.
 */
public class TextViewAdapter extends BaseAdapter {
    private Context context;
    private final String[] textViewValues;
    RadioButton textview, mSelectedRB;;
    private int mSelectedPosition = -1;

    public TextViewAdapter(Context context, String[] textViewValues) {
        this.context = context;
        this.textViewValues = textViewValues;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.filtercomponent, null);

            // set value into textview
            textview = (RadioButton) gridView
                    .findViewById(R.id.grid_item_label);
            textview.setText(textViewValues[position]);
        } else {
            gridView = (View) convertView;
        }
        textview.setOnClickListener(new View.OnClickListener() {

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
            textview.setChecked(false);
        } else {
            textview.setChecked(true);
            if (mSelectedRB != null && textview != mSelectedRB) {
                mSelectedRB = textview;
            }
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return textViewValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}