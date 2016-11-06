package demo.kolorob.kolorobdemoversion.model;

import android.database.Cursor;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * Created by Mazharul.Islam1 on 31 August.
 */
public class CommentItem {
    private String mob_no;
    private String service_id;
    private String comment;
    private String rating;
    private String date;
    private String user_name;
    private String email;


    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CommentItem(String mob_no, String service_id, String comment, String rating, String date) {
        this.mob_no = mob_no;
        this.service_id = service_id;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.user_name=user_name;
        this.email=email;
    }


    public CommentItem(Cursor cur){


        comment = StringEscapeUtils.unescapeJava(cur.getString(2)).replace("%20"," " );
        date = cur.getString(4);
        mob_no = cur.getString(1);
        rating = cur.getString(3);
        service_id = cur.getString(0);
        user_name= StringEscapeUtils.unescapeJava(cur.getString(5)).replace("%20"," " );
        email=cur.getString(6);

    }




}