fun main(args: Array<String>) {
    println(PatternCount(readLine()!!, readLine()!!))
}

fun PatternCount(pattern: String, genome: String): Int {
    var count = 0
    for (i in 0..genome.length - pattern.length) {
        if (pattern == genome.substring(i, i + pattern.length)) { //Не уверен читерно это или нет поскольку сравнение строк в Kotlin является эквивалентом str1.equals(str2) в Java
            count++
        }
    }
    return count
}
