package com.qiniu.csvc.utils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

public class HttpRequestUtil {

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public static <T> boolean isResponseSuccessful(Response<T> response) {
        return response != null && response.code() == 200;
    }

    public static RequestBody wrapJsonRequestBody(String[] keys, Object[] values) {
        JSONObject object = new JSONObject();
        try {
            for (int i = 0; i < keys.length; i++) {
                object.put(keys[i], values[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MEDIA_TYPE_JSON, String.valueOf(object));
    }

    public static RequestBody wrapJsonRequestBody(Object parameterObject) {
        return RequestBody.create(MEDIA_TYPE_JSON, GsonConverter.toJson(parameterObject));
    }

}
