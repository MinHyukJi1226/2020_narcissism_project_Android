package org.techtown.narcissism_android.net;

import org.techtown.narcissism_android.data.CategoryResponse;
import org.techtown.narcissism_android.data.QuestionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {
    @GET("question/{id}")
    Call<List<QuestionResponse>> getAnswer(@Path("id") int id);

    @GET("category/{id}")
    Call<List<CategoryResponse>> getCategory(@Path("id") int id);
}
