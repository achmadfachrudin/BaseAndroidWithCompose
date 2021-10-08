import java.util.*

fun printSubStr(
    str: String,
    low: Int,
    high: Int
) {
    println(
        str.substring(
            low, high + 1
        )
    )
}

println("longestPalSubstr ${longestPalSubstr("forgeeksskeegfor")}")
fun longestPalSubstr(str: String): Int {
    val n = str.length
    val table = Array(n) { BooleanArray(n) }

    var maxLength = 1
    for (i in 0 until n) table[i][i] = true

    var start = 0
    for (i in 0 until n - 1) {
        if (str[i] == str[i + 1]) {
            table[i][i + 1] = true
            start = i
            maxLength = 2
        }
    }

    for (k in 3..n) {
        for (i in 0 until n - k + 1) {
            val j = i + k - 1

            if (table[i + 1][j - 1] && str[i] == str[j]) {
                table[i][j] = true
                if (k > maxLength) {
                    start = i
                    maxLength = k
                }
            }
        }
    }

    print("Longest palindrome substring is; ")
    printSubStr(
        str,
        start,
        start + maxLength - 1
    )

    return maxLength
}

//println("QuestionsMarks ${QuestionsMarks("arrb6???4xxbl5???eee5")}")
fun QuestionsMarks(str: String): String {
    var result = "false"
    var questionCount = 0
    var lastDigit = -1

    str.forEach {
        if (it.isDigit()) {
            val digit = it.digitToInt()
            val sum = digit + lastDigit
            lastDigit = digit

            if (sum != 10) {
                questionCount = 0
                return@forEach
            }

            result = if (questionCount == 3) "true" else "false"
            questionCount = 0
        } else if (it == '?') questionCount++
    }

    return result
}

// println("minWindowSubstring ${minWindowSubstring(arrayOf("aaabaaddae", "aed"))}")
fun minWindowSubstring(strArr: Array<String>): String {
    val n = strArr[0]
    val k = strArr[1]

    for (size in k.length..n.length) {
        for (j in 0..n.length - size) {
            val save = n.substring(j, j + size)
            if (isContained(save, k)) return save
        }
    }
    return "Not found"
}

fun isContained(save: String, k: String): Boolean {
    val sb = StringBuilder(save)
    for (i in k.indices) {
        val place = sb.indexOfFirst { c -> c == k[i] }
        if (place == -1) {
            return false
        } else {
            sb.delete(place, place + 1)
        }
    }
    return true
}

// println("longestWord ${longestWord("hello wording siapa")}")
fun longestWord(sen: String): String {
    var theLongestSize = 0
    var theLongestWord = ""
    val strList = sen.split(" ")

    strList.forEach {
        if (it.toCharArray().size > theLongestSize) {
            theLongestSize = it.toCharArray().size
            theLongestWord = it
        }
    }

    return theLongestWord
}

// println("palindrome ${palindrome("racecar")}")
fun palindrome(word: String): Boolean {
    val cleanWord = word.replace(" ", "").lowercase()
    val reverseWord = cleanWord.reversed()

    return cleanWord == reverseWord
}

// println(treeConstructor(arrayOf("(1,2)", "(2,4)", "(7,2)")))
fun treeConstructor(strArr: Array<String>): String {
    if (strArr.size == 1) {
        return "true"
    }

    val map = mutableMapOf<Int, MutableList<Int>>()
    val set = mutableSetOf<Int>()
    val values = mutableSetOf<Int>()

    strArr.forEach {
        val split = it.replace("(", "")
            .replace(")", "")
            .split(",")
        val node = split[0].toInt()
        val parent = split[1].toInt()

        set.add(node)
        values.add(parent)

        if (map.containsKey(parent)) {
            map[parent]!!.add(node)
            if (map[parent]!!.size > 2) {
                return "false"
            }
        } else {
            map[parent] = mutableListOf(node)
        }
    }
    val nodesCount = set.size
    val roots = values.minus(set)
    return if ((nodesCount == strArr.size && roots.size == 1)) "true" else "false"
}

// println("bracketCombinations ${bracketCombinations(3)}")
fun bracketCombinations(num: Int): Int {
    return factorial(2 * num) / (factorial(num + 1) * factorial(num))
}

// println("factorial ${factorial(0)}")
fun factorial(num: Int): Int {
    return if (num == 1 || num == 0) 1
    else num * factorial(num - 1)
}

// bracketMatcher("the color re(d))()((")
fun bracketMatcher(str: String): Int {
    val open = str.replace("(", "")
    val close = str.replace(")", "")

    val result = if (open.length == close.length) 1 else 0
    println("result $result")

    return result
}

// ## Input: [3, 5, 2, -4, 8, 11] and the sum = 7
// ## Output: [[11, -4], [2, 5]]
fun getSum() {
    val params = mutableListOf(3, 5, 2, -4, 8, 11, 4, 4, 4, 0, 4, 4)

    val result = mutableListOf<List<Int>>()

    params.forEach {
        val spouse = 8 - it
        if (spouse in params) {
            val intList = listOf(it, spouse).sorted()
            if (intList !in result) result.add(intList)
        }
    }

    println("result is: $result")
}

val brackets = mapOf(
    '{' to '}',
    '(' to ')',
    '[' to ']'
)

// println("bracketValid ${bracketValid("{{}}")}")
fun bracketValid(input: CharSequence): Boolean {
    if (input.isEmpty()) return true

    val stack = Stack<Char>()

    input.forEach {
        if (brackets.containsKey(it)) {
            stack.push(it)
            println("stack $stack")
        } else if (stack.size > 0) {
            println("peek ${stack.peek()}")
            if (it == brackets[stack.peek()]) stack.pop()
        }
    }
    return stack.isEmpty()
}
