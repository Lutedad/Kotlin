fun main(array: Array<String>) {
    println("Activating")
    //Cal().addition()
}

class Cal() {
    fun addition(numbers: Array<Int>): Int {
        var addResult = 0
        for (number in numbers) {
            addResult += number
        }
        return addResult
    }

    fun subtraction(numbers: Array<Int>): Int {
        var subResult = 0
        for (number in numbers)
            subResult -= number
        return subResult
    }

    fun division(numbers: Array<Int>) {
    }

    fun multiplication(numbers: Array<Int>) {
    }
}
