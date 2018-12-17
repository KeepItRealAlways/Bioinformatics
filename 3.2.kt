val massTable = HashMap<String, Int>()

fun main(args: Array<String>) {

    fillMassTable()

    val peptide = readLine()!!
    val peptideMasses = ArrayList<Int>()

    for (i in 0 until peptide.length) {
        peptideMasses.add(massTable[peptide[i].toString()]!!)
    }

    val input = readLine()!!.split(" ")

    val spectrum = ArrayList<Int>()

    for (item in input) {
        spectrum.add(item.toInt())
    }

    println(score(peptideMasses, spectrum))
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

private fun score(peptide: ArrayList<Int>, spectrum: ArrayList<Int>): Int {
    val experimentalSpectrum = ArrayList(spectrum)
    val theoreticalSpectrum = getSpectrum(peptide, false)
    var scope = 0
    for (integer in theoreticalSpectrum) {
        if (experimentalSpectrum.contains(integer)) {
            scope++
            experimentalSpectrum.remove(integer)
        }
    }
    return scope
}

private fun getSpectrum(candidate: ArrayList<Int>, isLinear: Boolean): ArrayList<Int> {
    val subPeptides = if (isLinear) getLinearSubPeptides(candidate) else getSubPeptides(candidate)
    val spectrum = ArrayList<Int>()
    spectrum.add(0)
    for (subPeptide in subPeptides) {
        var sumMass = 0
        for (mass in subPeptide)
            sumMass += mass
        spectrum.add(sumMass)
    }
    spectrum.sort()
    return spectrum
}

private fun getSubPeptides(candidate: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    val subPeptides = ArrayList<ArrayList<Int>>()
    subPeptides.add(candidate)
    val extendedCandidate = ArrayList(candidate)
    extendedCandidate.addAll(candidate.subList(0, if (candidate.size > 2) candidate.size - 2 else 0))
    for (i in candidate.indices) {
        for (j in 0 until candidate.size - 1) {
            subPeptides.add(ArrayList(extendedCandidate.subList(i, i + j + 1)))
        }
    }
    return subPeptides
}

private fun getLinearSubPeptides(candidate: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    val subPeptides = ArrayList<ArrayList<Int>>()
    for (i in candidate.indices) {
        for (j in 0 until candidate.size - i) {
            subPeptides.add(ArrayList(candidate.subList(i, i + j + 1)))
        }
    }
    return subPeptides
}
