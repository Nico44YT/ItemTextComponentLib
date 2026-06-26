package nico.item_text_component.mixin.client;

import net.minecraft.client.font.TextHandler;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import nico.item_text_component.client.ItemTextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextHandler.class)
public abstract class TextHandlerMixin {
    @Inject(method = "getWidth(Lnet/minecraft/text/OrderedText;)F", at = @At("HEAD"))
    public void checkFlag(OrderedText text, CallbackInfoReturnable<Float> cir) {
        ItemTextRenderer.submittedWidth = false;
    }

    @Inject(method = "getWidth(Lnet/minecraft/text/StringVisitable;)F", at = @At("HEAD"))
    public void checkFlag(StringVisitable text, CallbackInfoReturnable<Float> cir) {
        ItemTextRenderer.submittedWidth = false;
    }
}
