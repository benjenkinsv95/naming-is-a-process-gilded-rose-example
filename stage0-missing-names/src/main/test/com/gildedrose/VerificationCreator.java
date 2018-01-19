package com.gildedrose;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class creates a verification file. A verification file goes through a set of items,
 * then calls {@link GildedRose#updateQuality()}, and then logs the item's new state to the verification file.
 */
public class VerificationCreator {

    public static final String STAGE0_VERIFICATION_FILENAME = "stage0_verification.txt";
    private static List<String> itemNames = List.of("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose",
            "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert");

    public static void main(String[] args) throws IOException {
        new VerificationCreator().createVerificationFile(STAGE0_VERIFICATION_FILENAME);
    }

    /**
     * Creates a verification file. A verification file goes through a set of items,
     * then calls {@link GildedRose#updateQuality()}, and then logs the item's new state to the verification file.
     */
    public void createVerificationFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int sellIn = -5; sellIn < 15; sellIn++) {
            for (int quality = -5; quality < 55; quality++) {
                writer.write(String.format("After iteration[sellIn:%d, quality:%d]", sellIn, quality));
                writer.newLine();

                for (String name : itemNames) {
                    final Item item = new Item(name, sellIn, quality);
                    Item[] items = new Item[]{item};
                    GildedRose app = new GildedRose(items);
                    app.updateQuality();
                    writer.write(String.format("%s should sell in %d with quality %d.",
                            item.name, item.sellIn, item.quality));
                    writer.newLine();
                }
                writer.newLine();
            }
            writer.newLine();
        }
        writer.close();
    }
}
