fun main(args: Array<String>) {

    val input = readLine()!!
    val k = input.split(" ")[0].toInt()
    val t = input.split(" ")[1].toInt()
    val dna = arrayOf(readLine()!!, readLine()!!, readLine()!!, readLine()!!, readLine()!!)

    val out = greedyMotifSearch(k, t, dna)
    for (i in out!!.indices) {
        println(out[i])
    }
}

fun greedyMotifSearch(k: Int, t: Int, dna: Array<String>): Array<String?>? { //Array<String?>? когда я уже ни во что не верю и ничего не жду...
    var bestMotifs: Array<String?>? = null
    var bestScore = -1
    for (m in 0..dna[0].length - k) {
        val motifs = arrayOfNulls<String>(t)
        motifs[0] = dna[0].substring(m, m + k)
        for (i in 1 until t) {
            val prevMotifs = arrayOfNulls<String>(i)
            for (x in prevMotifs.indices) {
                prevMotifs[x] = motifs[x]
            }
            val profile = profileFromMotifs(prevMotifs)

            motifs[i] = profileMostProbableKmer(dna[i], k, profile)
        }
        val currScore = scoreMotifs(motifs)
        if (currScore > bestScore) {
            bestScore = currScore
            bestMotifs = motifs
        }
    }
    return bestMotifs
}

fun profileFromMotifs(motifs: Array<String?>): Array<DoubleArray> {
    val profile = Array(4) { DoubleArray(motifs[0]!!.length) }
    for (seq in motifs.indices) {
        val curr = motifs[seq]
        for (i in 0 until curr!!.length) {
            val c = curr[i]
            ++profile[nucleoToInt(c)][i]
        }
    }
    for (i in profile.indices) {
        for (j in 0 until profile[0].size) {
            profile[i][j] = profile[i][j] / motifs.size.toDouble()
        }
    }
    return profile
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

fun scoreMotifs(motifs: Array<String?>): Int {
    val profile = profileFromMotifs(motifs)
    var consensus = ""
    for (i in 0 until motifs[0]!!.length) {
        var maxProb = -1.0
        var maxChar = '\u0000'
        for (j in 0..3) {
            if (profile[j][i] > maxProb) {
                maxProb = profile[j][i]
                maxChar = intToNucleo(j)
            }
        }
        consensus += maxChar
    }

    var score = 0
    for (i in 0 until consensus.length) {
        val c = consensus[i]
        for (j in motifs.indices) {
            if (motifs[j]!![i] == c) {
                ++score
            }
        }
    }
    return score
}

fun intToNucleo(i: Int): Char {
    return when (i) {
        0 -> 'A'
        1 -> 'C'
        2 -> 'G'
        3 -> 'T'
        else -> '\u0000'
    }
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
