package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AlertMessage;
import demo.kolorob.kolorobdemoversion.utils.ToastMessageDisplay;

/*
this activity for showing kolorob details in app.Basically it has 4 buttons and different kind of actions ( check switch case)
* */

public class AboutUs extends AppCompatActivity implements View.OnClickListener {
    ImageButton fb, tw, wb, insta, phnus, emailus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        tw = (ImageButton) findViewById(R.id.btntwt);
        wb = (ImageButton) findViewById(R.id.btnweb);
        fb = (ImageButton) findViewById(R.id.btnfb);

        insta = (ImageButton) findViewById(R.id.btninsta);
        phnus = (ImageButton) findViewById(R.id.btnphn);
        emailus = (ImageButton) findViewById(R.id.btnemail);
        fb.setOnClickListener(this);
        tw.setOnClickListener(this);
        wb.setOnClickListener(this);
        insta.setOnClickListener(this);
        phnus.setOnClickListener(this);
        emailus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnfb:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com/scib.kolorob"));
                startActivity(intent);
                break;
            case R.id.btninsta:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("http://www.instagram.com/__kolorobbd"));
                startActivity(intent1);
                break;
            case R.id.btntwt:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                intent2.setData(Uri.parse("http://www.twitter.com/kolorob_BD"));
                startActivity(intent2);
                break;
            case R.id.btnweb:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_VIEW);
                intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                intent3.setData(Uri.parse("http://www.kolorob.info"));
                startActivity(intent3);
                break;
            case R.id.btnemail:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@kolorob.net"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    ToastMessageDisplay.setText(AboutUs.this,getString(R.string.cant_mail));
                    ToastMessageDisplay.showText(AboutUs.this);

                }
                break;
            case R.id.btnphn:

                AlertMessage.showMessage(AboutUs.this, getString(R.string.sorry),
                        getString(R.string.cant_call));
                break;
            default:break;
        }

    }
}
