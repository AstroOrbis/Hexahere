package hexahere.processor

import com.astroorbis.hexahere.annotations.HexPatternCategory
import com.google.devtools.ksp.processing.*

const val genned_header = """
// Autogenned by HexaHere's symbol processor for no reason other than it's mildly more convenient 
// also it keeps docs synced lol
// don't edit ig?

"""

fun generatePatchouli(
    codeGenerator: CodeGenerator,
    patterns: List<PatternInfo>,
    category: HexPatternCategory
) {
    val entries = patterns.filter { it.category == category }

    if (entries.isEmpty()) return

    val file = codeGenerator.createNewFile(
        Dependencies(false),
        "",
        "assets/hexcasting/patchouli_books/thehexbook/en_us/entries/patterns/${category.id}",
        "json5"
    )

    file.writer().use { w ->
        w.write(genned_header)
        w.write("{\n")
        w.write("  \"name\":\"hexahere.book.${category.id}\",\n")
        w.write("  \"category\":\"hexcasting:patterns/spells\",\n")
        w.write("  \"icon\":\"minecraft:bedrock\",\n")
        w.write("  \"advancement\":\"hexcasting:root\",\n")
        w.write("  \"pages\":[\n")

        entries.forEachIndexed { i, p ->
            w.write("    {\n")
            w.write("      \"type\":\"hexcasting:pattern\",\n")
            w.write("      \"op_id\":\"hexahere:${p.id}\",\n")
            w.write("      \"anchor\":\"hexahere:${p.id}\",\n")
            w.write("      \"input\":\"${p.input}\",\n")
            w.write("      \"output\":\"${p.output}\",\n")
            w.write("      \"text\":\"hexahere.book.$category.${p.id}\"\n")
            w.write("    }")
            if (i != entries.lastIndex) w.write(",")
            w.write("\n")
        }

        w.write("  ]\n")
        w.write("}\n")
    }
}

private fun json5String(value: String): String {
    return buildString {
        for (ch in value) {
            when (ch) {
                '\\' -> append("\\\\")
                '"' -> append("\\\"")
                '\n' -> append("\\n")
                '\r' -> append("\\r")
                '\t' -> append("\\t")
                else -> append(ch)
            }
        }
    }
}

private fun generateBookSection(patterns: List<PatternInfo>): String {
    val byCategory = patterns.groupBy { it.category }
        .toSortedMap()

    return buildString {
        for ((category, entries) in byCategory) {
            append("      ")
            append(category)
            append(": {\n")

            val displayName = entries.firstOrNull()?.category?.displayName?.takeIf { it.isNotBlank() }
                ?: "hexahere.book.$category"

            append("        \"\": \"")
            append(json5String(displayName))
            append("\",\n")

            for (p in entries.sortedBy { it.id }) {
                append("        ")
                append(p.id)
                append(": \"")
                append(json5String(p.description))
                append("\",\n")
            }

            append("      },\n")
        }
    }
}

private fun generateActionNamesSection(patterns: List<PatternInfo>): String {
    // id: name
    return buildString {
        for (p in patterns.sortedBy { it.id }) {
            append("        ")
            append(p.id)
            append(": \"")
            append(json5String(p.name))
            append("\",\n")
        }
    }
}

fun generateLang(
    codeGenerator: CodeGenerator,
    patterns: List<PatternInfo>
) {
    val file = codeGenerator.createNewFile(
        Dependencies(false),
        "",
        "assets/hexahere/lang/en_us.flatten",
        "json5"
    )

    file.writer().use { w ->
        w.write(genned_header + """
{
  hexahere: {
    book: {
      HEXAHERE_PROCESSOR_BOOK
    },
  },

  hexcasting: {
    action: {
      "hexahere:": {
        HEXAHERE_PROCESSOR_ACTIONS
      },
    },
  },

  text: {
    hexahere: {
      cartesian: "Idk where this text shows up but I guess I'll figure it out"
    },

    "autoconfig.hexahere": {
      title: "Hexahere Config",

      category: {
        client: "Client",
        server: "Server",
      },

      option: {
        client: {
          dummyClientConfigOption: {
            "": "Dummy Client Config Option",
            "@Tooltip": "This is an example of a client-side config option that accepts a boolean.",
          },
        },

        server: {
          dummyServerConfigOption: {
            "": "Dummy Server Config Option",
            "@Tooltip": "This is an example of a server-side config option that accepts an integer.",
          },
        },
      },
    },
  },
}
""".replace("HEXAHERE_PROCESSOR_BOOK", generateBookSection(patterns)).replace("HEXAHERE_PROCESSOR_ACTIONS", generateActionNamesSection(patterns)))
    }
}
