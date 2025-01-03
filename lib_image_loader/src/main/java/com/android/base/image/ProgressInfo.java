package com.android.base.image;


import androidx.annotation.NonNull;

public class ProgressInfo {

    private long currentBytes;

    private long contentLength;

    private long intervalTime;

    /**
     * the total bytes that have been transferred since the last time this method was called.
     */
    private long eachBytes;

    private final long id;

    private boolean finish;

    public ProgressInfo(long id) {
        this.id = id;
    }

    public void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public void setEachBytes(long eachBytes) {
        this.eachBytes = eachBytes;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public long getEachBytes() {
        return eachBytes;
    }

    public long getId() {
        return id;
    }

    public boolean isFinished() {
        return finish;
    }

    /**
     * Get the download progress, from 0 to 1.
     */
    public float getProgress() {
        if (getCurrentBytes() <= 0 || getContentLength() <= 0) {
            return 0;
        }
        return ((1F * getCurrentBytes()) / getContentLength());
    }

    /**
     * Get the upload or download network speed, in bytes per second.
     */
    public long getSpeed() {
        if (getEachBytes() <= 0 || getIntervalTime() <= 0) {
            return 0;
        }
        return getEachBytes() * 1000 / getIntervalTime();
    }

    @NonNull
    @Override
    public String toString() {
        return "ProgressInfo{" +
                "id=" + id +
                ", currentBytes=" + currentBytes +
                ", contentLength=" + contentLength +
                ", eachBytes=" + eachBytes +
                ", intervalTime=" + intervalTime +
                ", finish=" + finish +
                '}';
    }

} 