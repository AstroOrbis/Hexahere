package com.astroorbis.hexahere.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import com.astroorbis.hexahere.annotations.HexPatternCategory
import com.astroorbis.hexahere.casting.actions.spells.*
import net.minecraft.world.phys.Vec3
import com.astroorbis.hexahere.annotations.HexPattern as HexPatternAnnotation

object HexahereActions : HexahereRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    // Constants
    @HexPatternAnnotation(
        id = "orthovec",
        name = "Orthogonal Vector",
        description = "Returns the vector (-1, 0, 1)",
        category = HexPatternCategory.MATH,
        input = "",
        output = "vec"
    )
    val OTRHOVEC = make("orthovec", HexDir.NORTH_WEST, "qdeddw", Action.makeConstantOp(Vec3Iota(Vec3(-1.0, 0.0, 1.0))))

    // Patterns
    val CARTESIAN = make("cartesian", HexDir.NORTH_WEST, "wdwdawdew", OpCartesian)
    val CARTESIAN3 = make("cartesian3", HexDir.NORTH_WEST, "wdwdawddae", OpCartesian3)

    val VEC2LIST = make("vec2list", HexDir.NORTH_WEST, "waqq", OpVecToList)
    val LIST2VEC = make("list2vec", HexDir.NORTH_WEST, "wdee", OpListToVec)

    val INTRANGE = make("intrange", HexDir.NORTH_WEST, "aqadaqa", OpIntRange)


    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
