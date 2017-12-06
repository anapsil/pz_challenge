package net.anapsil.videoplayer.download;

/**
 * @author anapsil
 * @since 1.0.0
 */

public enum DownloadStatus {
    NOT_DOWNLOADED("not downloaded"),
    IN_QUEUE("in queue"),
    DOWNLOADING("downloading"),
    DOWNLOADED("downloaded");

    private String status;

    DownloadStatus(String status) {
        this.status = status;
    }

    public static DownloadStatus getValue(String status) {
        for (DownloadStatus s : DownloadStatus.values()) {
            if (s.status.equalsIgnoreCase(status)) {
                return s;
            }
        }

        return null;
    }

    public String getStatus() {
        return status;
    }
}
