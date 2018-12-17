val peptideMasses = intArrayOf(57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186)

fun main(args: Array<String>) {
    val mass = readLine()!!.toInt()
    print(count(mass))
}

fun count(mass: Int): Long {
    val masses = LongArray(mass + 1)

    masses[0] = 1

    for (i in 0..mass) {
        for (j in 0 until peptideMasses.size) {
            if (i >= peptideMasses[j]) {
                masses[i] += masses[i - peptideMasses[j]]
            }
        }
    }

    return masses[mass]
}
