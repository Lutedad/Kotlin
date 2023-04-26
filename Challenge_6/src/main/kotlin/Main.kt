fun main(args: Array<String>) {
    val calculateLengths = fun(str:List<String>): List<Int> = str.map { it.trim().length }
    println(calculateLengths(listOf("Hello nice to meet you!","I don't know who you are!!!!!")))
}



//문자열 리스트를 입력받아, 각 문자열의 길이를 계산하고 그 결과를 리스트로 반환하는 함수를 작성해봐.
//
//조건:
//
//함수명: calculateLengths
//매개변수: 문자열 리스트 list
//반환값: 정수 리스트
//단, 람다식을 사용해서 구현해봐