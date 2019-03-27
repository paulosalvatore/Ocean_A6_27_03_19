package br.com.paulosalvatore.ocean_a6_27_03_19

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(GitHubService::class.java)

        val language = "kotlin"
        val call = service.searchRepositories("language:$language")
        call.enqueue(object : Callback<GitHubRepositoriesResult> {
            override fun onFailure(call: Call<GitHubRepositoriesResult>?, t: Throwable?) {
                textView.text = "Erro ao carregar os repositórios."
            }

            override fun onResponse(call: Call<GitHubRepositoriesResult>?,
                                    response: Response<GitHubRepositoriesResult>) {
                if (response.isSuccessful) {
                    response.body()?.items?.let { repositories ->
                        textView.text = ""
                        repositories.forEach {
                            textView.append("${it.full_name}\n")
                        }
                    }
                }
            }
        })
    }
}
