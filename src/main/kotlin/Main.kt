fun main() {
    println(calcCommission(cardType = "MasterCard", amount = 1000.0, initSum = 75000.0))
    println(calcCommission(cardType = "MasterCard", amount = 1000.0, initSum = 74950.0))
    println(calcCommission(cardType = "MasterCard", amount = 1000.0, initSum = 500.0))
    println(calcCommission(cardType = "Visa", amount = 100.0))
    println(calcCommission(cardType = "Мир", amount = 10000.0))
    println(calcCommission(cardType = "UnionPay", amount = 10000.0))
    println(calcCommission(amount = 10000.0))
    println(calcCommission(amount = 15001.0))
    println(calcCommission(amount = 1000.0, initSum = 39500.0))
    println(calcCommission(cardType = "Visa", amount = 200000.0))
    println(calcCommission(cardType = "Visa", amount = 100000.0, initSum = 550000.0))


}

fun calcCommission(amount: Double, cardType: String = "VK Pay", initSum: Double = 0.0): Double {
    if (!checkLimits(amount, cardType, initSum)) {
        return -1.0
    }
    return when (cardType) {
        "MasterCard", "Maestro" -> {
            val prefMonthLimit = 75000.0
            val commissionRate = 0.006
            val fixCommission = 20.0
            if (initSum + amount > prefMonthLimit) {
                val taxableAmount =
                    if (initSum >= prefMonthLimit) amount
                    else amount - (prefMonthLimit - initSum)
                taxableAmount * commissionRate + fixCommission
            } else 0.0
        }

        "Visa", "Мир" -> {
            val commissionRate = 0.0075
            val fixCommission = 35.0
            if (amount * commissionRate < fixCommission) fixCommission else amount * commissionRate
        }

        else -> 0.0
    }
}

fun checkLimits(amount: Double, cardType: String = "VK Pay", initSum: Double = 0.0): Boolean {
    return if (cardType == "VK Pay") {
        val limit = 40000
        val dayLimit = 15000
        amount < dayLimit && initSum + amount < limit
    } else {
        val limit = 600000
        val dayLimit = 150000
        amount < dayLimit && initSum + amount < limit
    }
}