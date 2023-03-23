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

package io.github.moulberry.notenoughupdates.options.seperateSections;

import com.google.gson.annotations.Expose;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigAccordionId;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorAccordion;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorBoolean;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorSlider;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorText;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigOption;

public class MacroSafety {
	@ConfigOption(name = "Fishing ", desc = "")
	@ConfigEditorAccordion(id = 8)
	public boolean afkFishingRelated = true;

	@Expose
	@ConfigOption(name = "Pause on Player detection", desc = "")
	@ConfigEditorBoolean
	@ConfigAccordionId(id = 8)
	public boolean pauseOnPlayer = false;

	@Expose
	@ConfigOption(
		name = "Whitelist",
		desc = "USe comma(,) to separate names ",
		searchTags = "whitelist"
	)
	@ConfigEditorText
	@ConfigAccordionId(id = 8)
	public String whitelist = "";


	@Expose
	@ConfigOption(name = "Auto kill on Player detection", desc = "")
	@ConfigEditorBoolean
	@ConfigAccordionId(id = 8)
	public boolean autoKillonPlayer=false;
	@Expose
	@ConfigOption(name = "Player Detection Range", desc = "")
	@ConfigEditorSlider(minValue = 0.0F, maxValue = 69.0F, minStep = 1.0F)
	@ConfigAccordionId(id = 8)
	public int playerRange = 10;

}