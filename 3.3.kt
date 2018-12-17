val massTable = HashMap<String, Int>()
var masses = intArrayOf(57, 71, 87, 97, 99, 101, 103, 113, 114, 115, 128, 129, 131, 137, 147, 156, 163, 186)

fun main(args: Array<String>) {

    fillMassTable()

    val n = readLine()!!.toInt()
    val spectrumList = readLine()!!.split(" ")
    val spectrum = ArrayList<Int>()

    for (item in spectrumList) {
        spectrum.add(item.toInt())
    }

    println(toStringWithDelimiter(leaderBoardSequencing(spectrum, n), "-"))
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

private fun cutLeads(leaderBoard: ArrayList<ArrayList<Int>>,
    n: Int, spectrum: ArrayList<Int>): ArrayList<ArrayList<Int>> {

    if (n > leaderBoard.size)
        return leaderBoard

    leaderBoard.sortWith(Comparator { o1, o2 -> -score(o1, spectrum).compareTo(score(o2, spectrum)) })

    val newLeaderBoard = ArrayList<ArrayList<Int>>()
    val lastScore = score(leaderBoard[n], spectrum)
    for (i in leaderBoard.indices) {
        if (i < n) {
            newLeaderBoard.add(leaderBoard[i])
        } else if (score(leaderBoard[i], spectrum) >= lastScore) {
            newLeaderBoard.add(leaderBoard[i])
        }
    }
    return newLeaderBoard
}

private fun leaderBoardSequencing(spectrum: ArrayList<Int>, n: Int): ArrayList<Int> {
    var leaderBoard = ArrayList<ArrayList<Int>>()
    var leaderPeptide = ArrayList<Int>()
    val parentMass = parentMass(spectrum)

    leaderBoard.add(ArrayList())
    while (!leaderBoard.isEmpty()) {
        leaderBoard = expand(leaderBoard)
        val tempLeaderBoard = ArrayList(leaderBoard)
        for (peptide in leaderBoard) {
            val mass = mass(peptide)
            if (mass == parentMass) {
                if (score(peptide, spectrum) > score(leaderPeptide, spectrum)) {
                    leaderPeptide = peptide
                }
            } else if (mass > parentMass) {
                tempLeaderBoard.remove(peptide)
            }
        }
        leaderBoard = cutLeads(tempLeaderBoard, n, spectrum)
    }

    return leaderPeptide
}

fun mass(peptide: List<Int>): Int {
    var result = 0

    for (integer in peptide) {
        result += integer
    }

    return result
}

private fun parentMass(spectrum: ArrayList<Int>): Int {
    var max = 0

    for (integer in spectrum) {
        max = if (max > integer) max else integer
    }

    return max
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

private fun toStringWithDelimiter(peptide: ArrayList<Int>, delimiter: String): String {
    val stringBuilder = StringBuilder()
    for (integer in peptide) {
        stringBuilder.append(integer)
        stringBuilder.append(delimiter)
    }
    val stringWithDelimiter = stringBuilder.toString()
    return stringWithDelimiter.substring(0, stringWithDelimiter.length - 1)
}
