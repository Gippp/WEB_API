package something.interesting.web_api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiguesDetailsModel {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("teams_count")
    @Expose
    private int teams_count;

    @SerializedName("country")
    @Expose
    private String country;


    public int getLigue_id(){
        return id;
    }

    public void setLigue_id(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }

    public int getTeams_count(){
        return teams_count;
    }
    public void setTeams_count(int t){
        this.teams_count = t;
    }

    public String getCountry(){
        return country;
    }
    public void setCountry(String c){
        this.country = c;
    }
}
