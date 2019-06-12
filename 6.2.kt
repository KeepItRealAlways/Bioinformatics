fun main(args: Array<String>) {
    val line = readLine()!!.split(" ")
    val Row = line[0].toInt()
    val Col = line[1].toInt()
    val MatrixDown = Array(Row) { IntArray(Col + 1) }   // the matrix of going down direction;
    val MatrixRight = Array(Row + 1) { IntArray(Col) }  // the matrix of going right direction;

    for (i in 0 until Row) {
        val splitted = readLine()!!.split(" ")
        for (j in 0..Col) {
            MatrixDown[i][j] = splitted[j].toInt()
        }
    }

    val Sepe = readLine()!!

    for (i in 0..Row) {
        val splitted = readLine()!!.split(" ")
        for (j in 0 until Col) {
            MatrixRight[i][j] = splitted[j].toInt()
        }
    }

    val Score = Array(Row + 1) { IntArray(Col + 1) } 

    Score[0][0] = 0

    for (i in 1..Row) {
        Score[i][0] = Score[i - 1][0] + MatrixDown[i - 1][0]
    }

    for (j in 1..Col) {
        Score[0][j] = Score[0][j - 1] + MatrixRight[0][j - 1]
    }

    for (i in 1..Row) {
        for (j in 1..Col) {
            Score[i][j] = Score[i - 1][j] + MatrixDown[i - 1][j]
            if (Score[i][j - 1] + MatrixRight[i][j - 1] > Score[i][j]) {
                Score[i][j] = Score[i][j - 1] + MatrixRight[i][j - 1]
            }
        }
    }

    println(Score[Row][Col])
}
