fun main(args: Array<String>) {
    val pattern = readLine()!!
    var output = ""
    
    for (i in 0 until pattern.length) {
        when (pattern[i]) {
            'A' -> output = 'T' + output
            'T' -> output = 'A' + output
            'C' -> output = 'G' + output
            'G' -> output = 'C' + output
        }
    }
    
    print(output)
}
