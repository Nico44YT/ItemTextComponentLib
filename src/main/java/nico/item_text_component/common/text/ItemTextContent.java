package nico.item_text_component.common.text;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.TextContent;
import nico.item_text_component.ItemTextComponentCommon;

import java.util.Optional;

public record ItemTextContent(ItemStack stack) implements TextContent {

    @Override
    public <T> Optional<T> visit(StringVisitable.StyledVisitor<T> visitor, Style style) {
        NbtCompound nbtCompound = new NbtCompound();
        stack.writeNbt(nbtCompound);
        return visitor.accept(style.withFont(ItemTextComponentCommon.FONT), nbtCompound.toString());
    }

    @Override
    public <T> Optional<T> visit(StringVisitable.Visitor<T> visitor) {
        NbtCompound nbtCompound = new NbtCompound();
        stack.writeNbt(nbtCompound);
        return visitor.accept(nbtCompound.toString());
    }
}