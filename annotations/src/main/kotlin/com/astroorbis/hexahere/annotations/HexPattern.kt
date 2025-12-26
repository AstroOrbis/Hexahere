package com.astroorbis.hexahere.annotations

enum class HexPatternCategory(
    val id: String,
    val displayName: String,
) {
    MISC("misc","HH Misc"),
    MATH("math", "HH Math"),
    TYPES("types", "HH Types"),
}

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class HexPattern(
    val id: String,
    val name: String,
    val description: String,
    val category: HexPatternCategory = HexPatternCategory.MISC,
    val input: String = "",
    val output: String = "",
)

