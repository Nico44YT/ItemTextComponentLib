package nico.item_text_component.mixin.client;

import net.minecraft.text.Style;
import nico.item_text_component.client.ItemTextRenderer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
@Mixin(targets = "net.minecraft.client.font.TextRenderer$Drawer")
public abstract class TextRendererDrawerMixin {

    @Shadow
    float y;

    @Shadow
    float x;

    @Inject(
            method = "accept",
            at = @At("HEAD"),
            cancellable = true
    )
    private void item_text_component$drawItem(int i, Style style, int j, CallbackInfoReturnable<Boolean> cir) {
        ItemTextRenderer.render(i, style, j, x, y, ($x, $y) -> {
            x += $x;
            y += $y;
        }, cir);
    }
}