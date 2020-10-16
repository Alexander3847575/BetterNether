package paulevs.betternether.config.screen;

import java.util.List;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget.PressAction;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import paulevs.betternether.biomes.NetherBiome;
import paulevs.betternether.config.Config;
import paulevs.betternether.registry.BiomesRegistry;

public class BiomesEdgesScreen extends Screen {
	private Screen parrent;
	private ButtonListWidget list;
	private Text header;

	public BiomesEdgesScreen(Screen parrent) {
		super(parrent.getTitle());
		this.parrent = parrent;
	}

	@Override
	protected void init() {
		this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE,
				new PressAction() {
					@Override
					public void onPress(ButtonWidget button) {
						client.openScreen(parrent);
						Config.save();
					}
				}));

		header = new TranslatableText("config.betternether.mod_reload");

		// Biomes Edges //
		final List<NetherBiome> biomes = BiomesRegistry.getAllBiomes();

		this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);

		biomes.forEach((biome) -> {
			final String key = "biome." + biome.getNamespace() + "." + biome.getRegistryName();
			if (biome.hasParentBiome() && biome.getParentBiome().getEdge() == biome) {
				final int size = BiomesRegistry.getDefaultEdgeSize(biome);
				final String path = "biomes." + biome.getNamespace() + ".edge";
				final String name = biome.getRegistryName() + "_size";

				this.list.addSingleOptionEntry(new DoubleOption(biome.getRegistryName(), 0, 32, 1F, (gameOptions) -> {
					return (double) Config.getInt(path, name, size);
				}, (gameOptions, value) -> {
					int val = Math.round(value.floatValue());
					Config.setInt(path, name, size, val);
				}, (gameOptions, doubleOption) -> {
					int val = (int) Math.round(doubleOption.get(gameOptions));
					return new TranslatableText("[" + biome.getNamespace() + "] ").append(new TranslatableText(key).getString() + String.format(": %d", val));
				}));
			}
		});

		this.children.add(list);
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		list.render(matrices, mouseX, mouseY, delta);
		for (AbstractButtonWidget button : this.buttons)
			button.render(matrices, mouseX, mouseY, delta);
		DrawableHelper.drawCenteredText(matrices, this.textRenderer, header, this.width / 2, 14, 16777215);
	}
}