package nico.item_text_component.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.font.Glyph;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Style;
import nico.item_text_component.ItemTextComponentCommon;
import nico.item_text_component.client.ItemTextRenderer;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@ApiStatus.Internal
@Mixin(value = TextRenderer.class)
public abstract class TextRendererMixin {

    @Unique
    boolean zeroWidth = false;

    @Inject(method = "method_27516(ILnet/minecraft/text/Style;)F", at = @At("HEAD"))
    public void item_text_component$getFont(int codePoint, Style style, CallbackInfoReturnable<Float> cir) {
        zeroWidth = style.getFont().equals(ItemTextComponentCommon.FONT);
    }

    @WrapOperation(method = "method_27516(ILnet/minecraft/text/Style;)F", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/Glyph;getAdvance(Z)F"))
    public float item_text_component$getAdvance(Glyph instance, boolean bold, Operation<Float> original) {
        if(!ItemTextRenderer.submittedWidth) {
            ItemTextRenderer.submittedWidth = true;
            return ItemTextComponentCommon.WIDTH;
        }

        return zeroWidth ? 0 : original.call(instance, bold);
    }
}
