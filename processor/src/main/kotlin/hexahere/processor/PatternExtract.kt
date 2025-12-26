package hexahere.processor

import com.astroorbis.hexahere.annotations.HexPatternCategory
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration

data class PatternInfo(
    val id: String,
    val name: String,
    val description: String,
    val category: HexPatternCategory,
    val input: String,
    val output: String
)

fun KSClassDeclaration.toPatternInfo(): PatternInfo? {
    val annotation = annotations.find {
        it.annotationType.resolve().declaration.qualifiedName?.asString() ==
            "com.astroorbis.hexahere.annotations.HexPattern"
    } ?: return null

    val (categoryId, categoryDisplayName) = annotation.getCategoryInfo()

    return PatternInfo(
        id = annotation.getArgument("id"),
        name = annotation.getArgument("name"),
        description = annotation.getArgument("description"),
        category = annotation.getArgument("category").let {
            HexPatternCategory.values().find { cat -> cat.id == categoryId }
                ?: HexPatternCategory.MISC
        },
        input = annotation.getArgument("input"),
        output = annotation.getArgument("output")
    )
}

fun KSPropertyDeclaration.toPatternInfo(): PatternInfo? {
    val annotation = annotations.find {
        it.annotationType.resolve().declaration.qualifiedName?.asString() ==
            "com.astroorbis.hexahere.annotations.HexPattern"
    } ?: return null

    val (categoryId, categoryDisplayName) = annotation.getCategoryInfo()

    return PatternInfo(
        id = annotation.getArgument("id"),
        name = annotation.getArgument("name"),
        description = annotation.getArgument("description"),
        category = annotation.getArgument("category").let {
            HexPatternCategory.values().find { cat -> cat.id == categoryId }
                ?: HexPatternCategory.MISC
        },
        input = annotation.getArgument("input"),
        output = annotation.getArgument("output")
    )
}

private fun KSAnnotation.getArgument(name: String): String {
    return arguments.find { it.name?.asString() == name }?.value?.toString() ?: ""
}

private fun KSAnnotation.getCategoryInfo(): Pair<String, String> {
    val rawCategory = getArgument("category")
    // could be full path or just the name
    val enumConstant = rawCategory.substringAfterLast('.')

    val id = enumConstant.lowercase()

    return id to "hexahere.book.$id"
}
