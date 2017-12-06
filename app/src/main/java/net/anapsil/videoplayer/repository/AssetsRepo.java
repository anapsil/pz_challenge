package net.anapsil.videoplayer.repository;

import net.anapsil.videoplayer.model.Assets;
import net.anapsil.videoplayer.remote.MediaServiceGenerator.MediaService;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class AssetsRepo {
    private MediaService mediaService;

    public AssetsRepo(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    public Single<Assets> getAssetsList() {
        return mediaService.getAssetsList();
    }

    public Observable<Response<ResponseBody>> download(String url) {
        return mediaService.downloadFile(url);
    }
}
