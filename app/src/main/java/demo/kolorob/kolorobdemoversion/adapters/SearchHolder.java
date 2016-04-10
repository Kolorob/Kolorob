package demo.kolorob.kolorobdemoversion.adapters;

import java.util.Comparator;

/**
 * Created by israt.jahan on 4/10/2016.
 */
public class SearchHolder {

    public String name;
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public SearchHolder(String name, String id, int catid) {
        this.name = name;
        this.id = id;
        this.catid = catid;
    }

    public int catid;

    public static Comparator<SearchHolder> NameCompare = new Comparator<SearchHolder>() {

        public int compare(SearchHolder s1, SearchHolder s2) {
            String name1 = s1.getName().toUpperCase();
            String name2 = s2.getName().toUpperCase();

            //ascending order
            return name1.compareTo(name2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }};
}
