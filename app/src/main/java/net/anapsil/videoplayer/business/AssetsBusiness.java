package net.anapsil.videoplayer.business;

import android.util.Log;

import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.repository.AssetsRepo;

import java.io.File;
import java.io.IOException;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class AssetsBusiness {
    private AssetsRepo repo;

    public AssetsBusiness(AssetsRepo repo) {
        this.repo = repo;
    }

    public Observable<Content> getContents() {
        return getRemoteContent().map(content -> {
            for (Content downloadedContent : VideoPlayerApplication.getDownloadedContent()) {
                if (content.getName().equals(downloadedContent.getName())) {
                    return downloadedContent;
                }
            }

            return content;
        });
    }

    private Observable<Content> getRemoteContent() {
        return repo.getAssetsList()
                .doAfterSuccess(assets -> VideoPlayerApplication.setAssetsLocation(assets.getAssetsLocation()))
                .flatMapObservable(assets -> Observable.fromIterable(assets.getObjects()));
    }

    private Observable<Content> getLocalContent() {
        return Observable.fromIterable(VideoPlayerApplication.getDownloadedContent());
    }

    public Completable download(final String filename) {
        return repo.download(String.format("%s/%s", VideoPlayerApplication.getAssetsLocation(), filename))
                .flatMapCompletable(response -> saveToDisk(response, filename));
    }

    public void addDownloadedContent(Content content) {
        VideoPlayerApplication.getDownloadedContent().add(content);
    }

    private Completable saveToDisk(final Response<ResponseBody> response, final String filename) {
        return Completable.create(subscriber -> {
            try {
                File targetFile = new File(String.format("%s/%s", VideoPlayerApplication.getLocalAssetsLocation(), filename));
                Log.d(getClass().getName(), "Saving file: " + targetFile.getPath());

                BufferedSink bufferedSink = Okio.buffer(Okio.sink(targetFile));
                bufferedSink.writeAll(response.body().source());
                bufferedSink.close();

                subscriber.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                subscriber.onError(e);
            } catch (NullPointerException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        });
    }
}
