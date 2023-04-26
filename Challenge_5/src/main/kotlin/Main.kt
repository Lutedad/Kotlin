fun main() {
    println("Write your phrase")
    val phrase: String = readln()
    val numbers: Int = countVowels(phrase)
    if (numbers == 1) {
        println("There is one vowel in $phrase")
    } else if (numbers > 1) {
        println("There are $numbers vowels in $phrase")
    } else {
        println("WHAT THE HECK? SOMETHING WENT WRONG!!")
    }
}

fun countVowels(phrase: String): Int {
    val vowels: Array<Char> = arrayOf('a', 'e', 'i', 'o', 'u')
    val length = phrase.length

    var result = 0


    vowels.forEach {
        var i = 0
        while (i < length) {

            if (phrase[i] == it) {
                result++;i++
            } else {
                i++
            }

        }


    }
    return result
}


//아래와 같은 기능을 수행하는 함수를 작성해보세요.
//
//함수명: countVowels
//매개변수: 문자열 str
//반환값: str 내에 포함된 모음의 개수를 나타내는 정수
//
//예를 들어, countVowels("Hello World") 함수를 호출하면 3을 반환합니다.