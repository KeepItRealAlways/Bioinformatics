fun main(args: Array<String>) {
    val n = readLine()!!.toLong()
    println(result(n))
}

fun result(n: Long): Long = n * (n-1)
