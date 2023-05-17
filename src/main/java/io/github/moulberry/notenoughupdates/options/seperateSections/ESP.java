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
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorColour;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigEditorSlider;
import io.github.moulberry.notenoughupdates.core.config.annotations.ConfigOption;

public class ESP {
	@ConfigOption(name = "Lava ", desc = "")
	@ConfigEditorAccordion(id = 18)
	public boolean lavaEsp = false;

	@Expose
	@ConfigOption(name = "Lava ESP", desc = "Warning! unstable asf. use with crashpatch ( unless u wanna crash )")
	@ConfigEditorBoolean
	@ConfigAccordionId(id = 18)
	public boolean lavaESP = false;

	@Expose
	@ConfigOption(name = "Lava ESP scan radius", desc = "Warning! higher values will case lag")
	@ConfigEditorSlider(minValue = 1, maxValue = 150, minStep = 1)
	@ConfigAccordionId(id = 18)
	public double lavaScanRadius = 50;

	@Expose
	@ConfigOption(name = "Lava ESP scan interval ( in Ticks )", desc = "Warning! lower intervals will cause lag")
	@ConfigEditorSlider(minValue = 10, maxValue = 100, minStep = 1)
	@ConfigAccordionId(id = 18)
	public int lavaScanInterval = 20;
	@Expose
	@ConfigOption(name = "Lava render limit", desc = "")
	@ConfigEditorSlider(minValue = 100, maxValue = 3000, minStep = 10)
	@ConfigAccordionId(id = 18)
	public int lavaLimit = 500;
	@Expose
	@ConfigOption(name = "Hide near blocks", desc = "")
	@ConfigEditorSlider(minValue = 5, maxValue = 100, minStep = 1)
	@ConfigAccordionId(id = 18)
	public int nearLimit = 15;
	@Expose
	@ConfigOption(
		name = "Lava ESP Color",
		desc = "",
		searchTags = "color"
	)
	@ConfigEditorColour
	@ConfigAccordionId(id = 18)
	public String lavaColor = "0:255:0:255:0";


	@ConfigOption(name = "Player ", desc = "")
	@ConfigEditorAccordion(id = 1)
	public boolean pesp = false;

	@Expose
	@ConfigOption(name = "Player ESP", desc = "Draws a bounding box around players nearby")
	@ConfigEditorBoolean
	@ConfigAccordionId(id = 1)
	public boolean playerESP = false;

	@Expose
	@ConfigOption(name = "Box Thiccness", desc = "")
	@ConfigEditorSlider(minValue = 1, maxValue = 10, minStep = 1)
	@ConfigAccordionId(id = 1)
	public int thiccness = 3;

}
