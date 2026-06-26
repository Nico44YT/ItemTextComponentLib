package nico.item_text_component.common.text;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import nico.item_text_component.ItemTextComponentCommon;

import java.util.List;

public class ItemText extends MutableText {

    private final ItemTextContent content;

    protected ItemText(ItemStack stack) {
        super(new ItemTextContent(stack), List.of(), Style.EMPTY);
        styled(style -> style.withFont(ItemTextComponentCommon.FONT));

        this.content = (ItemTextContent) getContent();
    }

    public static MutableText of(Item item) {
        return of(item.getDefaultStack());
    }

    public static MutableText of(ItemStack stack) {
        return new ItemText(stack);
    }

    public ItemStack getStack() {
        return content.stack();
    }
}