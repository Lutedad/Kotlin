// 두 숫자를 입력받아 더한 결과를 반환하는 함수를 작성하세요.
// 함수명: addNumbers
// 매개변수: 첫번째 숫자 num1(Int), 두번째 숫자 num2(Int)
// 반환값: 두 숫자의 합(Int)
//fun main() {
//    val num1 = 10
//    val num2 = 20
//    val result = addNumbers(num1, num2)
//    println("두 수의 합: $result")
//}
//
//fun addNumbers(num1 : Int, num2: Int): Int {
//    return num1 + num2
//}


// 자연수 n을 입력받아 1부터 n까지의 합을 반환하는 함수를 작성하세요.
// 함수명: sumNumbers
// 매개변수: 자연수 n(Int)
// 반환값: 1부터 n까지의 합(Int)

// 예시
// sumNumbers(10) -> 55 (1+2+3+4+5+6+7+8+9+10 = 55)

fun main(){
    val result = sumNumbers(40)
    println(result)
}

fun sumNumbers(n:Int): Double {
    return if (n%2 == 0){
        (n+1)*0.5*n
    }else {
        (n-1)*0.5*n+n
    }
}