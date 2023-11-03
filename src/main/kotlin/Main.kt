fun main() {
    println(calcCommission(cardType = "MasterCard", amount = 100.0, initSum = 75000.0))
    println(calcCommission(cardType = "MasterCard", amount = 100.0, initSum = 500.0))
    println(calcCommission(cardType = "Visa", amount = 100.0))
    println(calcCommission(cardType = "Мир", amount = 10000.0))
    println(calcCommission(amount = 10000.0))

}

fun calcCommission(cardType: String = "VK Pay", initSum: Double = 0.0, amount: Double): Double {
    val commission: Double =
        when (cardType) {
            "MasterCard", "Maestro" -> if (initSum >= 75000.0) amount * 0.006 + 20.0 else 0.0
            "Visa", "Мир" -> if (amount * 0.0075 < 35.0) 35.0 else amount * 0.0075
            else -> 0.0
        }
    return commission
}