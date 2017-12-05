package net.anapsil.videoplayer.business;

import net.anapsil.videoplayer.VideoPlayerApplication;
import net.anapsil.videoplayer.model.Content;
import net.anapsil.videoplayer.repository.AssetsRepo;

import io.reactivex.Observable;

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
        return repo.getAssetsList()
                .doAfterSuccess(assets -> VideoPlayerApplication.setAssetsLocation(assets.getAssetsLocation()))
                .flatMapObservable(assets -> Observable.fromIterable(assets.getObjects()));
    }
}
