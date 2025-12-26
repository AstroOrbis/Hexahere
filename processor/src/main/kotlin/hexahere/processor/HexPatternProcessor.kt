package hexahere.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*

class HexPatternProcessor(
    private val env: SymbolProcessorEnvironment
) : SymbolProcessor {
    private var processed = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (processed) return emptyList()

        val classPatterns = resolver
            .getSymbolsWithAnnotation("com.astroorbis.hexahere.annotations.HexPattern")
            .filterIsInstance<KSClassDeclaration>()
            .mapNotNull { it.toPatternInfo() }

        val propertyPatterns = resolver
            .getSymbolsWithAnnotation("com.astroorbis.hexahere.annotations.HexPattern")
            .filterIsInstance<KSPropertyDeclaration>()
            .mapNotNull { it.toPatternInfo() }

        val patterns = (classPatterns + propertyPatterns).toList()

        if (patterns.isEmpty()) return emptyList()

        processed = true

        patterns
            .map { it.category }
            .distinct()
            .sorted()
            .forEach { category ->
                generatePatchouli(env.codeGenerator, patterns, category)
            }

        generateLang(env.codeGenerator, patterns)

        return emptyList()
    }
}
