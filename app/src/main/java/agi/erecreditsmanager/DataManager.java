package agi.erecreditsmanager;

import agi.erecreditsmanager.FreeLecture.FreeLecture;
import agi.erecreditsmanager.Lecture.Lecture;
import agi.erecreditsmanager.LectureField.LectureField;
import agi.erecreditsmanager.LectureGroup.LectureGroup;
import agi.erecreditsmanager.LectureWorld.LectureWorld;
import agi.erecreditsmanager.Type.Type;

public class DataManager {

    final public static boolean OFF = false;
    final public static boolean ON = true;


    //Type
    final public static int TYPE = 0;
    static Type culture = new Type("교양", 40);
    static Type major = new Type("전공", 62);
    static Type normal = new Type("그 외", 28);


    //LectureField
    final public static int LECTURE_FIELD = 1;
    static LectureField culture_basic = new LectureField("학문의 기초", 34);
    static LectureField culture_world = new LectureField("학문의 세계(2개 영역 이상)", 6);
    static LectureField culture_engineering = new LectureField("공대 사회/창의성", 6);
    static LectureField major_necessary_to15 = new LectureField("전공필수", 27);
    static LectureField major_necessary_from16 = new LectureField("전공필수", 19);
    static LectureField major_optNec = new LectureField("전공선택필수", 9);
    static LectureField major_optional_to15 = new LectureField("전공선택", 23);
    static LectureField major_optional_from16 = new LectureField("전공선택", 40);
    static LectureField major_other = new LectureField("공대 타학과개론", 3);

    //LectureGroup
    final public static int LECTURE_GROUP = 2;
    static LectureGroup thiExp = new LectureGroup("사고와 표현", 3);
    static LectureGroup foreign = new LectureGroup("외국어 2개 교과목\n    (TEPS 900점 이하 영어 1과목 필수)", 4);
    static LectureGroup numAnaInf = new LectureGroup("수량적 분석과 추론", 12);
    static LectureGroup sciThiExp = new LectureGroup("과학적 사고와 실험", 12);
    static LectureGroup comInfApp = new LectureGroup("컴퓨터와 정보 활용", 3);
    static LectureGroup society = new LectureGroup("사회성 교과목군 or 인간과 사회 영역", 3);
    static LectureGroup creativity = new LectureGroup("창의성 교과목군 or 문화와 예술 영역", 3);

    //LectureWorld
    final public static int LECTURE_WORLD = 3;
    static LectureWorld lenLit = new LectureWorld("언어와 문학");
    static LectureWorld culArt = new LectureWorld("문화와 예술");
    static LectureWorld hisPhi = new LectureWorld("역사와 철학");
    static LectureWorld polEco = new LectureWorld("정치와 경제");
    static LectureWorld humSoc = new LectureWorld("인간과 사회");

    //Lecture
    final public static int LECTURE = 4;
    static Lecture sciEngWri = new Lecture("과학과 기술 글쓰기", 3);
    static Lecture math1 = new Lecture("(고급)수학 및 연습1", 3);
    static Lecture math2 = new Lecture("(고급)수학 및 연습2", 3);
    static Lecture engMat1 = new Lecture("공학수학1", 3);
    static Lecture engMat2 = new Lecture("공학수학2", 3);
    static Lecture physics1 = new Lecture("(고급)물리학1(물리의 기본1)", 3);
    static Lecture phyExp1 = new Lecture("물리학실험1", 1);
    static Lecture physics2 = new Lecture("(고급)물리학2(물리의 기본2)", 3);
    static Lecture phyExp2 = new Lecture("물리학실험2", 1);
    static Lecture physics = new Lecture("물리학", 3);
    static Lecture phyExp = new Lecture("물리학실험", 1);
    static Lecture chemistry1 = new Lecture("화학1", 3);
    static Lecture cheExp1 = new Lecture("화학실험1", 1);
    static Lecture chemistry2 = new Lecture("화학2", 3);
    static Lecture cheExp2 = new Lecture("화학실험2", 1);
    static Lecture chemistry = new Lecture("화학", 3);
    static Lecture cheExp = new Lecture("화학실험", 1);
    static Lecture earSysSci = new Lecture("지구시스템과학", 3);
    static Lecture earSysSciExp = new Lecture("지구시스템과학실험", 1);
    static Lecture computer = new Lecture("컴퓨터의 개념 및 실습", 3);
    //15학번 이전 전공필수(16학번 전공필수에 추가)
    static Lecture eneResFut = new Lecture("에너지자원과미래", 2);
    static Lecture advResGeo = new Lecture("응용자원지질", 3);
    static Lecture eneResFigAna = new Lecture("에너지자원수치해석", 3);
    //15학번 이전 전공선택필수
    static Lecture driEng = new Lecture("시추공학", 3);
    static Lecture newRenEne = new Lecture("신재생에너지", 3);
    static Lecture advEarChe = new Lecture("응용지구화학", 3);
    static Lecture eneEcoEng = new Lecture("에너지환경공학", 3);
    //16학번 이후 전공필수
    static Lecture eneResDyn = new Lecture("에너지자원역학", 3);
    static Lecture eneEcoTecAdm = new Lecture("에너지환경기술경영", 3);
    static Lecture earPhyEng = new Lecture("지구물리공학", 3);
    static Lecture stoDynExp = new Lecture("암석역학및실험", 3);
    static Lecture oilGasEngExp = new Lecture("석유가스공학및실험", 3);
    static Lecture resEngPra = new Lecture("자원공학실습", 1);
    static Lecture resProEng = new Lecture("자원처리공학", 3);

    //FreeLecture
    final public static int FREE_LECTURE = 5;
    static FreeLecture foreignFree = new FreeLecture(foreign, 0);
    static FreeLecture lenLitFree = new FreeLecture(lenLit, 0);
    static FreeLecture culArtFree = new FreeLecture(culArt, 0);
    static FreeLecture hisPhiFree = new FreeLecture(hisPhi, 0);
    static FreeLecture polEcoFree = new FreeLecture(polEco, 0);
    static FreeLecture humSocFree = new FreeLecture(humSoc, 0);
    static FreeLecture socFree = new FreeLecture(society, 0);
    static FreeLecture creFree = new FreeLecture(creativity, 0);
    static FreeLecture optFree_to15 = new FreeLecture(major_optional_to15, 0);
    static FreeLecture optFree_from16 = new FreeLecture(major_optional_from16, 0);
    static FreeLecture othFree = new FreeLecture(major_other, 0);
    static FreeLecture norFree = new FreeLecture(normal, 0);

    //AddedLecture
    final public static int ADDED_LECTURE = 6;

}