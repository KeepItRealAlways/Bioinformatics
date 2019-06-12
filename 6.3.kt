fun main(args: Array<String>) {

    val SeqOne = readLine()!!// the upper sequence;
    val SeqTwo = readLine()!! // the left sequence;

    val row = SeqTwo.length // the length of SeqTwo;
    val col = SeqOne.length // the length of SeqOne;

    var matchMatrix = Array(row) { IntArray(col) }
    matchMatrix = buildMatchMatrix(SeqOne, SeqTwo) // build matchMatrix base on two sequences;

    var scoreMatrix = Array(row + 1) { IntArray(col + 1) }
    scoreMatrix = buildScoreMatrix(matchMatrix, row, col)

    var backTractMatrix = Array(row + 1) { CharArray(col + 1) }
    backTractMatrix = buildBackTractMatrix(scoreMatrix, row + 1, col + 1)

    printLCS(backTractMatrix, SeqTwo, row, col)
}


private fun printLCS(backTractMatrix: Array<CharArray>, seqTwo: String, Row: Int, Col: Int) {
    if (Row == 0 || Col == 0)
        return

    if (backTractMatrix[Row][Col] == 'O') {

        printLCS(backTractMatrix, seqTwo, Row - 1, Col - 1)
        print("" + seqTwo[Row - 1])
    } else if (backTractMatrix[Row][Col] == 'U') {

        printLCS(backTractMatrix, seqTwo, Row - 1, Col)
    } else if (backTractMatrix[Row][Col] == 'L') {

        printLCS(backTractMatrix, seqTwo, Row, Col - 1)
    }


}


private fun buildBackTractMatrix(scoreMatrix: Array<IntArray>, Row: Int, Col: Int): Array<CharArray> {
    val BTMatrix = Array(Row) { CharArray(Col) }

    for (i in 0 until Row) {
        BTMatrix[i][0] = 'U'
    }

    for (j in 0 until Col) {
        BTMatrix[0][j] = 'L'
    }

    for (i in 1 until Row) {
        for (j in 1 until Col) {
            if (scoreMatrix[i][j] == scoreMatrix[i - 1][j]) {
                BTMatrix[i][j] = 'U'
            } else if (scoreMatrix[i][j] == scoreMatrix[i][j - 1]) {
                BTMatrix[i][j] = 'L'
            } else if (scoreMatrix[i][j] == scoreMatrix[i - 1][j - 1] + 1) {
                BTMatrix[i][j] = 'O'
            }

        }
    }

    return BTMatrix

}


private fun buildScoreMatrix(matchMatrix: Array<IntArray>, Row: Int, Col: Int): Array<IntArray> {
    val newRow = Row + 1
    val newCol = Col + 1
    val newMatrix = Array(newRow) { IntArray(newCol) }

    for (i in 0 until newRow) {
        newMatrix[i][0] = 0
    }
    for (j in 0 until newCol) {
        newMatrix[0][j] = 0
    }

    for (i in 1 until newRow) {
        for (j in 1 until newCol) {
            newMatrix[i][j] = matchMatrix[i - 1][j - 1]

        }
    }

    val scoreMatrix = Array(newRow) { IntArray(newCol) }
    for (i in 0 until newRow) {
        for (j in 0 until newCol) {
            scoreMatrix[i][j] = newMatrix[i][j]

        }
    }

    for (i in 1 until newRow) {

        for (j in 1 until newCol) {

            if (scoreMatrix[i][j] == 1) {
                scoreMatrix[i][j] =
                    maxOfThree(scoreMatrix[i - 1][j], scoreMatrix[i][j - 1], scoreMatrix[i - 1][j - 1] + 1)
            } else {
                scoreMatrix[i][j] = maxOfThree(scoreMatrix[i - 1][j], scoreMatrix[i][j - 1], 0)
            }
        }
    }

    return scoreMatrix
}


private fun maxOfThree(i: Int, j: Int, k: Int): Int {
    var Max = i

    if (j > Max)
        Max = j
    if (k > Max)
        Max = k

    return Max
}

private fun buildMatchMatrix(seqOne: String, seqTwo: String): Array<IntArray> {
    val Row = seqTwo.length
    val Col = seqOne.length
    val MatchMatrix = Array(Row) { IntArray(Col) }
    for (i in 0 until Col) {
        MatchMatrix[0][i] = 0
    }

    for (j in 0 until Row) {
        MatchMatrix[j][0] = 0
    }

    for (i in 0 until Row) {
        for (j in 0 until Col) {
            if (seqOne[j] == seqTwo[i]) {
                MatchMatrix[i][j] = 1
            } else {
                MatchMatrix[i][j] = 0
            }
        }
    }

    return MatchMatrix
}
