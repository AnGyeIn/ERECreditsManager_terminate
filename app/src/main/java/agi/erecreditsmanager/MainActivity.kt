package agi.erecreditsmanager

import agi.erecreditsmanager.ForLecture.ForLecture
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import kotlinx.android.synthetic.main.activity_service_terminate.*
import kotlinx.android.synthetic.main.eretm_toastborder.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class MainActivity : AppCompatActivity() {

    private var filename = ""

    private var progressingMajor = ""
    private var total : Total? = null
    private var forLectures = arrayListOf<ForLecture>()
    private var isLifeChecked = false

    private var name = ""
    private var sNum = ""
    private var password = ""
    private var pNum = ""
    private lateinit var apiService : ApiService
    private var token: String? = null
    private var dataStr: String? = null

    //todo: 리뉴얼 앱 다운 링크로 변경
    private val renewalUrl = "http://www.naver.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_terminate)

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        val sdcardFolder = Environment.getExternalStorageDirectory()
        val sdcardPath = sdcardFolder.absolutePath

        this.filename = sdcardPath + File.separator + "PersonalCredits"

        val file = File(filename)

        try {
            val inputStream = ObjectInputStream(FileInputStream(file))

            total = inputStream.readObject() as Total
            forLectures = inputStream.readObject() as ArrayList<ForLecture>
            progressingMajor = inputStream.readObject() as String
            isLifeChecked = inputStream.readObject() as Boolean

            inputStream.close()
            ERETMToast(this, "불러오기 성공", Toast.LENGTH_SHORT)
        } catch(e : Exception) {
            e.printStackTrace()
            ERETMToast(this, "불러오기 실패", Toast.LENGTH_SHORT)
        }

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.API_URL)
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun backupClicked(v: View) {
        name = signupNameEditText.text.toString()
        sNum = signupSNumEditText.text.toString()
        password = signupPasswordEditText.text.toString()
        pNum = signupPNumEditText.text.toString()

        if(name.isEmpty() || sNum.isEmpty() || password.isEmpty() || pNum.isEmpty()) {
            ERETMToast(this, "정보를 모두 입력했는지 확인해주세요.", Toast.LENGTH_LONG)
            return
        } else {
            val culTot = TotalSerializer(total!!.culture).totalize()
            val majTot = TotalSerializer(total!!.major).totalize()
            val norTot = TotalSerializer(total!!.normal).totalize()

            val data = JsonObject()
            data.add("culTot", culTot)
            data.add("majTot", majTot)
            data.add("norTot", norTot)
            data.add("forLectures", serialForLectures(forLectures))
            data.addProperty("progressingMajor", progressingMajor)
            data.addProperty("isLifeChecked", isLifeChecked)
            data.addProperty("studentNum", total!!.studentNum)

            dataStr = data.toString()
            backup()
        }
    }

    private fun serialForLectures(forLectures: ArrayList<ForLecture>): JsonObject {
        val types = arrayListOf<String>()
        val names = arrayListOf<String>()
        val num = forLectures.size
        for(i in 0 until num) {
            types.add("\""+forLectures[i].type+"\"")
            names.add("\""+forLectures[i].name+"\"")
        }

        val jsonObject = JsonObject()
        jsonObject.addProperty("types", types.toString())
        jsonObject.addProperty("names", names.toString())
        jsonObject.addProperty("num", num)

        return jsonObject
    }

    private fun getToken(afterSuccess: () -> Unit, afterFailure: () -> Unit) {
        val tokenCall = apiService.getToken(sNum, password)
        tokenCall.enqueue(object: Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if(response.isSuccessful) {
                    token = response.body()?.get("token")?.asString
                    if(token != null)
                        afterSuccess()
                    else
                        ERETMToast(applicationContext, "토큰 획득 실패", Toast.LENGTH_SHORT)
                } else
                    afterFailure()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                afterFailure()
            }
        })
    }

    private fun signUp(afterSuccess: () -> Unit) {
        val signupCall = apiService.signUp(name, sNum, password, pNum)
        signupCall.enqueue(object: Callback<JsonPrimitive> {
            override fun onResponse(call: Call<JsonPrimitive>, response: Response<JsonPrimitive>) {
                if(response.isSuccessful) {
                    try {
                        if(response.body()?.asInt == -1)
                            ERETMToast(applicationContext, "비밀번호가 틀렸습니다. 비밀번호를 분실했거나 본인이 가입하지 않았다면 학생회로 연락해주세요.", Toast.LENGTH_LONG)
                    } catch(e: Exception) {
                        afterSuccess()
                    }
                }
            }

            override fun onFailure(call: Call<JsonPrimitive>, t: Throwable) {
                ERETMToast(applicationContext, "회원가입 요청 실패", Toast.LENGTH_SHORT)
                throw t
            }
        })
    }

    private fun uploadData() {
        val uploadCall = apiService.uploadData("JWT $token", sNum, dataStr)
        uploadCall.enqueue(object: Callback<JsonPrimitive> {
            override fun onResponse(call: Call<JsonPrimitive>, response: Response<JsonPrimitive>) {
                if(response.isSuccessful)
                    ERETMToast(applicationContext, "백업 성공", Toast.LENGTH_SHORT)
                else {
                    ERETMToast(applicationContext, "백업 실패", Toast.LENGTH_SHORT)
                    Log.i("백업", response.body().toString())
                }
            }

            override fun onFailure(call: Call<JsonPrimitive>, t: Throwable) {
                ERETMToast(applicationContext, "백업 요청 실패", Toast.LENGTH_SHORT)
                throw t
            }
        })
    }

    private fun backup() {
        getToken({
            uploadData()
        }, {
            signUp {
                getToken({
                    uploadData()
                }, {
                    ERETMToast(applicationContext, "토큰 요청 실패", Toast.LENGTH_SHORT)
                })
            }
        })
    }

    fun toRenewalApp(v: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(renewalUrl)))
    }

    companion object {
        fun ERETMToast(context : Context, text : String, duration : Int) {
            Toast(context).run {
                setDuration(duration)
                view = ERETMToastLayout(context).apply {
                    toastTextView.text = text
                }
                show()
            }
        }
    }
}
