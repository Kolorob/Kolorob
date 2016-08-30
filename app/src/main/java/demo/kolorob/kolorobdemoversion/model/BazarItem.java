package demo.kolorob.kolorobdemoversion.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by touhid on 10/30/15.
 * @author Tawseef
 */
public class BazarItem {
    public int id;
    public String type;
    public int _customer;
    public String description;
    public String date;
    public String expiry_date;
    public String phone;  //this has to come from shared preference
    public int price;
    public String condition;
    public String contact;
    public String contact_person;



    public BazarItem(Cursor cur){
        id = cur.getInt(0);
        type = cur.getString(1);
        _customer = cur.getInt(2);
        description = cur.getString(3);
        date = cur.getString(4);
        expiry_date = cur.getString(5);
        phone = cur.getString(6);
        price = cur.getInt(7);
        condition = cur.getString(8);
        contact = cur.getString(9);
        contact_person = cur.getString(10);
   }

    public BazarItem(){}

    @Override
    public String toString() {
        return id + type + _customer + description + date + expiry_date + phone + price + condition + contact + contact_person;
    }
}
