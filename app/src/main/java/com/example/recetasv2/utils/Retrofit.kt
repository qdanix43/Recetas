package com.example.recetasv2.utils



object RetrofitClient {

    private const val BASE_URL = "https://tu.url.base.aqui/"

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

private fun Any.build(): Retrofit? {

}

private fun Any.addConverterFactory(create: Any): Any {
    TODO("Not yet implemented")
}

private fun Any.baseUrl(baseUrl: String): Any {
    TODO("Not yet implemented")
}

class Retrofit {
    companion object {
        fun Builder(): Any {
            TODO("Not yet implemented")
        }
    }

}
