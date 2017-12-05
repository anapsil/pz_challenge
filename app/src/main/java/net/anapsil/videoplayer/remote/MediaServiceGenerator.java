package net.anapsil.videoplayer.remote;

import net.anapsil.videoplayer.model.Assets;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author anapsil
 * @since 1.0.0
 */

public final class MediaServiceGenerator {
    private static MediaServiceGenerator instance;
    private final String BASE_URL = "http://pbmedia.pepblast.com/pz_challenge/";
    private Retrofit retrofit;

    private MediaServiceGenerator() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static MediaServiceGenerator with() {
        if (instance == null) {
            instance = new MediaServiceGenerator();
        }
        return instance;
    }

    public MediaService service() {
        return retrofit.create(MediaService.class);
    }

    public interface MediaService {

        @GET("assets.json")
        public Single<Assets> getAssetsList();

        @GET
        public Call<ResponseBody> getAsset(@Url String url);
    }
}
