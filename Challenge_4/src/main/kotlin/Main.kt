fun main() {
    val result: Int? = countOddNumbers(arrayOf(1, 2, 3, 4, 5))
    result?.let {
        println(it)
    } ?: println("There is not an Integer to represent the number of odd numbers.")
}

fun countOddNumbers(numbers: Array<Int>): Int? {
    var i = 0
    numbers.forEach {
        if (it % 2 != 0) {
            i++
        }
    }
    return if (i in numbers) {
        i
    } else {
        null
    }

}