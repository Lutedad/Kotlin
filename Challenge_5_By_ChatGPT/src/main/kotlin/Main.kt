fun main() {
    // 사용자로부터 문자열 입력받기
    println("Write your phrase")
    val phrase = readln().trim()

    // 입력받은 문자열의 모음 개수 카운트
    val numVowels = countVowels(phrase)

    // 모음 개수에 따라 출력문 결정
    when (numVowels) {
        0 -> println("There are no vowels in $phrase")
        1 -> println("There is one vowel in $phrase")
        else -> println("There are $numVowels vowels in $phrase")
    }
}

fun countVowels(str: String): Int {
    // 모음 배열 선언
    val vowels = arrayOf('a', 'e', 'i', 'o', 'u')

    // 문자열에서 모음 개수 카운트
    var vowelCount = 0
    for (char in str) {
        if (char.lowercaseChar() in vowels) { // 소문자로 변경 후 모음 배열에 포함되는지 체크
            vowelCount++
        }
    }

    // 모음 개수 반환
    return vowelCount
}
