package something.interesting.web_api.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import something.interesting.web_api.Model.LiguesDetailsModel;
import something.interesting.web_api.Model.LiguesModel;

public interface MyService {
    @GET("/api/ligues/{id}")
    public Call<LiguesDetailsModel> getligueByID(@Path("id") int ligueId);

    @GET("/api/ligues")
    public Call<List<LiguesModel>> findAll();

    @PUT("/api/ligues/{id}")
    public Call<LiguesDetailsModel> putdata(@Path("id") int ligueId, @Body LiguesDetailsModel data);

    @POST("/api/ligues")
    public Call<LiguesDetailsModel> postData(@Body LiguesDetailsModel data);

    @DELETE("/api/ligues/{id}")
    Call<Void> deleteData(@Path("id") int id);

}
