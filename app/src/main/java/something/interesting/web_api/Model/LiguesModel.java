package something.interesting.web_api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LiguesModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
