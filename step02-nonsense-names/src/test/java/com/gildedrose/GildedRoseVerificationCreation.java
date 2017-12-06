package com.gildedrose;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GildedRoseVerificationCreation {

    private static List<String> names = List.of("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose",
            "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert");

    public static void main(String[] args) throws IOException {
        final String fileName = "verification.txt";
        createVerificationFile(fileName);
    }

    public static void createVerificationFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int sellIn = -5; sellIn < 15; sellIn++) {
            for (int quality = -5; quality < 55; quality++) {
                writer.write(String.format("After iteration[sellIn:%d, quality:%d]", sellIn, quality));
                writer.newLine();

                for (String name : names) {
                    final Item item = new Item(name, sellIn, quality);
                    Item[] items = new Item[]{item};
                    GildedRose app = new GildedRose(items);
                    app.updateQuality();
                    writer.write(String.format("%s should sell in %d and has quality %d.",
                            item.name, item.sellIn, item.quality));
                    writer.newLine();
                }
                writer.newLine();
            }
            writer.newLine();
        }
        writer.close();
    }

    @Test
    public void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

}
