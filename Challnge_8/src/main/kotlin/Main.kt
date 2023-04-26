//문자열을 입력받아, 그 문자열이 팰린드롬인지 아닌지를 판별하는 함수를 작성하세요.
//
//함수명: isPalindrome
//매개변수: 문자열 str
//반환값: boolean
//팰린드롬: 앞으로 읽으나 뒤로 읽으나 동일한 단어 또는 문장을 말합니다.
//예를 들어, "racecar"와 "level"은 팰린드롬이지만 "hello"는 아닙니다.




//아니 fun isPalindrome(str: String): Boolean {
//    return str == str.reversed()
//}
//이렇게 간단했다고??? .reversed() 하나를 몰라가지고 ㅠㅠ.




fun main() {
    val result = isPalindrome("level").toString()
    println("It is $result")
}

fun isPalindrome(str: String): Boolean? {
    val length = str.length
    var i = 0.0

    while (i < length) {
        if (length % 2 == 0) {
            while (str[(0 + i).toInt()] == str[(length - i - 1).toInt()]) {
                i++
                if (i == length * 0.5) {
                    return true
                }
            };break
        } else {
            while (str[(0 + i).toInt()] == str[(length - i - 1).toInt()]) {
                i++
                if (0 + i > length - i - 1) {
                    return true
                }

            }
            return false

        }
    }

return null
}