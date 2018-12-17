val codonTable = HashMap<String, String>()

fun main(args: Array<String>) {
    fillCodonTable()
    println(translate(readLine()!!))
}

fun translate(pattern: String): String {
    var peptide = ""
    val chunkedPattern = pattern.chunked(3)
    
    for (chunk in chunkedPattern) {
        peptide = peptide + codonTable[chunk]
    }
    
    return peptide
}

fun String.chunked(size: Int): List<String> {
    val nChunks = length / size
    return (0 until nChunks).map { substring(it * size, (it + 1) * size) }
}

fun fillCodonTable() {
    codonTable.put("AAA", "K")
    codonTable.put("AAC", "N")
    codonTable.put("AAG", "K")
    codonTable.put("AAU", "N")
    codonTable.put("ACA", "T")
    codonTable.put("ACC", "T")
    codonTable.put("ACG", "T")
    codonTable.put("ACU", "T")
    codonTable.put("AGA", "R")
    codonTable.put("AGC", "S")
    codonTable.put("AGG", "R")
    codonTable.put("AGU", "S")
    codonTable.put("AUA", "I")
    codonTable.put("AUC", "I")
    codonTable.put("AUG", "M")
    codonTable.put("AUU", "I")
    codonTable.put("CAA", "Q")
    codonTable.put("CAC", "H")
    codonTable.put("CAG", "Q")
    codonTable.put("CAU", "H")
    codonTable.put("CCA", "P")
    codonTable.put("CCC", "P")
    codonTable.put("CCG", "P")
    codonTable.put("CCU", "P")
    codonTable.put("CGA", "R")
    codonTable.put("CGC", "R")
    codonTable.put("CGG", "R")
    codonTable.put("CGU", "R")
    codonTable.put("CUA", "L")
    codonTable.put("CUC", "L")
    codonTable.put("CUG", "L")
    codonTable.put("CUU", "L")
    codonTable.put("GAA", "E")
    codonTable.put("GAC", "D")
    codonTable.put("GAG", "E")
    codonTable.put("GAU", "D")
    codonTable.put("GCA", "A")
    codonTable.put("GCC", "A")
    codonTable.put("GCG", "A")
    codonTable.put("GCU", "A")
    codonTable.put("GGA", "G")
    codonTable.put("GGC", "G")
    codonTable.put("GGG", "G")
    codonTable.put("GGU", "G")
    codonTable.put("GUA", "V")
    codonTable.put("GUC", "V")
    codonTable.put("GUG", "V")
    codonTable.put("GUU", "V")
    codonTable.put("UAA", "")
    codonTable.put("UAC", "Y")
    codonTable.put("UAG", "") 
    codonTable.put("UAU", "Y")
    codonTable.put("UCA", "S")
    codonTable.put("UCC", "S")
    codonTable.put("UCG", "S")
    codonTable.put("UCU", "S")
    codonTable.put("UGA", "")
    codonTable.put("UGC", "C")
    codonTable.put("UGG", "W")
    codonTable.put("UGU", "C")
    codonTable.put("UUA", "L")
    codonTable.put("UUC", "F")
    codonTable.put("UUG", "L")
    codonTable.put("UUU", "F")
}
