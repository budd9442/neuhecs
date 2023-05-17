/*
 * Copyright (C) 2023 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.commands.misc

import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.realmsclient.gui.ChatFormatting
import io.github.moulberry.notenoughupdates.NotEnoughUpdates
import io.github.moulberry.notenoughupdates.autosubscribe.NEUAutoSubscribe
import io.github.moulberry.notenoughupdates.events.RegisterBrigadierCommandEvent
import io.github.moulberry.notenoughupdates.miscfeatures.FairySouls
import io.github.moulberry.notenoughupdates.util.Calculator
import io.github.moulberry.notenoughupdates.util.brigadier.RestArgumentType
import io.github.moulberry.notenoughupdates.util.brigadier.get
import io.github.moulberry.notenoughupdates.util.brigadier.thenArgumentExecute
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import io.github.moulberry.notenoughupdates.util.brigadier.*
import net.minecraft.util.EnumChatFormatting.AQUA
import net.minecraft.util.EnumChatFormatting.WHITE
import net.minecraft.util.EnumChatFormatting.BLUE
import net.minecraft.util.EnumChatFormatting
import net.minecraft.util.EnumChatFormatting.DARK_PURPLE
import net.minecraft.util.EnumChatFormatting.RED
import java.text.DecimalFormat


@NEUAutoSubscribe
class QOLCommands {
    @SubscribeEvent
    fun onCommands(event: RegisterBrigadierCommandEvent) {
        event.command("afk", ) {
            val enable = thenLiteralExecute("on") {
                reply("${DARK_PURPLE}Enabled anti-afk")
                NotEnoughUpdates.INSTANCE.config.fishing.antiAFK=true
            }
            thenLiteral("on") { redirect(enable) }
            val disable = thenLiteralExecute("off") {
                NotEnoughUpdates.INSTANCE.config.fishing.antiAFK=false
                reply("${DARK_PURPLE}Disabled anti-afk")
            }
            thenLiteral("off") { redirect(disable) }
        }
        event.command("af","autofish" ) {
            val enable = thenLiteralExecute("on") {
                reply("${DARK_PURPLE}Enabled auto-fish!")
                NotEnoughUpdates.INSTANCE.config.fishing.autoFishing=true
            }
            thenLiteral("on") { redirect(enable) }
            val disable = thenLiteralExecute("off") {
                NotEnoughUpdates.INSTANCE.config.fishing.autoFishing=false
                reply("${DARK_PURPLE}Disabled auto-fish!")
            }
            thenLiteral("off") { redirect(disable) }
        }
        event.command("lavaesp","lesp" ) {
            val enable = thenLiteralExecute("on") {
                reply("${DARK_PURPLE}Enabled lava ESP!")
                NotEnoughUpdates.INSTANCE.config.esp.lavaESP=true
            }
            thenLiteral("on") { redirect(enable) }
            val disable = thenLiteralExecute("off") {
                NotEnoughUpdates.INSTANCE.config.esp.lavaESP=false
                reply("${DARK_PURPLE}Disabled lava ESP!")
            }
            thenLiteral("off") { redirect(disable) }
        }
        event.command("dev" ) {
            val enable = thenLiteralExecute("on") {
                reply("${DARK_PURPLE}Enabled Developer mode!")
                NotEnoughUpdates.INSTANCE.config.hidden.dev=true
            }
            thenLiteral("on") { redirect(enable) }
            val disable = thenLiteralExecute("off") {
                NotEnoughUpdates.INSTANCE.config.hidden.dev=false
                reply("${DARK_PURPLE}Disabled Developer mode!")
            }
            thenLiteral("off") { redirect(disable) }
        }
        event.command("chums", "whitelist") {
            thenExecute() {

                reply("$AQUA Whitelist $WHITE: "+NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist.replace(',',' ') )
            }
        }

        event.command("addchum", "addwhitelist") {
            thenArgumentExecute("name", RestArgumentType) { name ->
                val name = this[name]
                NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist += "$name,"
                reply("Added $name to whitelist")
            }
        }.withHelp("Add player to whitelist")

        event.command("removechum", "removewhitelist") {
            thenArgumentExecute("name", RestArgumentType) { name ->
                val name = this[name]
                if(NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist.contains("$name")) {
                    NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist = NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist.replace("$name,", "")
                    NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist = NotEnoughUpdates.INSTANCE.config.macroSafety.whitelist.replace("$name", "")
                    reply("Removed $name from whitelist")
                }else{
                    reply("$name is not whitelisted")
                }
            }
        }.withHelp("Remove a player from whitelist")

        event.command("addAlert", "blacklist") {
            thenArgumentExecute("name", RestArgumentType) { name ->
                val name = this[name]
                NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist += "$name,"
                reply("Added $name to Alert list")
            }
        }.withHelp("Add player to Alert list")

        event.command("removeAlert", "remblacklist") {
            thenArgumentExecute("name", RestArgumentType) { name ->
                val name = this[name]
                if(NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist.contains("$name")) {
                    NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist = NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist.replace("$name,", "")
                    NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist = NotEnoughUpdates.INSTANCE.config.macroSafety.alertlist.replace("$name", "")
                    reply("Removed $name from alert list")
                }else{
                    reply("$name is not in alert list")
                }
            }
        }.withHelp("Remove a player from alert list")
        event.command("editdelay") {
            thenExecute {
                if(!NotEnoughUpdates.INSTANCE.config.fishing.delayEditMode){
                    NotEnoughUpdates.INSTANCE.config.fishing.delayEditMode=true;
                    reply("$BLUE Delay editor enabled.${WHITE} Use arrow keys ( UP / DOWN ) while holding the fishing rod to modify fishing delay")
                }else{
                    NotEnoughUpdates.INSTANCE.config.fishing.delayEditMode=false;
                    reply("$BLUE Delay editor disabled.")
                }
            }
        }.withHelp("Use arrow keys ( UP / DOWN ) to modify fishing delay")

    }
}
