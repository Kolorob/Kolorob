package demo.kolorob.kolorobdemoversion.model;

import android.database.Cursor;


/**
 * Created by Mazharul.Islam1 on 31 August.
 */
public class CommentItem {
    private String mob_no;
    private String service_id;
    private String comment;
    private String date;


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

    public CommentItem(String mob_no, String service_id, String comment, String date) {
        this.mob_no = mob_no;
        this.service_id = service_id;
        this.comment = comment;
        this.date = date;



    }


    public CommentItem(Cursor cur){
        mob_no = cur.getString(0);
        service_id = cur.getString(1);
        comment = cur.getString(2);
        date = cur.getString(3);

    }




}