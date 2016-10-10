package demo.kolorob.kolorobdemoversion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.kolorob.kolorobdemoversion.R;

public class EmergencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);


        String[] name_bangla = new String[] { "বাংলাদেশ ফায়ার সার্ভিস অ্যান্ড সিভিল ডিফেন্স ",
                "পুলিশ কন্ট্রোল রুম",
                "পল্লবী থানা",
                "র্যাব - ৪",
                "ডেসকো – ইলেক্ট্রিসিটি ",
                "দুর্যোগ ব্যবস্থাপনা ও ত্রাণ মন্ত্রণালয়","তিতাস গ্যাস ","ঢাকা ওয়াসা","ব্লাড ব্যাংক ","ঢাকা উত্তর সিটি কর্পোরেশন","নারী ও শিশু নির্যাতন প্রতিরোধ",""
        };

        String[] phone_no = new String[] { "029555555",
                "029555555",
                "027124000",
                "02-9015922",
                "029015922",
                "01777910499","02-9014291","16162","029139940","16364","10921",""
        };

        String[] address_bangla = new String[] { "ঢাকা",
                "মিরপুর ১০, ঢাকা",
                "ঢাকা",
                "ঢাকা",
                "পল্লবী, ঢাকা",
                "পল্লবী, ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা","ঢাকা",
        };

        String[] location = new String[] { "23.8069959 90.3665992",
                "not found",
                "23.8260387 90.366459",
                "23.78613 90.3570516",
                "23.8320088 90.4187671",
                "23.7296558 90.4090196","23.7505538 90.3934337","23.7531737 90.3925594","23.753432 90.4045906","23.7932742 90.4080312","23.7475818 90.399754"
        };

    }
}
