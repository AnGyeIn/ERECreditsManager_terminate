package agi.erecreditsmanager;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //todo: 앞에 키워드들 지워도 되는지
    //    public static final String API_URL = "http://angyein.pythonanywhere.com/";
    String API_URL = "http://angyein.pythonanywhere.com/";

    @FormUrlEncoded
    @POST("api-token-auth/")
    Call<JsonObject> getToken(@Field("sNum") String sNum, @Field("password") String password);

    @FormUrlEncoded
    @POST("lecturebook/signup/")
    Call<JsonPrimitive> signUp(@Field("name") String name, @Field("sNum") String sNum, @Field("password") String password, @Field("pNum") String pNum);

    @FormUrlEncoded
    @POST("credit/save/")
    Call<JsonPrimitive> uploadData(@Header("Authorization") String token, @Field("sNum") String sNum, @Field("data") String data);
}
