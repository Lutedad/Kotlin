fun main() {
    println("Write your phrase") // 사용자에게 문자열을 입력받는다는 안내 메시지 출력
    val phrase: String = readln() // 사용자 입력을 받아 phrase 변수에 할당
    val numbers: Int = countVowels(phrase) // countVowels 함수를 호출하여 모음 개수를 반환받아 numbers 변수에 할당

    // 모음 개수에 따른 출력문을 조건문으로 나누어 출력
    if (numbers == 1) {
        println("There is one vowel in $phrase")
    } else if (numbers > 1) {
        println("There are $numbers vowels in $phrase")
    } else {
        println("WHAT THE HECK? SOMETHING WENT WRONG!!")
    }
}

// 문자열에서 모음의 개수를 반환하는 함수
fun countVowels(phrase: String): Int {
    val vowels: Array<Char> = arrayOf('a', 'e', 'i', 'o', 'u') // 모음을 배열에 할당
    val length = phrase.length // 문자열의 길이를 구하여 length 변수에 할당

    var result = 0 // 모음의 개수를 저장할 변수 초기화

    // 모음 배열에서 하나씩 꺼내와서 문자열과 비교하며 모음의 개수를 셈
    vowels.forEach {
        var i = 0
        while (i < length) {
            if (phrase[i] == it) { // 현재 비교하는 문자가 모음인 경우, 모음 개수를 1 증가시키고 i를 1 증가시킴
                result++
                i++
            } else {
                i++ // 비교하는 문자가 모음이 아닌 경우, i를 1 증가시킴
            }
        }
    }
    return result // 구해진 모음 개수 반환
}
