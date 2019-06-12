fun main() {

    val text = readLine()!!
    val k = readLine()!!.toInt()
    val profileRow1 = readLine()!!
    val profileRow2 = readLine()!!
    val profileRow3 = readLine()!!
    val profileRow4 = readLine()!!

    val p1 = profileRow1.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val p2 = profileRow2.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val p3 = profileRow3.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val p4 = profileRow4.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val profile = Array(4) { DoubleArray(k) }
    for (i in 0 until k) {
        profile[0][i] = java.lang.Double.parseDouble(p1[i])
        profile[1][i] = java.lang.Double.parseDouble(p2[i])
        profile[2][i] = java.lang.Double.parseDouble(p3[i])
        profile[3][i] = java.lang.Double.parseDouble(p4[i])
    }
    println(profileMostProbableKmer(text, k, profile))
}

fun profileMostProbableKmer(sequence: String, k: Int, profile: Array<DoubleArray>): String? {
    var best: String? = null
    var bestP = -1.0
    for (i in 0..sequence.length - k) {
        val sub = sequence.substring(i, i + k).toUpperCase()
        var prob = 1.0
        for (j in 0 until sub.length) {
            prob *= profile[nucleoToInt(sub[j])][j]
        }
        if (prob > bestP) {
            best = sub
            bestP = prob
        }
    }
    return best
}

fun nucleoToInt(n: Char): Int {

    return when (n) {
        'A' -> 0
        'C' -> 1
        'G' -> 2
        'T' -> 3
        else -> -1
    }
}
