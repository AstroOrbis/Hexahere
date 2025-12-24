package com.astroorbis.hexahere.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.getList
import at.petrak.hexcasting.api.casting.iota.ListIota
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import net.minecraft.world.entity.Entity

object OpCartesianII : ConstMediaAction {
    override val argc = 3

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val A = args.getList(0, argc)
        val B = args.getList(1, argc);
        val C = args.getList(1, argc);

        val out: MutableList<Iota> = mutableListOf();

        for (i: Iota in A) {
            for (j: Iota in B) {
                for (k: Iota in C) {
                    out.add(ListIota(listOf(i,j,k)))
                }
            }
        }

        return out
    }
}
