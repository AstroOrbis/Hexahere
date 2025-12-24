package com.astroorbis.hexahere.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import com.astroorbis.hexahere.casting.actions.spells.OpCartesian
import com.astroorbis.hexahere.casting.actions.spells.OpCartesian2
import com.astroorbis.hexahere.casting.actions.spells.OpListToVec
import com.astroorbis.hexahere.casting.actions.spells.OpVecToList
import net.minecraft.world.phys.Vec3

object HexahereActions : HexahereRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    // Constants
    val OTRHOVEC = make("orthovec", HexDir.NORTH_WEST, "qdeddw", Action.makeConstantOp(Vec3Iota(Vec3(-1.0, 0.0, 1.0))))


    // Patterns
    val CARTESIAN = make("cartesian", HexDir.NORTH_WEST, "wdwdawdew", OpCartesian)
    val CARTESIAN2 = make("cartesian2", HexDir.NORTH_WEST, "wdwdawddae", OpCartesian2)

    val VEC2LIST = make("vec2list", HexDir.NORTH_WEST, "waqq", OpVecToList)
    val LIST2VEC = make("list2vec", HexDir.NORTH_WEST, "wdee", OpListToVec)

    private fun make(name: String, startDir: HexDir, signature: String, action: Action) =
        make(name, startDir, signature) { action }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
