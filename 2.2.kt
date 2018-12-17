val codonTable = HashMap<String, String>()

fun main(args: Array<String>) {
    fillCodonTable()

    val pattern = DNAToRNA(readLine()!!)
    val sequence = readLine()!!

    findSequnces(pattern, sequence)
}

fun findSequnces(pattern: String, sequence: String) {
    for (i in 0..pattern.length - sequence.length * 3) {
        val chunk = pattern.substring(i, i + sequence.length * 3)
        var peptide = translate(chunk)
        if (peptide == sequence) {
            println(RNAToDNA(chunk))
        }
        peptide = translate(reverse(chunk))
        if (peptide == sequence) {
            println(RNAToDNA(chunk))
        }
    }
}

fun translate(pattern: String): String {
    var peptide = ""
    val chunkedPattern = pattern.chunked(3)

    for (chunk in chunkedPattern) {
        if (codonTable[chunk] != null)
            peptide += codonTable[chunk]
    }

    return peptide
}

fun DNAToRNA(dna: String): String {
    return dna.replace('T', 'U')
}

fun RNAToDNA(rna: String): String {
    return rna.replace('U', 'T')
}

fun reverse(rna: String): String {
    var output = ""

    for (i in 0 until rna.length) {
        when (rna[i]) {
            'A' -> output = "U$output"
            'U' -> output = "A$output"
            'C' -> output = "G$output"
            'G' -> output = "C$output"
        }
    }

    return output
}

fun String.chunked(size: Int): List<String> {
    val nChunks = length / size
    return (0 until nChunks).map { substring(it * size, (it + 1) * size) }
}

fun fillCodonTable() {
    codonTable["AAA"] = "K"
    codonTable["AAC"] = "N"
    codonTable["AAG"] = "K"
    codonTable["AAU"] = "N"
    codonTable["ACA"] = "T"
    codonTable["ACC"] = "T"
    codonTable["ACG"] = "T"
    codonTable["ACU"] = "T"
    codonTable["AGA"] = "R"
    codonTable["AGC"] = "S"
    codonTable["AGG"] = "R"
    codonTable["AGU"] = "S"
    codonTable["AUA"] = "I"
    codonTable["AUC"] = "I"
    codonTable["AUG"] = "M"
    codonTable["AUU"] = "I"
    codonTable["CAA"] = "Q"
    codonTable["CAC"] = "H"
    codonTable["CAG"] = "Q"
    codonTable["CAU"] = "H"
    codonTable["CCA"] = "P"
    codonTable["CCC"] = "P"
    codonTable["CCG"] = "P"
    codonTable["CCU"] = "P"
    codonTable["CGA"] = "R"
    codonTable["CGC"] = "R"
    codonTable["CGG"] = "R"
    codonTable["CGU"] = "R"
    codonTable["CUA"] = "L"
    codonTable["CUC"] = "L"
    codonTable["CUG"] = "L"
    codonTable["CUU"] = "L"
    codonTable["GAA"] = "E"
    codonTable["GAC"] = "D"
    codonTable["GAG"] = "E"
    codonTable["GAU"] = "D"
    codonTable["GCA"] = "A"
    codonTable["GCC"] = "A"
    codonTable["GCG"] = "A"
    codonTable["GCU"] = "A"
    codonTable["GGA"] = "G"
    codonTable["GGC"] = "G"
    codonTable["GGG"] = "G"
    codonTable["GGU"] = "G"
    codonTable["GUA"] = "V"
    codonTable["GUC"] = "V"
    codonTable["GUG"] = "V"
    codonTable["GUU"] = "V"
    codonTable["UAA"] = ""
    codonTable["UAC"] = "Y"
    codonTable["UAG"] = ""
    codonTable["UAU"] = "Y"
    codonTable["UCA"] = "S"
    codonTable["UCC"] = "S"
    codonTable["UCG"] = "S"
    codonTable["UCU"] = "S"
    codonTable["UGA"] = ""
    codonTable["UGC"] = "C"
    codonTable["UGG"] = "W"
    codonTable["UGU"] = "C"
    codonTable["UUA"] = "L"
    codonTable["UUC"] = "F"
    codonTable["UUG"] = "L"
    codonTable["UUU"] = "F"
}
