package agi.erecreditsmanager

import agi.erecreditsmanager.ConstManager.EREOnly
import agi.erecreditsmanager.ConstManager.EREnOther
import agi.erecreditsmanager.ConstManager.OthernERE
import agi.erecreditsmanager.ConstManager.OthernSubERE
import agi.erecreditsmanager.ForLecture.ForLecAdapter
import agi.erecreditsmanager.ForLecture.ForLecture
import agi.erecreditsmanager.FreeLecture.FreeLecture
import agi.erecreditsmanager.Lecture.Lecture
import agi.erecreditsmanager.LectureField.LectureField
import agi.erecreditsmanager.LectureGroup.LectureGroup
import agi.erecreditsmanager.LectureWorld.LectureWorld
import agi.erecreditsmanager.Type.Type
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.drm.DrmStore
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.eretm_toastborder.view.*
import kotlinx.android.synthetic.main.maildialog.view.*
import java.io.*

class MainActivity : AppCompatActivity() {

    val d = DataManager()

    private var filename = ""
    var forLectureType = ""
    private var progressingMajor = ""

    private var total : Total? = null

    private var adapter : MainAdapter? = null
    private var totalCredits = 0
    private var minTotalCredits = 0

    private var studentNum = 0

    val types = arrayOf("전공", "교양")
    private val forLecAdapter = ForLecAdapter(this)

    private var forLectures = arrayListOf<ForLecture>()

    private var isLifeChecked = false

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forLecSpinner.run {
            adapter = ArrayAdapter(this@MainActivity, R.layout.eretm_spinner_dropdown_item, R.id.dropdownItemTextView, this@MainActivity.types)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    this@MainActivity.forLectureType = types[position]
                }
            }
        }
        forLecRecyclerView.run {
            adapter = forLecAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if(permissionCheck != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

        val sdcardFolder = Environment.getExternalStorageDirectory()
        val sdcardPath = sdcardFolder.absolutePath

        this.filename = sdcardPath + File.separator + "PersonalCredits"

        val file = File(filename)

        total = Total(-1)
        progressingMajor = EREOnly
        isLifeChecked = false

        try {
            val inputStream = ObjectInputStream(FileInputStream(file))

            total = inputStream.readObject() as Total
            forLectures = inputStream.readObject() as ArrayList<ForLecture>
            progressingMajor = inputStream.readObject() as String
            isLifeChecked = inputStream.readObject() as Boolean

            inputStream.close()
            ERETMToast(this, "불러오기 성공", Toast.LENGTH_LONG)
        } catch(e : Exception) {
            e.printStackTrace()
            ERETMToast(this, "불러오기 실패", Toast.LENGTH_LONG)
        }

        if(total!!.studentNum == -1) {
            studentNumLayout.visibility = View.VISIBLE
            totalLayout.visibility = View.INVISIBLE
        } else {
            studentNumLayout.visibility = View.INVISIBLE
            changeStudentNumButton.text = "${total!!.studentNum} 학번"
            multiMajorButton.text = progressingMajor
            setting()

            if(total!!.studentNum >= 16) {
                lifeRespectCheckBox.run {
                    visibility = View.VISIBLE
                    isChecked = isLifeChecked
                }
            } else {
                lifeRespectCheckBox.visibility = View.INVISIBLE
            }
        }

        forLecRecyclerView.adapter = forLecAdapter.apply {
            this.forLectures = this@MainActivity.forLectures
        }
    }

    private fun initialization(studentNum : Int) {
        if(studentNum <= 13) {  //13학번 이전
            d.run {
                culture!!.run {
                    minCredits = 47
                    addUnderManager(culture_basic.apply {
                        minCredits = 38
                        addUnderManager(thiExp.apply {
                            addUnderManager(korean)
                        })
                        addUnderManager(foreign.apply {
                            name = "대학영어 또는 고급영어"
                            minCredits = 2
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(math1)
                            addUnderManager(math2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            minCredits = 16
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1.apply {
                                name = "화학1(화학의 기본1)"
                            })
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2.apply {
                                name = "화학2(화학의 기본2)"
                            })
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(biology1)
                            addUnderManager(biology)
                            addUnderManager(biology2)
                            addUnderManager(bioExp2)
                            addUnderManager(biology)
                            addUnderManager(bioExp)
                            addUnderManager(statistics)
                            addUnderManager(staExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(keyCulture.apply {
                        addUnderManager(litArt.apply {
                            addUnderManager(litArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(socIde.apply {
                            addUnderManager(socIdeFree)
                        })
                    })
                    addUnderManager(culture_engineering.apply {
                        addUnderManager(society.apply {
                            addUnderManager(socFree)
                        })
                        addUnderManager(creativity.apply {
                            addUnderManager(creFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        minCredits = 27
                        addUnderManager(eneResFut)
                        addUnderManager(eneResDyn)
                        addUnderManager(advResGeo)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(earPhyEng)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(resEngPra)
                        addUnderManager(resProEng)
                        addUnderManager(eneResFigAna)
                    })
                    addUnderManager(major_optNec.apply {
                        addUnderManager(driEng)
                        addUnderManager(newRenEne)
                        addUnderManager(advEarChe)
                        addUnderManager(eneEcoEng)
                    })
                    addUnderManager(major_optional.apply {
                        minCredits = 23
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.run {
                    minCredits = 21
                    addUnderManager(norFree)
                }
            }
        } else if(studentNum <= 15) {   //14, 15학번
            d.run {
                culture!!.run {
                    addUnderManager(culture_basic.apply {
                        addUnderManager(thiExp.apply {
                            addUnderManager(sciEngWri)
                        })
                        addUnderManager(foreign.apply {
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(math1)
                            addUnderManager(math2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1)
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2)
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(culture_world.apply {
                        addUnderManager(lenLit.apply {
                            addUnderManager(lenLitFree)
                        })
                        addUnderManager(culArt.apply {
                            addUnderManager(culArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(polEco.apply {
                            addUnderManager(polEcoFree)
                        })
                        addUnderManager(humSoc.apply {
                            addUnderManager(humSocFree)
                        })
                    })
                    addUnderManager(culture_engineering.apply {
                        addUnderManager(society.apply {
                            addUnderManager(socFree)
                        })
                        addUnderManager(creativity.apply {
                            addUnderManager(creFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        minCredits = 27
                        addUnderManager(eneResFut)
                        addUnderManager(eneResDyn)
                        addUnderManager(advResGeo)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(earPhyEng)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(resEngPra)
                        addUnderManager(resProEng)
                        addUnderManager(eneResFigAna)
                    })
                    addUnderManager(major_optNec.apply {
                        addUnderManager(driEng)
                        addUnderManager(newRenEne)
                        addUnderManager(advEarChe)
                        addUnderManager(eneEcoEng)
                    })
                    addUnderManager(major_optional.apply {
                        minCredits = 23
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.addUnderManager(norFree)
            }
        } else if(studentNum <= 17) {   //16, 17학번
            d.run {
                culture!!.run {
                    addUnderManager(culture_basic.apply {
                        addUnderManager(thiExp.apply {
                            addUnderManager(sciEngWri)
                        })
                        addUnderManager(foreign.apply {
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(math1)
                            addUnderManager(math2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1)
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2)
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(culture_world.apply {
                        addUnderManager(lenLit.apply {
                            addUnderManager(lenLitFree)
                        })
                        addUnderManager(culArt.apply {
                            addUnderManager(culArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(polEco.apply {
                            addUnderManager(polEcoFree)
                        })
                        addUnderManager(humSoc.apply {
                            addUnderManager(humSocFree)
                        })
                    })
                    addUnderManager(culture_engineering.apply {
                        addUnderManager(society.apply {
                            addUnderManager(socFree)
                        })
                        addUnderManager(creativity.apply {
                            addUnderManager(creFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        addUnderManager(eneResDyn)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(earPhyEng)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(resEngPra)
                        addUnderManager(resProEng)
                    })
                    addUnderManager(major_optional.apply {
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.addUnderManager(norFree)
            }
        } else if(studentNum == 18) {   //18학번
            d.run {
                culture!!.run {
                    addUnderManager(culture_basic.apply {
                        addUnderManager(thiExp.apply {
                            addUnderManager(sciEngWri)
                        })
                        addUnderManager(foreign.apply {
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(math1)
                            addUnderManager(math2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1)
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2)
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(culture_world.apply {
                        addUnderManager(lenLit.apply {
                            addUnderManager(lenLitFree)
                        })
                        addUnderManager(culArt.apply {
                            addUnderManager(culArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(polEco.apply {
                            addUnderManager(polEcoFree)
                        })
                        addUnderManager(humSoc.apply {
                            addUnderManager(humSocFree)
                        })
                    })
                    addUnderManager(culture_engineering.apply {
                        addUnderManager(society.apply {
                            addUnderManager(socFree)
                        })
                        addUnderManager(creativity.apply {
                            addUnderManager(creFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        addUnderManager(eneResDyn)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(earPhyEng)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(resProEng)
                        addUnderManager(resEngDes)
                    })
                    addUnderManager(major_optional.apply {
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.addUnderManager(norFree)
            }
        } else if(studentNum == 19) {   //19학번
            d.run {
                culture!!.run {
                    minCredits = 41
                    addUnderManager(culture_basic.apply {
                        minCredits = 35
                        addUnderManager(thiExp.apply {
                            minCredits = 4
                            addUnderManager(colWri1)
                            addUnderManager(colWri2)
                        })
                        addUnderManager(foreign.apply {
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(mathPra1)
                            addUnderManager(mathPra2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1)
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2)
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(culture_world.apply {
                        addUnderManager(lenLit.apply {
                            addUnderManager(lenLitFree)
                        })
                        addUnderManager(culArt.apply {
                            addUnderManager(culArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(polEco.apply {
                            addUnderManager(polEcoFree)
                        })
                        addUnderManager(humSoc.apply {
                            addUnderManager(humSocFree)
                        })
                    })
                    addUnderManager(culture_engineering.apply {
                        addUnderManager(society.apply {
                            addUnderManager(socFree)
                        })
                        addUnderManager(creativity.apply {
                            addUnderManager(creFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        minCredits = 21
                        addUnderManager(eneUnd)
                        addUnderManager(enePra)
                        addUnderManager(eneResDyn)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(earPhyEng)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(resProEng)
                        addUnderManager(resEngDes)
                    })
                    addUnderManager(major_optional.apply {
                        minCredits = 38
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.run {
                    minCredits = 27
                    addUnderManager(norFree)
                }
            }
        } else if(studentNum >= 20) {   //20학번 이후
            d.run {
                culture!!.run {
                    minCredits = 47
                    addUnderManager(culture_basic.apply {
                        minCredits = 35
                        addUnderManager(thiExp.apply {
                            minCredits = 4
                            addUnderManager(colWri1)
                            addUnderManager(colWri2)
                        })
                        addUnderManager(foreign.apply {
                            addUnderManager(foreignFree)
                        })
                        addUnderManager(numAnaInf.apply {
                            addUnderManager(mathPra1)
                            addUnderManager(mathPra2)
                            addUnderManager(engMat1)
                            addUnderManager(engMat2)
                        })
                        addUnderManager(sciThiExp.apply {
                            addUnderManager(physics1)
                            addUnderManager(phyExp1)
                            addUnderManager(physics2)
                            addUnderManager(phyExp2)
                            addUnderManager(physics)
                            addUnderManager(phyExp)
                            addUnderManager(chemistry1)
                            addUnderManager(cheExp1)
                            addUnderManager(chemistry2)
                            addUnderManager(cheExp2)
                            addUnderManager(chemistry)
                            addUnderManager(cheExp)
                            addUnderManager(earSysSci)
                            addUnderManager(earSysSciExp)
                        })
                        addUnderManager(comInfApp.apply {
                            addUnderManager(computer)
                        })
                    })
                    addUnderManager(culture_world_sin20.apply {
                        addUnderManager(lenLit.apply {
                            addUnderManager(lenLitFree)
                        })
                        addUnderManager(culArt.apply {
                            addUnderManager(culArtFree)
                        })
                        addUnderManager(hisPhi.apply {
                            addUnderManager(hisPhiFree)
                        })
                        addUnderManager(polEco.apply {
                            addUnderManager(polEcoFree)
                        })
                        addUnderManager(humSoc.apply {
                            addUnderManager(humSocFree)
                        })
                    })
                }
                major.run {
                    addUnderManager(major_necessary.apply {
                        minCredits = 30
                        addUnderManager(eneUnd)
                        addUnderManager(enePra)
                        addUnderManager(eneMat)
                        addUnderManager(eneThe)
                        addUnderManager(eneEcoTecAdm)
                        addUnderManager(eneFlu)
                        addUnderManager(eneEar)
                        addUnderManager(stoDynExp)
                        addUnderManager(oilGasEngExp)
                        addUnderManager(elaExp)
                        addUnderManager(resProEng)
                        addUnderManager(resEngDes)
                    })
                    addUnderManager(major_optional.apply {
                        minCredits = 29
                        addUnderManager(optFree)
                    })
                    addUnderManager(major_other.apply {
                        addUnderManager(othFree)
                    })
                }
                normal!!.run {
                    minCredits = 21
                    addUnderManager(norFree)
                }
            }
        }
    }

    private fun apply() {
        when(progressingMajor) {
            EREOnly -> {
                total!!.run {
                    culture.sumCredits()
                    major.sumCredits()
                    normal.sumCredits()
                }
                totalCredits = total!!.run {
                    culture.credits + major.credits + normal.credits
                }
            }
            EREnOther -> {
                total!!.run {
                    culture.sumCredits()
                    major.sumCredits()
                    normal.sumCredits()
                }
                totalCredits = total!!.run {
                    culture.credits + major.credits + normal.credits
                }
            }
            OthernERE -> {
                total!!.major.sumCredits()
                totalCredits = total!!.major.credits
            }
            OthernSubERE -> {
                total!!.major.sumCredits()
                totalCredits = total!!.major.credits
            }
        }
        totalCreditsTextView.text = "전체 학점 : ${totalCredits} / ${minTotalCredits}"
    }
    fun applyClicked(view : View) {
        apply()
    }

    private fun save() {
        val file = File(filename)

        try {
            val outputStream = ObjectOutputStream(FileOutputStream(file))
            outputStream.run {
                writeObject(total)
                writeObject(forLectures)
                writeObject(progressingMajor)
                writeObject(lifeRespectCheckBox.isChecked)

                flush()
                close()
            }
            ERETMToast(this, "저장되었습니다.", Toast.LENGTH_LONG)
        } catch(e : Exception) {
            e.printStackTrace()
            ERETMToast(this, "저장 실패", Toast.LENGTH_LONG)
        }
    }
    fun saveClicked(view : View) {
        save()
    }

    fun closeClicked(view : View) {
        save()
        finish()
    }

    override fun onStop() {
        save()
        super.onStop()
    }

    private fun studentNumSave() {
        try {
            studentNum = Integer.parseInt(studentNumEditText.text.toString())
            studentNumLayout.visibility = View.INVISIBLE
            totalLayout.visibility = View.VISIBLE
            changeMajorProcess(progressingMajor)
            total!!.studentNum = this.studentNum
            changeStudentNumButton.text = "${studentNum}학번"

            if(studentNum >= 16) {
                lifeRespectCheckBox.visibility = View.VISIBLE
            } else {
                lifeRespectCheckBox.visibility = View.INVISIBLE
            }
        } catch(e : Exception) {
            e.printStackTrace()
            ERETMToast(this, "학번은 숫자 2자리로 입력해주세요.", Toast.LENGTH_LONG)
        }
    }
    fun studentNumSaveClicked(view : View) {
        studentNumSave()
    }

    private fun setting() {
        adapter = MainAdapter(this)
        when(this.progressingMajor) {
            EREOnly -> {
                adapter!!.run {
                    setCreditManager(total!!.culture)
                    setCreditManager(total!!.major)
                    setCreditManager(total!!.normal)
                }
                minTotalCredits = 130
            }
            EREnOther -> {
                adapter!!.run {
                    setCreditManager(total!!.culture)
                    setCreditManager(total!!.major)
                    setCreditManager(total!!.normal)
                }
                minTotalCredits = 130
            }
            OthernERE -> {
                adapter!!.setCreditManager(total!!.major)
                minTotalCredits = 39
            }
            OthernSubERE -> {
                adapter!!.setCreditManager(total!!.major)
                minTotalCredits = 21
            }
        }
        apply()
        listView.adapter = adapter
    }

    private fun changeStudentNum() {
        totalLayout.visibility = View.INVISIBLE
        studentNumLayout.visibility = View.VISIBLE
        clearAll()
    }
    fun changeStudentNumClicked(view : View) {
        AlertDialog.Builder(this).run {
            setTitle("학번 수정 안내")
            setMessage("학번 설정을 수정할 경우 과목들을 체크 및 추가해 놓은 데이터가 사라집니다. 그래도 괜찮으시면 [확인] 버튼을 눌러주세요.")
            setPositiveButton("확인") { _, _ ->
                changeStudentNum()
            }
            setNegativeButton("취소") { _, _ ->

            }
            create().show()
        }
    }

    private fun clearAll() {
        d.run {
            //Type
            culture = Type("교양", 40)
            major = Type("전공", 62)
            normal = Type("그 외", 28)

            //LectureField
            culture_basic = LectureField("학문의 기초", 34)
            keyCulture = LectureField("핵심교양", 9)
            culture_world = LectureField("학문의 세계(2개 영역 이상)", 6)
            culture_world_sin20 = LectureField("학문의 세계(3개 영역 이상)", 12)  //20학번 이후
            culture_engineering = LectureField("공대 사회/창의성", 6)
            major_necessary = LectureField("전공필수", 19)
            major_optNec = LectureField("전공선택필수", 9)
            major_optional = LectureField("전공선택", 40)
            major_optOrNec = LectureField("전공선택, 전공선택필수", 3) // 15학번 이전 복수/부전공을 위한 항목
            major_other = LectureField("공대 타학과개론", 3)

            //LectureGroup
            thiExp = LectureGroup("사고와 표현", 3)
            foreign = LectureGroup("외국어 2개 교과목\n    (TEPS 900점 이하 영어 1과목 필수)", 4)
            numAnaInf = LectureGroup("수량적 분석과 추론", 12)
            sciThiExp = LectureGroup("과학적 사고와 실험", 12)
            comInfApp = LectureGroup("컴퓨터와 정보 활용", 3)
            society = LectureGroup("사회성 교과목군 or 인간과 사회 영역", 3)
            creativity = LectureGroup("창의성 교과목군 or 문화와 예술 영역", 3)

            //LectureWorld
            litArt = LectureWorld("문학과 예술")
            socIde = LectureWorld("사회와 이념")
            lenLit = LectureWorld("언어와 문학")
            culArt = LectureWorld("문화와 예술")
            hisPhi = LectureWorld("역사와 철학")
            polEco = LectureWorld("정치와 경제")
            humSoc = LectureWorld("인간과 사회")

            //Lecture
            sciEngWri = Lecture("과학과 기술 글쓰기", 3)
            korean = Lecture("대학국어", 3)
            colWri1 = Lecture("대학 글쓰기 1", 2)
            colWri2 = Lecture("대학 글쓰기 2: 과학기술 글쓰기", 2)
            math1 = Lecture("(고급)수학 및 연습1", 3)
            math2 = Lecture("(고급)수학 및 연습2", 3)
            mathPra1 = Lecture("수학 1과 수학연습 1", 3)
            mathPra2 = Lecture("수학 2와 수학연습 2", 3)
            engMat1 = Lecture("공학수학1", 3)
            engMat2 = Lecture("공학수학2", 3)
            physics1 = Lecture("(고급)물리학1(물리의 기본1)", 3)
            phyExp1 = Lecture("물리학실험1", 1)
            physics2 = Lecture("(고급)물리학2(물리의 기본2)", 3)
            phyExp2 = Lecture("물리학실험2", 1)
            physics = Lecture("물리학", 3)
            phyExp = Lecture("물리학실험", 1)
            chemistry1 = Lecture("화학1", 3)
            cheExp1 = Lecture("화학실험1", 1)
            chemistry2 = Lecture("화학2", 3)
            cheExp2 = Lecture("화학실험2", 1)
            chemistry = Lecture("화학", 3)
            cheExp = Lecture("화학실험", 1)
            biology1 = Lecture("생물학1", 3)
            bioExp1 = Lecture("생물학실험1", 1)
            biology2 = Lecture("생물학2", 3)
            bioExp2 = Lecture("생물학실험2", 1)
            biology = Lecture("생물학", 3)
            bioExp = Lecture("생물학실험", 1)
            statistics = Lecture("통계학", 3)
            staExp = Lecture("통계학실험", 1)
            earSysSci = Lecture("지구시스템과학", 3)
            earSysSciExp = Lecture("지구시스템과학실험", 1)
            computer = Lecture("컴퓨터의 개념 및 실습", 3)
            eneResFut = Lecture("에너지자원과미래", 2)
            eneUnd = Lecture("에너지자원공학의이해", 1)
            enePra = Lecture("에너지자원공학실습", 1)
            advResGeo = Lecture("응용자원지질", 3)
            eneResFigAna = Lecture("에너지자원수치해석", 3)
            driEng = Lecture("시추공학", 3)
            newRenEne = Lecture("신재생에너지", 3)
            advEarChe = Lecture("응용지구화학", 3)
            eneEcoEng = Lecture("에너지환경공학", 3)
            eneResDyn = Lecture("에너지자원역학", 3)
            eneMat = Lecture("에너지자원재료역학", 3)
            eneEcoTecAdm = Lecture("에너지환경기술경영", 3)
            earPhyEng = Lecture("지구물리공학", 3)
            eneThe = Lecture("에너지자원열역학", 3)
            eneFlu = Lecture("에너지자원유체역학", 3)
            eneEar = Lecture("에너지자원지구화학", 3)
            elaExp = Lecture("탄성파탐사", 3)
            stoDynExp = Lecture("암석역학및실험", 3)
            oilGasEngExp = Lecture("석유가스공학및실험", 3)
            resEngPra = Lecture("자원공학실습", 1)
            resEngDes = Lecture("자원공학설계", 1)
            resProEng = Lecture("자원처리공학", 3)

            //FreeLecture
            foreignFree = FreeLecture(foreign, 0)
            litArtFree = FreeLecture(litArt, 0)
            socIdeFree = FreeLecture(socIde, 0)
            lenLitFree = FreeLecture(lenLit, 0)
            culArtFree = FreeLecture(culArt, 0)
            hisPhiFree = FreeLecture(hisPhi, 0)
            polEcoFree = FreeLecture(polEco, 0)
            humSocFree = FreeLecture(humSoc, 0)
            socFree = FreeLecture(society, 0)
            creFree = FreeLecture(creativity, 0)
            optFree = FreeLecture(major_optional, 0)
            othFree = FreeLecture(major_other, 0)
            norFree = FreeLecture(normal, 0)
            majorOptOrNecFree = FreeLecture(major_optOrNec, 0)
        }
    }

    fun openForLecLayout(v : View) {
        totalLayout.visibility = View.INVISIBLE
        forLecLayout.visibility = View.VISIBLE
    }
    fun closeForLecLayout(v : View) {
        forLecLayout.visibility = View.INVISIBLE
        totalLayout.visibility = View.VISIBLE
    }

    fun addForLecture(view : View) {
        val forLectureName = forLecSpinnerEditText.text.toString()
        if((forLectureType.isNotEmpty()) && (forLectureName.isNotEmpty())) {
            forLecAdapter.setForLecture(ForLecture(forLectureType, forLectureName))
            ERETMToast(this, "외국어진행강좌가 추가되었습니다.", Toast.LENGTH_LONG)
        } else {
            ERETMToast(this, "과목의 종류와 과목명을 설정해주세요.", Toast.LENGTH_LONG)
        }
    }

    fun onMultiMajorButtonClicked(v : View) {
        AlertDialog.Builder(this).run {
            setTitle("전공과정 변경 안내")
            setMessage("전공과정 변경 시 과목들을 체크 및 추가해 놓은 데이터가 사라집니다. 전공과정 변경 후 다시 설정해 주세요.")
            val multiMajorDialogLayout = MultiMajorDialogLayout(this@MainActivity)
            setView(multiMajorDialogLayout)
            setPositiveButton("확인") { _, _ ->
                try {
                    val selectedMajor = multiMajorDialogLayout.checkSelectedMajor()
                    ERETMToast(this@MainActivity, selectedMajor + "을 선택하셨습니다.", Toast.LENGTH_LONG)
                    multiMajorButton.text = selectedMajor
                    progressingMajor = selectedMajor
                    changeMajorProcess(selectedMajor)
                } catch(e : Exception) {
                    ERETMToast(this@MainActivity, "전공과정이 선택되지 않았습니다.", Toast.LENGTH_LONG)
                }
            }
            setNegativeButton("취소") { _, _ ->

            }
            create().show()
        }
    }

    private fun changeMajorProcess(selectedMajor : String) {
        clearAll()
        initialization(studentNum)

        if(studentNum <= 15) { //15학번 이전
            when(selectedMajor) {
                EREOnly -> {}
                EREnOther -> {
                    d.run {
                        major = Type("전공", 42).apply {
                            addUnderManager(major_necessary)
                            addUnderManager(major_optOrNec.apply {
                                addUnderManager(majorOptOrNecFree)
                            })
                            addUnderManager(major_optNec)
                            addUnderManager(major_other)
                        }
                        if(studentNum <=13) { //13학번 이전
                            normal!!.minCredits = 41
                        } else if(studentNum <= 15) { //14, 15학번
                            normal!!.minCredits = 48
                        }
                    }
                }
                OthernERE -> {
                    total = Total(studentNum)
                    d.run {
                        major = Type("전공", 39).apply {
                            addUnderManager(major_necessary)
                            addUnderManager(major_optOrNec.apply {
                                addUnderManager(majorOptOrNecFree)
                            })
                            addUnderManager(major_optNec)
                        }
                        culture = null
                        normal = null
                    }
                }
            }
        } else if(studentNum >= 16) {  //16학번 이후
            when(selectedMajor) {
                EREOnly -> {}
                EREnOther -> {
                    d.run {
                        major = Type("전공", 42).apply {
                            addUnderManager(major_necessary)
                            addUnderManager(major_optional.apply {
                                minCredits = 20
                            })
                            addUnderManager(major_other)

                        }
                        normal!!.minCredits = 48
                    }
                }
                OthernERE -> {
                    total = Total(studentNum)
                    d.run {
                        major = Type("전공", 39).apply {
                            addUnderManager(major_necessary)
                            addUnderManager(major_optional.apply {
                                minCredits = 20
                            })
                        }
                        culture = null
                        normal = null
                    }
                }
                OthernSubERE -> {
                    total = Total(studentNum)
                    d.run {
                        major = Type("전공", 21).apply {
                            addUnderManager(LectureField("전공필수", 15).apply {
                                major_necessary = this
                                addUnderManager(eneEcoTecAdm)
                                addUnderManager(earPhyEng)
                                addUnderManager(resProEng)
                                addUnderManager(stoDynExp)
                                addUnderManager(oilGasEngExp)
                            })
                            addUnderManager(major_optional.apply {
                                minCredits = 6
                            })
                        }
                        culture = null
                        normal = null
                    }
                }
            }
        }
        total.run {
            this?.culture = d.culture
            this?.major = d.major
            this?.normal = d.normal
        }
        setting()
    }

    fun onAdviceButtonClicked(v : View) {
        AlertDialog.Builder(this).run {
            setTitle("도움말")
            setMessage("본 앱은 에자공 학부생 개인이 만든 것으로 각 항목들은 정확하지 않을 수 있습니다. 마이스누 > 학사정보 > 졸업 > 졸업사정(자가진단)처리 전공내역에서 '이수규정 및 내규조회' 및 졸업시뮬레이션 또는 학과사무실에 문의를 통해 정확한 졸업 요건을 확인하시기 바랍니다.\n" +
                    "학과사무실 : 02-880-7219")
            setPositiveButton("확인") { _, _ ->

            }
            setNeutralButton("에너지자원공학과 홈페이지 링크") { _, _ ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://ere.snu.ac.kr/ko/node/26")))
            }
            create().show()
        }
    }

    fun onMailButtonClicked(v : View) {
        AlertDialog.Builder(this).run {
            setTitle("데이터 메일로 내보내기")
            setMessage("기기변경 시 데이터 전달을 위해 메일로 데이터를 보낸 뒤 새로운 기기에서 메일의 첨부파일을 다운로드 할 수 있습니다.")
            val mailDialogLayout = MailDialogLayout(this@MainActivity)
            setView(mailDialogLayout)

            setPositiveButton("메일 보내기") { _, _ ->
                val address = mailDialogLayout.mailEditText.text.toString()
                startActivity(Intent(Intent.ACTION_SEND, Uri.parse("mailto:")).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
                    putExtra(Intent.EXTRA_SUBJECT, "에자공 졸업 학점 체크리스트 데이터 파일")
                    putExtra(Intent.EXTRA_TEXT, "=====\n" +
                            "데이터 파일 첨부 방법\n" +
                            "-> [파일에서 선택]\n" +
                            "-> 가장 하단에 [기타 위치 - 디바이스]\n" +
                            "(-> 상단 탭이 폰 기종으로 설정돼 있지 않으면 터치해서 폰 기종 선택(내부 저장소 접근))\n" +
                            "-> 가장 하단에 [PersonalCredits] 파일 선택\n" +
                            "-> 메일 전송\n" +
                            "=====\n" +
                            "새로운 폰에서 데이터 파일 다운로드 방법\n" +
                            "첨부된 파일 길게 터치\n" +
                            "-> [장치](다운로드)\n" +
                            "-> 홈으로 이동 후 [내 파일] 접근" +
                            "-> [Download] 폴더 접근\n" +
                            "-> [PersonalCredits] 파일 길게 터치\n" +
                            "-> 하단의 [이동]\n" +
                            "-> 상단의 [내장 메모리]\n" +
                            "-> [여기로 이동](완료)\n" +
                            "=====")
                })
            }
            setNegativeButton("취소") { _, _ -> }
            create().show()
        }
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
