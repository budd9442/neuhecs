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

package io.github.moulberry.notenoughupdates.miscfeatures;

import io.github.moulberry.notenoughupdates.autosubscribe.NEUAutoSubscribe;
import io.github.moulberry.notenoughupdates.util.Utils;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringUtils;
import scala.Console;

@NEUAutoSubscribe
public class Responses {
	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event) {
		String[] message = event.message.getUnformattedText().split(" ");
		if(message.length < 2 ) return;

		Console.println(message[0]);
		Console.println(message[1]);
		Console.println(message[2]);
		Console.println(message[3]);

		String sender = StringUtils.chop(message[1]);
		if(message[0].equals("To")){
			Utils.addChatMessage(sender + "is sending u a message");
		}
		if(message[1]== "Froudo" && message[2]=="dsc"){
			Minecraft.getMinecraft().theWorld.sendQuittingDisconnectingPacket();
		}

	}
	@SubscribeEvent
	public void onMouseMove(MouseEvent event){
			Console.println(event.x + " " + event.y);
	}
}
