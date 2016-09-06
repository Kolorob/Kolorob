
package demo.kolorob.kolorobdemoversion.utils;

        import android.content.Context;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import demo.kolorob.kolorobdemoversion.R;


public class ToastMessageDisplay {


    public static void ShowToast(Context context,String message)
    {
        //  LayoutInflater inflater = context.getResources();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View toastView = inflater.inflate(R.layout.toast_view,null);
        Toast toast = new Toast(context);
        toast.setView(toastView);
        TextView toastMessage = (TextView) toastView.findViewById(R.id.toasts);
        toastMessage.setTextSize(25);
        toastMessage.setText(message);
//        toastMessage.setTextColor(getResources().getColor(R.color.orange));
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setCompoundDrawablePadding(26);
        toast.show();
    }
}

