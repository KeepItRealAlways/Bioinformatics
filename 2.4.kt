val massTable = HashMap<String, Int>()

fun main(args: Array<String>) {
    fillMassTable()

    val peptide = readLine()!!

    val spectrum = spectrum(peptide)

    for (item in spectrum) {
        print(item)
        print(" ")
    }
}

fun mass(peptide: String): Int {
    var result = 0
    val chunkedPeptide = peptide.chunked(1)

    for (chunk in chunkedPeptide) {
        result += massTable[chunk]!!

    }

    return result
}

fun spectrum(peptide: String): List<Int> {
    val spectrum = ArrayList<Int>()

    spectrum.add(0)
    spectrum.add(mass(peptide))

    val repeatedPeptide = peptide + peptide

    for (i in 1 until peptide.length) {
        for (j in 0 until peptide.length) {
            val subpeptide = repeatedPeptide.substring(j, j+i)
            spectrum.add(mass(subpeptide))
        }
    }

    spectrum.sort()

    return spectrum
}

fun String.chunked(size: Int): List<String> {
    val nChunks = length / size
    return (0 until nChunks).map { substring(it * size, (it + 1) * size) }
}

fun fillMassTable() {
    massTable["G"] = 57
    massTable["A"] = 71
    massTable["S"] = 87
    massTable["P"] = 97
    massTable["V"] = 99
    massTable["T"] = 101
    massTable["C"] = 103
    massTable["I"] = 113
    massTable["L"] = 113
    massTable["N"] = 114
    massTable["D"] = 115
    massTable["K"] = 128
    massTable["Q"] = 128
    massTable["E"] = 129
    massTable["M"] = 131
    massTable["H"] = 137
    massTable["F"] = 147
    massTable["R"] = 156
    massTable["Y"] = 163
    massTable["W"] = 186
}
