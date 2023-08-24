package com.example.baseandroid.data.repository

import com.example.baseandroid.data.model.User
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.*

interface IApiRepository {

    /*@GET("users/dashboard")
    fun getDashBoard(): Observable<BaseResponse<DashBoardResponse>>

    @POST("diet/users")
    fun register(@Body registerRequest: RegisterRequest): Observable<BaseResponse<RegisterResponse>>

    @GET("diet/food/fs/list")
    fun getListFood(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("keyword") keyword: String
    ): Observable<BaseResponse<DataSearchFood>>

    @DELETE("diet/recipe/ban/{id}")
    fun deleteRecipe(@Header("Authorization")  authHeader : String, @Path("id") itemId : Int) : Observable<BaseResponse<Any>>

    @PUT("diet/food")
    fun editFood(@Header("Authorization")  authHeader : String, @Body createFoodRequest: CreateFoodRequest) :
            Observable<BaseResponse<CreateFoodResponse>>*/

    @GET("posts")
    fun getListUser(): Observable<List<User>>

}