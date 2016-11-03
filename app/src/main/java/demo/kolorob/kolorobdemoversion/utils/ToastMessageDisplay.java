package demo.kolorob.kolorobdemoversion.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import demo.kolorob.kolorobdemoversion.R;

/**
 * Created by Mazharul.Islam1 on 9/5/2016.
 */
public class ToastMessageDisplay {
static String m;

    public static void setText(Context context,String message)
    {
      //  LayoutInflater inflater = context.getResources();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View toastView = inflater.inflate(R.layout.toast_view,null);
        Toast toast = new Toast(context);
        toast.setView(toastView);
        TextView toastMessage = (TextView) toastView.findViewById(R.id.toasts);

        m=message;
       // toastMessage.setText(message);
//        toastMessage.setTextColor(getResources().getColor(R.color.orange));
        toastMessage.setGravity(Gravity.CENTER);

       // toastMessage.setCompoundDrawablePadding(26);

    }
    public static  void showText(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastView = inflater.inflate(R.layout.toast_view,null);
        TextView toastMessage = (TextView) toastView.findViewById(R.id.toasts);
        Toast toast = new Toast(context);
        toastMessage.setText(m);
        toast.setView(toastView);
        if (SharedPreferencesHelper.isTabletDevice(context)){
            toastMessage.setPadding(18,15,15,15);
            toastMessage.setTextSize(25);}
        else toastMessage.setTextSize(15);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
