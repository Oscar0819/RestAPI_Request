package com.example.restapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.restapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 10.0.2.2는 안드로이드에서 pc의 localhost를 가리킬 수 있는 주소이다 : (Emulator 테스트시)
        // 내부 ip주소(IPv4) : (Device 테스트시)
        val url = "http://10.0.2.2:3000"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var server = retrofit.create(APIInterface::class.java)

        binding.btnGet.setOnClickListener {
            server.getRequest("name").enqueue(object :Callback<ResponseDC>{
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }

            })
        }
        binding.btnPost.setOnClickListener {
            server.postRequest("id", "password").enqueue(object:Callback<ResponseDC>{
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
            })
        }
        binding.btnUpdate.setOnClickListener {
            server.putRequest("board", "내용").enqueue(object :Callback<ResponseDC>{
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
            })
        }
        binding.btnDelete.setOnClickListener {
            server.deleteRequest("board").enqueue(object:Callback<ResponseDC>{
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
            })
        }
    }
}