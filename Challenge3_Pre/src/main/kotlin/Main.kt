// 프로그램을 실행할 때 맨 처음 출력되는 부분입니다.
fun main() {
    println("Activating")

    // 무한루프를 돌면서 사용자 입력을 받습니다.
    while (true) {
        // 입력받은 숫자를 저장할 배열입니다.
        var numbers: Array<Float> = arrayOf()

        // 무한루프를 돌면서 숫자를 입력받습니다.
        while (true) {
            // 현재까지 입력받은 숫자의 갯수와 다음으로 입력받을 숫자를 출력합니다.
            println("Input your ${numbers.size + 1} number\n--------------\ntype c to continue")
            val input = readLine() ?: ""

            // c를 입력하면 루프를 종료합니다.
            if (input == "c") {
                break
            } else try {
                // 입력받은 문자열을 실수형으로 변환하여 numbers 배열에 추가합니다.
                numbers += input.toFloat()
            } catch (e: Exception) {
                // 입력받은 값이 숫자가 아닌 경우 예외처리합니다.
                println("Something went wrong, Try again!")
            }
        }

        // 사용자가 선택한 연산자를 입력받습니다.
        println("Choose your operator \n+\n-\n/\n*")

        // 선택한 연산자에 따라 결과값을 계산하여 result에 저장합니다.
        val result = when (readLine()) {
            "+" -> Cal1().addition(numbers)
            "-" -> Cal1().subtraction(numbers)
            "/" -> Cal1().division(numbers)
            "*" -> Cal1().multiplication(numbers)
            else -> null // 지원하지 않는 연산자인 경우 null을 반환합니다.
        }

        // 결과값이 null이 아니면 출력하고, null이면 "Invalid operator" 메시지를 출력합니다.
        result?.let {
            println(it)
        } ?: println("Invalid operator")

        // 프로그램을 종료할 것인지 계속 진행할 것인지 입력받습니다.
        println("Do you want to exit the program?\n yes/no")
        if (readLine() == "yes") {
            println("Good Bye!")
            break // 프로그램을 종료합니다.
        } else {
            println("Continuing!")
        }
    }
}

// Cal1 클래스는 각종 사칙연산을 담당합니다.
class Cal1 {
    // 입력받은 숫자들을 더한 값을 반환합니다.
    fun addition(numbers: Array<Float>): Float = numbers.sum()

    // 입력받은 숫자들을 차례대로 빼서 결과값을 반환합니다.
    fun subtraction(numbers: Array<Float>): Float = numbers.reduce { acc, n -> acc - n }

    // 입력받은 숫자들을 차례대로 나눠서 결과값을 반환합니다.
    fun division(numbers: Array<Float>): Float = numbers.fold(1f) { acc, n -> acc / n }

    // 입력받은 숫자들을 차례대로 곱해서 결과값을 반환합니다.
    fun multiplication(numbers: Array<Float>): Float = numbers.fold(1f) { acc, n -> acc * n }
}

