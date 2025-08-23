package com.example.discountclub.data.remote

import com.example.discountclub.data.remote.model.PurchaseResponse
import com.example.discountclub.data.remote.model.Response
import com.example.discountclub.di.IoDispatcher
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PurchasesRemoteDataSource @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getPurchases(): List<PurchaseResponse> {
        return withContext(dispatcher) {
            val listType =
                TypeToken.getParameterized(List::class.java, PurchaseResponse::class.java).type
            val responseType = TypeToken.getParameterized(Response::class.java, listType).type
            val response: Response<List<PurchaseResponse>> =
                Gson().fromJson(json, responseType)
            response.data
        }
    }
}

private const val json = """
 {
   "data":[
      {
         "date":"2022-09-10T21:55:33Z",
         "name":[
            "123",
            "321"
         ]
      },
      {
         "date":"2022-09-10T21:50:33Z",
         "name":[
            "1234",
            "4321"
         ]
      },
      {
         "date":"2022-09-08T01:55:33Z",
         "name":[
            "12345",
            "54321"
         ]
      },
      {
         "date":"2022-09-07T21:55:33Z",
         "name":[
            "123456",
            "654321"
         ]
      },
      {
         "date":"2022-09-07T11:55:33Z",
         "name":[
            "1234567",
            "7654321"
         ]
      }
   ]
}
"""