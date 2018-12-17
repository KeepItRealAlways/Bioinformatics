fun main(args: Array<String>) {
    val frequentWords = FrequentWords(readLine()!!, readLine()!!.toInt())
    for (i in 0 until frequentWords.size) {
        print("${frequentWords[i]} ")
    }
}

fun FrequentWords(genome: String, k: Int): List<String> {
    val frequentWords = ArrayList<String>()
    var record = 0
    
    for (i in 0..genome.length - k) {
        val pattern = genome.substring(i, i + k)
        if (!IsDuplicate(pattern, frequentWords)) {
            val score = PatternCount(pattern, genome)
            if (score > record) {
                frequentWords.clear()
                frequentWords.add(pattern)
                record = score
            } else if (score == record) {
                frequentWords.add(pattern)
            }
        }
    }
    
    return frequentWords
}

fun IsDuplicate(pattern: String, list: List<String>): Boolean {
    for (i in 0 until list.size) {
        if (pattern == list[i])
            return true
    }
    
    return false
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
