package com.suka.dsc.difableapp.service;

import com.suka.dsc.difableapp.model.ResponseAudio;
import com.suka.dsc.difableapp.model.ResponseCategory;
import com.suka.dsc.difableapp.model.ResponseLogin;
import com.suka.dsc.difableapp.model.ReqUploadResponse;
import com.suka.dsc.difableapp.model.ResponseSignup;
import com.suka.dsc.difableapp.model.ResponseUserData;
import com.suka.dsc.difableapp.model.ResponseImageUpload;

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
    Call<ResponseCategory> getAllCategories();

    @GET("response/cat/detail")
    Call<ResponseAudio> getAllbooksByCat(@Query("cat") String category);

    @GET("response/dif/{uid}")
    Call<ResponseAudio> getMyBook(@Path("uid") String userDifId);

    @GET("user/{uid}")
    Call<ResponseUserData> getUserData(@Path("uid") String userId);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/createuser")
    Call<ResponseSignup> signup(@Field("email") String email,
                                @Field("password") String password,
                                @Field("phone") String phone_number,
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
    Call<ResponseImageUpload> uploadImage(@Part MultipartBody.Part image);
}
