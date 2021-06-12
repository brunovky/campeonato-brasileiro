package com.brunooliveira.campeonatobrasileiro.repository

import com.brunooliveira.campeonatobrasileiro.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(request()
            .newBuilder()
            .addHeader("X-Auth-Token", BuildConfig.AUTH_TOKEN)
            .build())
    }

}