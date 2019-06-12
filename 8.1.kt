import java.util.Arrays

fun main(args: Array<String>) {

    val subLen = readLine()!!.toInt()
    val OriStr = readLine()!!

    val oriLen = OriStr.length
    val subStrings = arrayOfNulls<String>(oriLen - subLen + 1)

    for (i in 0..oriLen - subLen) {
        subStrings[i] = OriStr.substring(i, i + subLen)
    }

    Arrays.sort(subStrings)

    for (i in 0..oriLen - subLen) {
        println(subStrings[i])
    }
}
