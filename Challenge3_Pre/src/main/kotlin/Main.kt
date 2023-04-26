//fun main(array: Array<String>) {
//    println("Activating")
//
//    while (true) {
//        var count = 0
//        var numbers: Array<Float> = arrayOf()
//
//        while (true) {
//            count++
//
//            println("Input your $count number\n--------------\ntype c to continue")
//            var input = readln()
//
//            if (input == "c") {
//                break
//            } else try {
//                numbers = numbers.plus(input.toFloat())
//            } catch (e: Exception) {
//                println("Something went wrong, Try again!")
//            }
//        }
//
//        println("Choose your operator \n+\n-\n/\n*")
//        val operator = readln().toString()
//
//        if (operator == "+") {
//            var result = Cal().addition(numbers)
//            println(result)
//        } else if (operator == "-") {
//            var result = Cal().subtraction(numbers)
//            println(result)
//        } else if (operator == "/") {
//            var result = Cal().division(numbers)
//            println(result)
//        } else if (operator == "*") {
//            var result = Cal().multiplication(numbers)
//            println(result)
//        }
//        //Cal().addition()
//        println("Do you want to exit the program?\n yes/no")
//        if (readln() == "yes") {
//            break
//        } else {
//            println("Continuing!")
//        }
//
//    }
//}
//
//class Cal() {
//    fun addition(numbers: Array<Float>): Float {
//        var addResult = 0f
//        for (number in numbers) {
//            addResult += number
//        }
//        return addResult
//    }
//
//    fun subtraction(numbers: Array<Float>): Float {
//        var subResult: Float = 0f
//        for (number in numbers) {
//            subResult -= number
//        }
//        return subResult
//    }
//
//    fun division(numbers: Array<Float>): Float {
//        var divResult = 0f
//        var count1 = 0
//        for (number in numbers) {
//            count1++
//            if (count1 == 1) {
//                divResult = number.toFloat()
//            } else {
//                divResult /= number
//            }
//        }
//        return divResult
//    }
//
//    fun multiplication(numbers: Array<Float>): Float {
//        var multiResult = 0f
//        var count1 = 0
//        for (number in numbers) {
//            count1++
//            if (count1 == 1) {
//                multiResult = number.toFloat()
//            } else {
//                multiResult *= number
//            }
//        }
//        return multiResult
//    }
//}
fun main() {
    println("Activating")
    println("Testing Git")

    while (true) {
        var count = 0
        var numbers = emptyList<Float>()

        while (true) {
            count++

            println("Input your $count number\n--------------\ntype c to continue")
            val input = readln()

            if (input == "c") {
                break
            } else {
                input.toFloatOrNull()?.let {
                    numbers = numbers + it
                } ?: println("Something went wrong, Try again!")
            }
        }

        println("Choose your operator \n+\n-\n/\n*")
        val operator = readln()

        val result = when (operator) {
            "+" -> Cal::addition
            "-" -> Cal::subtraction
            "/" -> Cal::division
            "*" -> Cal::multiplication
            else -> null
        }?.invoke(numbers.toTypedArray())

        result?.let {
            println(it)
        }

        println("Do you want to exit the program?\n yes/no")
        if (readln() == "yes") {
            break
        } else {
            println("Continuing!")
        }
    }
}

class Cal {
    companion object {
        fun addition(numbers: Array<Float>): Float = numbers.sum()

        fun subtraction(numbers: Array<Float>): Float = numbers.fold(0f, { acc, n -> acc - n })

        fun division(numbers: Array<Float>): Float = numbers.fold(1f, { acc, n -> acc / n })

        fun multiplication(numbers: Array<Float>): Float = numbers.fold(1f, { acc, n -> acc * n })
    }
}

