package agi.erecreditsmanager

import agi.erecreditsmanager.ForLecture.ForLecture
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_service_terminate.*
import kotlinx.android.synthetic.main.eretm_toastborder.view.*
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream

class MainActivity : AppCompatActivity() {

    private var filename = ""

    private var progressingMajor = ""
    private var total: Total? = null
    private var forLectures = arrayListOf<ForLecture>()
    private var isLifeChecked = false

    private var sNum = ""
    private var pNum = ""
    private var dataStr: String? = null

    //todo: 리뉴얼 앱 다운 링크로 변경
    private val renewalUrl = "http://www.naver.com/"

    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_terminate)

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED)
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
        } catch (e: Exception) {
            e.printStackTrace()
            ERETMToast(this, "불러오기 실패", Toast.LENGTH_SHORT)
        }
    }

    fun backupClicked(v: View) {
        try {
            user = FirebaseAuth.getInstance().currentUser!!

            sNum = signupSNumEditText.text.toString()
            pNum = signupPNumEditText.text.toString()

            if (sNum.isEmpty() || pNum.isEmpty()) {
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
                try {
                    val database = Firebase.database.reference
                    database.child("Student").child(user.uid).run {
                        child("name").setValue(user.displayName)
                        child("pNum").setValue(pNum)
                        child("sNum").setValue(sNum)
                    }
                    database.child("credit").child("CreditData").child(user.uid).setValue(dataStr)
                    ERETMToast(this, "백업 성공", Toast.LENGTH_SHORT)
                } catch (e: Exception) {
                    e.printStackTrace()
                    ERETMToast(this, "백업 실패", Toast.LENGTH_SHORT)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                ERETMToast(this, "로그인 성공", Toast.LENGTH_SHORT)
            } else {
                ERETMToast(this, "로그인 실패", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun serialForLectures(forLectures: ArrayList<ForLecture>): JsonObject {
        val types = arrayListOf<String>()
        val names = arrayListOf<String>()
        val num = forLectures.size
        for (i in 0 until num) {
            types.add("\"" + forLectures[i].type + "\"")
            names.add("\"" + forLectures[i].name + "\"")
        }

        val jsonObject = JsonObject()
        jsonObject.addProperty("types", types.toString())
        jsonObject.addProperty("names", names.toString())
        jsonObject.addProperty("num", num)

        return jsonObject
    }

    fun toRenewalApp(v: View) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(renewalUrl)))
    }

    companion object {
        fun ERETMToast(context: Context, text: String, duration: Int) {
            Toast(context).run {
                setDuration(duration)
                view = ERETMToastLayout(context).apply {
                    toastTextView.text = text
                }
                show()
            }
        }

        const val RC_SIGN_IN = 1
    }
}
