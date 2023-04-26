fun main() {
    val result = calculate("1+2+3-9+8")
    println(result)
}

fun calculate(str: String): Int? {
    var i = 0
    var num = mutableListOf<Int>()
    var operator = mutableListOf<Char>()

    while (i < str.length) {
        if (str[i].isDigit()) {
            num += str[i].toString().toInt()
        } else if (str[i] in listOf('+', '-')) {
            operator += str[i]
        }
        i++
    }

    var result = num[0]
    for (j in 1 until num.size) {
        when (operator[j - 1]) {
            '+' -> result += num[j]
            '-' -> result -= num[j]
        }
    }
    return result
}

////문제
////두개의 수식으로 이루어진 문자열을 입력받아, 수식의 연산 결과를 계산하는 함수를 작성해보세요.
////
////수식은 덧셈과 뺄셈으로만 이루어져 있으며, 숫자와 연산자 사이에는 공백이 없다고 가정합니다. 수식에는 괄호가 없으며, 덧셈과 뺄셈 연산자는 번갈아서 등장합니다.
////
////함수는 문자열을 인자로 받아서, 계산된 결과를 반환해야 합니다.
////
////예시
////
////scss
////Copy code
////calculate("1 + 2 - 3 + 4 - 5") => -1
////calculate("10 - 5 - 3 - 1") => 1
////calculate("1 + 2 + 3 + 4") => 10
////참고
////
////입력된 수식은 반드시 덧셈과 뺄셈으로만 이루어져 있다고 가정합니다.
////숫자와 연산자 사이에는 공백이 없다고 가정합니다.
////입력으로 받는 수식의 길이는 100 이하입니다.
////반환값은 정수형으로 표현합니다.

