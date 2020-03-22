package agi.erecreditsmanager

import agi.erecreditsmanager.FreeLecture.FreeLecture
import agi.erecreditsmanager.Lecture.Lecture
import agi.erecreditsmanager.LectureField.LectureField
import agi.erecreditsmanager.LectureGroup.LectureGroup
import agi.erecreditsmanager.LectureWorld.LectureWorld
import agi.erecreditsmanager.Type.Type

class DataManager {
    //Type
    var culture : Type? = Type("교양", 40)
    var major = Type("전공", 62)
    var normal : Type? = Type("그 외", 28)

    //LectureField
    var culture_basic = LectureField("학문의 기초", 34)
    var keyCulture = LectureField("핵심교양", 9)
    var culture_world = LectureField("학문의 세계(2개 영역 이상)", 6)
    var culture_engineering = LectureField("공대 사회/창의성", 6)
    var major_necessary = LectureField("전공필수", 19)
    var major_optNec = LectureField("전공선택필수", 9)
    var major_optional = LectureField("전공선택", 40)
    var major_optOrNec = LectureField("전공선택, 전공선택필수", 3) // 15학번 이전 복수/부전공을 위한 항목
    var major_other = LectureField("공대 타학과개론", 3)

    //LectureGroup
    var thiExp = LectureGroup("사고와 표현", 3)
    var foreign = LectureGroup("외국어 2개 교과목\n    (TEPS 900점 이하 영어 1과목 필수)", 4)
    var numAnaInf = LectureGroup("수량적 분석과 추론", 12)
    var sciThiExp = LectureGroup("과학적 사고와 실험", 12)
    var comInfApp = LectureGroup("컴퓨터와 정보 활용", 3)
    var society = LectureGroup("사회성 교과목군 or 인간과 사회 영역", 3)
    var creativity = LectureGroup("창의성 교과목군 or 문화와 예술 영역", 3)

    //LectureWorld
    var litArt = LectureWorld("문학과 예술")
    var socIde = LectureWorld("사회와 이념")
    var lenLit = LectureWorld("언어와 문학")
    var culArt = LectureWorld("문화와 예술")
    var hisPhi = LectureWorld("역사와 철학")
    var polEco = LectureWorld("정치와 경제")
    var humSoc = LectureWorld("인간과 사회")

    //Lecture
    var sciEngWri = Lecture("과학과 기술 글쓰기", 3)
    var korean = Lecture("대학국어", 3)
    var math1 = Lecture("(고급)수학 및 연습1", 3)
    var math2 = Lecture("(고급)수학 및 연습2", 3)
    var engMat1 = Lecture("공학수학1", 3)
    var engMat2 = Lecture("공학수학2", 3)
    var physics1 = Lecture("(고급)물리학1(물리의 기본1)", 3)
    var phyExp1 = Lecture("물리학실험1", 1)
    var physics2 = Lecture("(고급)물리학2(물리의 기본2)", 3)
    var phyExp2 = Lecture("물리학실험2", 1)
    var physics = Lecture("물리학", 3)
    var phyExp = Lecture("물리학실험", 1)
    var chemistry1 = Lecture("화학1", 3)
    var cheExp1 = Lecture("화학실험1", 1)
    var chemistry2 = Lecture("화학2", 3)
    var cheExp2 = Lecture("화학실험2", 1)
    var chemistry = Lecture("화학", 3)
    var cheExp = Lecture("화학실험", 1)
    var biology1 = Lecture("생물학1", 3)
    var bioExp1 = Lecture("생물학실험1", 1)
    var biology2 = Lecture("생물학2", 3)
    var bioExp2 = Lecture("생물학실험2", 1)
    var biology = Lecture("생물학", 3)
    var bioExp = Lecture("생물학실험", 1)
    var statistics = Lecture("통계학", 3)
    var staExp = Lecture("통계학실험", 1)
    var earSysSci = Lecture("지구시스템과학", 3)
    var earSysSciExp = Lecture("지구시스템과학실험", 1)
    var computer = Lecture("컴퓨터의 개념 및 실습", 3)
    var eneResFut = Lecture("에너지자원과미래", 2)
    var advResGeo = Lecture("응용자원지질", 3)
    var eneResFigAna = Lecture("에너지자원수치해석", 3)
    var driEng = Lecture("시추공학", 3)
    var newRenEne = Lecture("신재생에너지", 3)
    var advEarChe = Lecture("응용지구화학", 3)
    var eneEcoEng = Lecture("에너지환경공학", 3)
    var eneResDyn = Lecture("에너지자원역학", 3)
    var eneEcoTecAdm = Lecture("에너지환경기술경영", 3)
    var earPhyEng = Lecture("지구물리공학", 3)
    var stoDynExp = Lecture("암석역학및실험", 3)
    var oilGasEngExp = Lecture("석유가스공학및실험", 3)
    var resEngPra = Lecture("자원공학실습", 1)
    var resProEng = Lecture("자원처리공학", 3)

    //FreeLecture
    var foreignFree = FreeLecture(foreign, 0)
    var litArtFree = FreeLecture(litArt, 0)
    var socIdeFree = FreeLecture(socIde, 0)
    var lenLitFree = FreeLecture(lenLit, 0)
    var culArtFree = FreeLecture(culArt, 0)
    var hisPhiFree = FreeLecture(hisPhi, 0)
    var polEcoFree = FreeLecture(polEco, 0)
    var humSocFree = FreeLecture(humSoc, 0)
    var socFree = FreeLecture(society, 0)
    var creFree = FreeLecture(creativity, 0)
    var optFree = FreeLecture(major_optional, 0)
    var othFree = FreeLecture(major_other, 0)
    var norFree = FreeLecture(normal, 0)
    var majorFree = FreeLecture(major, 0)
    var majorOptOrNecFree = FreeLecture(major_optOrNec, 0)
}