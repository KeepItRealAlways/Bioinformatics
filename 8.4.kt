import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val subLen = readLine()!!.toInt()
    val OriSeq = readLine()!!

    val Len = OriSeq.length

    var subSeqs = ArrayList<String>(Len - subLen + 1)

    for (i in 0..Len - subLen) {
        val frontHalf = OriSeq.substring(i, i + subLen - 1)
        val backHalf = OriSeq.substring(i + 1, i + subLen)

        subSeqs.add("$frontHalf -> $backHalf")
    }

    subSeqs.sort()

    subSeqs = combineSequences(subSeqs, subLen - 1)

    for (i in 0..Len - subLen) {
        if (subSeqs[i][0] != ' ') {
            println(subSeqs[i])
        }
    }
}

private fun combineSequences(subSeqs: ArrayList<String>, subLen: Int): ArrayList<String> {
    val Len = subSeqs.size
    for (i in 1 until Len) {
        val aftStr = subSeqs[i].substring(0, subLen)

        for (j in 0 until i) {
            val befStr = subSeqs[j].substring(0, subLen)

            if (befStr == aftStr) {
                subSeqs[j] += "," + subSeqs[i].substring(subLen + 4)
                subSeqs[i] = "    " + subSeqs[i]
            }
        }
    }
    return subSeqs
}
