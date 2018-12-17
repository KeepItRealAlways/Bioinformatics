var masses =
    intArrayOf(57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186)

fun main(args: Array<String>) {
    val input = readLine()!!.split(" ")

    val spectrum = ArrayList<Int>()

    for (item in input) {
        spectrum.add(item.toInt())
    }

    val result = sequencing(spectrum)

    for (peptide in result) {
        print(toStringWithDelimiter(peptide, "-") + " ")
    }
}

private fun toStringWithDelimiter(peptide: ArrayList<Int>, delimiter: String): String {
    val stringBuilder = StringBuilder()
    for (integer in peptide) {
        stringBuilder.append(integer)
        stringBuilder.append(delimiter)
    }
    val stringWithDelimiter = stringBuilder.toString()
    return stringWithDelimiter.substring(0, stringWithDelimiter.length - 1)
}

private fun sequencing(spectrum: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    var candidates = ArrayList<ArrayList<Int>>()
    candidates.add(ArrayList())

    val output = ArrayList<ArrayList<Int>>()

    while (!candidates.isEmpty()) {
        candidates = expand(candidates)
        output.addAll(check(candidates, spectrum))
        candidates = trim(candidates, spectrum)
    }
    return output
}

private fun expand(candidates: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val expandedCandidates = ArrayList<ArrayList<Int>>()
    for (candidate in candidates) {
        for (i in masses.indices) {
            val expandedCandidate = ArrayList(candidate)
            expandedCandidate.add(masses[i])
            expandedCandidates.add(expandedCandidate)
        }
    }
    return expandedCandidates
}

private fun check(candidates: ArrayList<ArrayList<Int>>, spectrum: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    val matchedCandidates = ArrayList<ArrayList<Int>>()
    for (candidate in candidates) {
        if (getSpectrum(candidate, false) == spectrum)
            matchedCandidates.add(candidate)
    }
    return matchedCandidates
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

private fun trim(candidates: ArrayList<ArrayList<Int>>, spectrum: ArrayList<Int>): ArrayList<ArrayList<Int>> {
    val consistentCandidates = ArrayList<ArrayList<Int>>()
    for (candidate in candidates) {
        val linearSpectrum = getSpectrum(candidate, true)
        val givenSpectrum = ArrayList(spectrum)
        var isConsistent = true
        for (mass in linearSpectrum) {
            if (givenSpectrum.contains(mass)) {
                givenSpectrum.remove(mass)
            } else {
                isConsistent = false
                break
            }
        }
        if (isConsistent)
            consistentCandidates.add(candidate)
    }
    return consistentCandidates
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
