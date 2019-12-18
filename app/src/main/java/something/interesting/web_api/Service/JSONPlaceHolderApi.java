package something.interesting.web_api.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import something.interesting.web_api.Model.Post;

public interface JSONPlaceHolderApi {

    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);
}
