package com.qiniu.csvc.base;

import android.support.annotation.NonNull;

public interface BaseDataSource {

    interface GetDataSourceCallback<T> {

        void onLoaded(@NonNull T data);

        void onDataNotAvailable();
    }

    interface SetDataSourceCallback<T> {

        void onUpdated(@NonNull T newData);

        void onError();

    }

    void refresh();

}
