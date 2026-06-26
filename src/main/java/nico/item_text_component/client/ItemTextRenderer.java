package nico.item_text_component.client;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.text.Style;
import nico.item_text_component.ItemTextComponentCommon;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiConsumer;

@ApiStatus.Internal
public class ItemTextRenderer {
    public static DrawContext drawContext;
    public static boolean submittedWidth = true;

    private static float renderX;
    private static float renderY;

    private static int lastChar = 0;
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void render(int position, Style style, int character, float x, float y, BiConsumer<Float, Float> positionChanges, CallbackInfoReturnable<Boolean> cir) {
        if (!ItemTextComponentCommon.FONT.equals(style.getFont())) {
            return;
        }


        if (lastChar > position) {
            if (drawContext != null) {
                try {
                    MatrixStack matrixStack = drawContext.getMatrices();

                    matrixStack.push();

                    float scalar = 0.5f;

                    matrixStack.translate(renderX, renderY, 0);
                    matrixStack.scale(scalar, scalar, scalar);
                    NbtCompound nbtCompound = StringNbtReader.parse(stringBuilder.toString());

                    drawContext.drawItem(ItemStack.fromNbt(nbtCompound), (int) (-ItemTextComponentCommon.WIDTH / scalar), 0);

                    positionChanges.accept(ItemTextComponentCommon.WIDTH, 0f);

                    matrixStack.pop();
                } catch (Exception ignore) {
                }
            }

            stringBuilder = new StringBuilder();
        }

        stringBuilder.append((char) character);
        lastChar = position;

        renderX = x;
        renderY = y;

        cir.setReturnValue(true);
    }
}
