fun main(args: Array<String>) {
    val k = readLine()!!.toInt()
    val dna = ArrayList<String>()
    for (i in 0 until 10) {
        dna.add(readLine()!!)
    }

    println(MedianString(dna, k))
}

fun MedianString(dna: List<String>, k: Int): String {

    var minScore = Int.MAX_VALUE
    var pattern = ""

    val list = listOfPatterns(k)

    //println(dna.size)

    for (item in list) {
        var itemScore = 0
        for (dnaItem in dna) {
            for (d in 0..k) {
                if (find(item, dnaItem, d)) {
                    itemScore += d
                    break
                }
            }
        }
        if (itemScore < minScore) {
            minScore = itemScore
            pattern = item
        }
    }

    return pattern
}

fun compare(str1: String, str2: String, d: Int): Boolean {
    var mismatches = 0
    //print("Comparing $str1 $str2 ")
    for (i in 0 until str1.length) {
        if (str1[i] != str2[i]) {
            mismatches++
        }
        if (mismatches > d) {
            //println ("No")
            return false
        }
    }

    //println("Yes")

    return true
}

fun listOfPatterns(size: Int): List<String> {
    val out = ArrayList<String>()

    val from = ArrayList<Char>(size)
    var to = ""

    for (i in 0 until size) {
        from.add('A')
        to += "T"
    }

    while (render(from) != to) {
        out.add(render(from))
        increase(from, from.size - 1)
    }
    out.add(render(from))

    return out
}

fun increase(pattern: ArrayList<Char>, position: Int) {
    when {
        pattern[position] == 'A' -> pattern[position] = 'C'
        pattern[position] == 'C' -> pattern[position] = 'G'
        pattern[position] == 'G' -> pattern[position] = 'T'
        pattern[position] == 'T' -> {
            pattern[position] = 'A'
            increase(pattern, position - 1)
        }
    }
}

fun render(source: List<Char>): String {
    var out = ""
    for (char in source) {
        out += char
    }
    return out
}

fun appears(what: String, where: List<String>, d: Int): Boolean {

    //println("Checking for $what")

    for (str in where) {
        if (!find(what, str, d))
            return false
    }

    return true
}

fun find(what: String, where: String, d: Int): Boolean {
    for (i in 0..where.length-what.length) {
        if (compare(what, where.substring(i until i+what.length), d))
            return true
    }

    return false
}
