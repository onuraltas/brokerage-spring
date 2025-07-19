package com.brokerage.spring.util;

import java.util.*;

public class AssetUtil {

    public static final String TRY = "TRY";

    private static final Map<String, String> allowedAssetMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("TRY", "TRY"),
            new AbstractMap.SimpleEntry<>("USD", "USD"),
            new AbstractMap.SimpleEntry<>("EUR", "EUR"),
            new AbstractMap.SimpleEntry<>("XAU", "XAU")
    );

    public static boolean isValidAsset(String assetName) {
        return allowedAssetMap.get(assetName) != null;
    }
}
