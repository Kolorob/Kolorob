package demo.kolorob.kolorobdemoversion.model;

import android.database.Cursor;

import org.apache.commons.lang3.StringEscapeUtils;

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
    public String price;
    public String condition;
    public String contact;
    public String contact_person;
    public String product_name;
    public String address;



    public BazarItem(Cursor cur){
        id = cur.getInt(0);
        type = cur.getString(1);
        _customer = cur.getInt(2);
        description = StringEscapeUtils.unescapeJava(cur.getString(3)).replace("%20"," " );;
        date = cur.getString(4);
        expiry_date = cur.getString(5);
        phone = cur.getString(6);
        price = cur.getString(7);
        condition = cur.getString(8);
        contact = cur.getString(9);
        contact_person = StringEscapeUtils.unescapeJava(cur.getString(10)).replace("%20"," " );;
        product_name = StringEscapeUtils.unescapeJava(cur.getString(11)).replace("%20"," " );;
        address = StringEscapeUtils.unescapeJava(cur.getString(12)).replace("%20"," " );;
   }

    public BazarItem(){}

    @Override
    public String toString() {
        return id + type + _customer + description + date + expiry_date + phone + price + condition + contact + contact_person + product_name + address;
    }
}
