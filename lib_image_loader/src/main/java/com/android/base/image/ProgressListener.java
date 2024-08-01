package com.android.base.image;

/**
 * @author Ztiany
 */
public interface ProgressListener {

    void onProgress(String url, ProgressInfo progressInfo);

    default void onError(long id, String url, Throwable throwable){}

}
