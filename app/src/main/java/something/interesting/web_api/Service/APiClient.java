package something.interesting.web_api.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APiClient {
    private static APiClient mInstance;
    private static final String BASE_URL = "http://c3bfda98.ngrok.io/";
    private Retrofit mRetrofit;

    private APiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APiClient getInstance() {
        if (mInstance == null) {
            mInstance = new APiClient();
        }
        return mInstance;
    }

    public MyService getAPI() {
        return mRetrofit.create(MyService.class);
    }
}
