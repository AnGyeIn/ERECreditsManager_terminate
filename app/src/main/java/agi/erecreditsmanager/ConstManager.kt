package agi.erecreditsmanager

object ConstManager {
    const val OFF = false
    const val ON = true

    //전공과정 변경 처리를 위한 상수
    const val EREOnly = "에자공 주전공"
    const val EREnOther = "에자공 주전공, 타학과 복수/부전공"
    const val OthernERE = "타학과 주전공, 에자공 복수전공"
    const val OthernSubERE = "타학과 주전공, 에자공 부전공"

    //클래스 구분을 위한 코드 상수
    const val TYPE = 0
    const val LECTURE_FIELD = 1
    const val LECTURE_GROUP = 2
    const val LECTURE_WORLD = 3
    const val LECTURE = 4
    const val FREE_LECTURE = 5
    const val ADDED_LECTURE = 6
}