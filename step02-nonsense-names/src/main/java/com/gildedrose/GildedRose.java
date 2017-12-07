package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .forEach(this::updateItemQuality);
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    updateBackstagePassQuality(item);
                }
            }
        } else {
            if (item.quality > 0) {
                if (!item.name.equals(SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }
        }

        updateBasedOnSellIn(item);
    }

    private void updateBackstagePassQuality(Item item) {
        if (item.sellIn < 11) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void updateBasedOnSellIn(Item item) {
        updateSellIn(item);

        if (itemExpired(item)) {
            updateExpiredItemQuality(item);
        }
    }

    private void updateExpiredItemQuality(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        } else {
            if (item.name.equals(BACKSTAGE_PASSES)) {
                item.quality = item.quality - item.quality;
            } else {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS)) {
                        item.quality = item.quality - 1;
                    }
                }
            }
        }
    }

    private boolean itemExpired(Item item) {
        return item.sellIn < 0;
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }
}