import java.math.BigDecimal
import kotlin.math.abs

fun main() {
    var isContinue = true
    do {
        println("#1 - My age. For a number, considered as the age of the person, return a line of view «21 год», «32 года», «12 лет»\n" +
                "#2 - For a given number n > 1, find a minimum divisor greater than 1\n" +
                "#3 - Determine whether a brick with sides a, b, c through a rectangular hole in the wall with side r, s\n" +
                "#4 - For the given x, calculate with the specified eps accuracy (sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...)\n" +
                "#5 - Return 0 if no threat, 1 if threat only from rook, 2 if only from elephant, and 3, if there is a threat from both the rook and the elephant\n" +
                "#6 - Convert natural number n > 0 to Roman system\n" +
                "# - any to exit")
        when(inputIntInRange(1, Int.MAX_VALUE)){
            1 -> {
                println(ageDescription(inputIntInRange(1, 200)))
            }
            2 -> {
                println(minDivisor(inputIntInRange(1, Int.MAX_VALUE)))
            }
            3 -> {
                performThirdTask()
            }
            4 -> {
                performFourthTask()
            }
            5 -> {
                performFifthTask()
            }
            6 -> {
                println(roman(inputIntInRange(1, 3999)))
            }
            else ->{
                isContinue = false
            }
        }
    }while (isContinue)
}

fun ageDescription(age: Int): String{
    return if (age % 100 in 11..14){
        "$age лет"
    }else{
         when (age % 10) {
            1 -> {
                "$age год"
            }
            in 2..4 -> {
                "$age года"
            }
            else -> {
                "$age лет"
            }
        }
    }
}

fun minDivisor(n: Int): Int{
    for (i in 2 until n ){
        if (n % i == 0){
            return i
        }
    }
    return n
}

fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean{
    if (((a <= r) and (b <= s)) or ((a <= s) and (b <= r))
        or ((a <= r) and (c <= s)) or ((a <= s) and (c <= r))
        or ((b <= r) and (c <= s)) or ((b <= s) and (c <= r))){
        return true
    }
    return false
}

fun sin(x: Double, eps: Double): Double{
    var current = x.toBigDecimal()
    var result = 0.toBigDecimal()
    var sign = 1
    var temp = 1.toBigDecimal()
    while (abs(current.toDouble()) > abs(eps)){
        current = pow(x.toBigDecimal(), temp) / fact(temp)
        result += current * sign.toBigDecimal()
        sign *= -1
        temp += 2.toBigDecimal()
    }
    println("full sin(x)= $result")
    return result.toDouble()
}

fun pow(x: BigDecimal, pow: BigDecimal): BigDecimal{
    var i = 1.toBigDecimal()
    var result = x
    while (i < pow){
        result *= x
        i++
    }
    return result
}

fun fact(n: BigDecimal): BigDecimal {
    return if( n!=1.toBigDecimal() ) {
        n * fact(n-1.toBigDecimal())
    } else 1.toBigDecimal()
}

fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int): Int{
    return if(((kingX == rookX) or (kingY == rookY)) and (abs(kingX - bishopX) == abs(kingY - bishopY))){
        3
    }else if(abs(kingX - bishopX) == abs(kingY - bishopY)){
        2
    }else if((kingX == rookX) or (kingY == rookY)){
        1
    }else{
        0
    }
}

fun roman(n: Int): String{
    val map = mapOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
                    100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
                    10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
    var number = n
    var result = ""
    while (number > 0){
        map.forEach { (digit, romanDigit) ->
            while (number >= digit){
                result += romanDigit
                number -= digit
            }
        }
    }
    return result
}

fun inputIntInRange(min: Int, max:Int): Int{
    var isNotCorrect: Boolean
    var number = 0
    do {
        isNotCorrect = false
        println("Input number in range $min..$max")
        try {
            number = readLine()!!.toInt()
        }catch (e: NumberFormatException){
            println("Wrong input, try again: ")
            isNotCorrect = true
        }
        if ((number < min) or (number > max)){
            isNotCorrect = true
        }
    }while (isNotCorrect)
    return number
}

fun inputDouble(): Double{
    var isNotCorrect = false
    var number = 0.0
    do {
        try {
            number = readLine()!!.toDouble()
        }catch (e: NumberFormatException){
            println("Wrong input, try again: ")
            isNotCorrect = true
        }
    }while (isNotCorrect)
    return number
}

fun performThirdTask(){
    println("Input a ")
    val a = inputIntInRange(1, Int.MAX_VALUE)
    println("Input b ")
    val b = inputIntInRange(1, Int.MAX_VALUE)
    println("Input c ")
    val c = inputIntInRange(1, Int.MAX_VALUE)
    println("Input r ")
    val r = inputIntInRange(1, Int.MAX_VALUE)
    println("Input s ")
    val s = inputIntInRange(1, Int.MAX_VALUE)
    if (brickPasses(a, b, c, r, s))
        println("Brick passes")
    else
        println("Brick does not passes")
}

fun performFourthTask(){
    println("Input x ")
    val x = inputDouble()
    println("Input eps ")
    val eps = inputDouble()
    println("sin(x) = " + sin(x, eps))
}

fun performFifthTask(){
    println("Input KingX ")
    val kingX = inputIntInRange(1, 8)
    println("Input KingY ")
    val kingY = inputIntInRange(1, 8)
    println("Input RookX ")
    val rookX = inputIntInRange(1, 8)
    println("Input RookY ")
    val rookY = inputIntInRange(1, 8)
    println("Input BishopX ")
    val bishopX = inputIntInRange(1, 8)
    println("Input BishopY ")
    val bishopY = inputIntInRange(1, 8)
    println(rookOrBishopThreatens(kingX, kingY, rookX, rookY, bishopX, bishopY))
}
