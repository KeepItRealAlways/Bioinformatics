fun main(args: Array<String>) {

    val money = readLine()!!.toInt()
    val coins = readLine()!!.split(",").map { it.toInt() }

    val table = arrayOfNulls<Int>(money+1)
    table[0] = 0

    for (i in 1..money) {
        minNumCoins(money, coins, table)
    }

    println(table[money])
}

fun minNumCoins(money: Int, coins: List<Int>, table: Array<Int?>): Int {

    return if (table[money] != null) {
        table[money]!!
    } else {
        var minNumCoins = Int.MAX_VALUE

        for (coin in coins) {
            if (money - coin >= 0) {
                val numCoins = minNumCoins(money - coin, coins, table) + 1
                if (numCoins < minNumCoins) {
                    minNumCoins = numCoins
                }
            }
        }

        table[money] = minNumCoins

        table[money]!!
    }
}
