package com.brokerage.spring;

import com.brokerage.spring.util.AssetUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssetUtilTests {

    @Test
    void isValidAsset() {
        assertTrue(AssetUtil.isValidAsset("USD"));
    }

    @Test
    void isInvalidAsset() {
        assertFalse(AssetUtil.isValidAsset("ABC"));
    }

}
