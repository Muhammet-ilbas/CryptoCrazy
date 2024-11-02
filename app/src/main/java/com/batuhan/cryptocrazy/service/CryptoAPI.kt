package com.batuhan.cryptocrazy.service

import com.batuhan.cryptocrazy.model.Crypto
import com.batuhan.cryptocrazy.model.CryptoList
import retrofit2.http.GET

interface CryptoAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList(): CryptoList

    @GET("crypto.json")
    suspend fun getCrypto(): Crypto

    /*
    @GET("prices")
    suspend fun getCryptoList(
        @Query("key") key: String,
    ): CryptoList

    @GET("currencies")
    suspend fun getCrypto(
        @Query("key") key: String,
        @Query("ids") id : String,
        @Query("attributes") attributes: String
    ): Crypto

     */
}