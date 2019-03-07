package com.suka.dsc.difableapp.service;

import com.suka.dsc.difableapp.model.AudioResponses;
import com.suka.dsc.difableapp.model.BookCategoriesModel;
import com.suka.dsc.difableapp.model.BookModel;
import com.suka.dsc.difableapp.model.CategoryResponses;
import com.suka.dsc.difableapp.model.LoginResponse;
import com.suka.dsc.difableapp.model.ReqUploadResponse;
import com.suka.dsc.difableapp.model.SignupResponse;
import com.suka.dsc.difableapp.model.UploadImage;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //@GET("allbook_categories")
    //Call<BookCategoriesModel> getAllbookCategories();

    //@GET("lookup_allbook")
    //Call<BookModel> getAllbook(@Query("id") String idCategory);

    @GET("response/cat")
    Call<CategoryResponses> getAllCategories();

    @GET("response/cat/detail")
    Call<AudioResponses> getAllbooksByCat(@Query("cat") String category);

    @GET("response/dif/{uid}")
    Call<AudioResponses> getMyBook(@Path("uid") String userDifId);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/createuser")
    Call<SignupResponse> signup(@Field("email") String email,
                                @Field("password") String password,
                                @Field("phone_number") String phone_number,
                                @Field("name") String name,
                                @Field("image_url") String image_url,
                                @Field("role") String role);

    @Multipart
    @POST("request/upload")
    Call<ReqUploadResponse> requestUpload(@Part MultipartBody.Part audio,
                                          @Part ("user_id") RequestBody user_id,
                                          @Part ("user_image_url") RequestBody user_image_url,
                                          @Part ("name") RequestBody name);

    @Multipart
    @POST("image/upload")
    Call<UploadImage> uploadImage(@Part MultipartBody.Part image);
}