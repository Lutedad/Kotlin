fun main() {
    println("Activating")

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

        val result = when (readln()) {
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