package demo.kolorob.kolorobdemoversion.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by touhid on 12/26/15.
 * @author touhid
 */
public class CategoryListAdapter  extends ArrayAdapter<CategoryItem> {

    private static double VIEW_WIDTH;
    private LayoutInflater inflater;

    public CategoryListAdapter(Context context, int resource) {
        super(context, resource);
        inflater = LayoutInflater.from(context);

        VIEW_WIDTH = AppUtils.getScreenWidth(context) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.cat_side_list_item, parent, false);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIconCatList);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvNameCatList);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();

        CategoryItem ci = getItem(position);

        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        holder.ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
        ViewGroup.LayoutParams lpIv = holder.ivIcon.getLayoutParams();
        lpIv.width = (int)Math.floor(VIEW_WIDTH * 0.80); // 80% of the view width

        holder.tvName.setText(ci.getCatName());
        holder.tvName.setTextSize((float)(VIEW_WIDTH * .10));

        return convertView;
    }

    public void setViewWidth(Context ctx, double dwPercentage){
        VIEW_WIDTH = AppUtils.getScreenWidth(ctx) * dwPercentage;
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        public ImageView ivIcon;
        public TextView tvName;
    }
}
