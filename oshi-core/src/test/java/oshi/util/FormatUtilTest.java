/**
 * MIT License
 *
 * Copyright (c) 2010 - 2020 The OSHI Project Contributors: https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package oshi.util;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormatSymbols;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class FormatUtilTest.
 */
public class FormatUtilTest {

    /** The decimal separator. */
    private static char DECIMAL_SEPARATOR;

    /**
     * Sets the up class.
     */
    @BeforeClass
    public static void setUpClass() {
        // use decimal separator according to current locale
        DecimalFormatSymbols syms = new DecimalFormatSymbols();
        DECIMAL_SEPARATOR = syms.getDecimalSeparator();
    }

    /**
     * Test format bytes.
     */
    @Test
    public void testFormatBytes() {
        assertEquals("Test format bytes.", "0 bytes", FormatUtil.formatBytes(0));
        assertEquals("Test format byte.", "1 byte", FormatUtil.formatBytes(1));
        assertEquals("Test format bytes.", "532 bytes", FormatUtil.formatBytes(532));
        assertEquals("Test format KiByte.", "1 KiB", FormatUtil.formatBytes(1024));
        assertEquals("Test format GiByte.", "1 GiB", FormatUtil.formatBytes(1024 * 1024 * 1024));
        assertEquals("Test format TiByte.", "1 TiB", FormatUtil.formatBytes(1099511627776L));
    }

    /**
     * Test format bytes with decimal separator.
     */
    @Test
    public void testFormatBytesWithDecimalSeparator() {
        String expected1 = "1" + DECIMAL_SEPARATOR + "3 KiB";
        String expected2 = "2" + DECIMAL_SEPARATOR + "3 MiB";
        String expected3 = "2" + DECIMAL_SEPARATOR + "2 GiB";
        String expected4 = "1" + DECIMAL_SEPARATOR + "1 TiB";
        String expected5 = "1" + DECIMAL_SEPARATOR + "1 PiB";
        String expected6 = "1" + DECIMAL_SEPARATOR + "1 EiB";
        assertEquals("Test format KiBytes with decimal separator.", expected1, FormatUtil.formatBytes(1340));
        assertEquals("Test format MiBytes with decimal separator.", expected2, FormatUtil.formatBytes(2400016));
        assertEquals("Test format GiBytes with decimal separator.", expected3, FormatUtil.formatBytes(2400000000L));
        assertEquals("Test format TiBytes with decimal separator.", expected4,
                FormatUtil.formatBytes(1099511627776L + 109951162777L));
        assertEquals("Test format PiBytes with decimal separator.", expected5,
                FormatUtil.formatBytes(1125899906842624L + 112589990684262L));
        assertEquals("Test format EiBytes with decimal separator.", expected6,
                FormatUtil.formatBytes(1152921504606846976L + 115292150460684698L));
    }

    /**
     * Test format decimal bytes.
     */
    @Test
    public void testFormatBytesDecimal() {
        assertEquals("Test format bytesDecimal.", "0 bytes", FormatUtil.formatBytesDecimal(0));
        assertEquals("Test format byteDecimal.", "1 byte", FormatUtil.formatBytesDecimal(1));
        assertEquals("Test format bytesDecimal.", "532 bytes", FormatUtil.formatBytesDecimal(532));
        assertEquals("Test format KbytesDecimal.", "1 KB", FormatUtil.formatBytesDecimal(1000));
        assertEquals("Test format GbytesDecimal.", "1 GB", FormatUtil.formatBytesDecimal(1000 * 1000 * 1000));
        assertEquals("Test format TbytesDecimal.", "1 TB", FormatUtil.formatBytesDecimal(1000000000000L));
    }

    /**
     * Test format decimal bytes with decimal separator.
     */
    @Test
    public void testFormatBytesDecimalWithDecimalSeparator() {
        String expected1 = "1" + DECIMAL_SEPARATOR + "3 KB";
        String expected2 = "2" + DECIMAL_SEPARATOR + "3 MB";
        String expected3 = "2" + DECIMAL_SEPARATOR + "2 GB";
        String expected4 = "1" + DECIMAL_SEPARATOR + "1 TB";
        String expected5 = "3" + DECIMAL_SEPARATOR + "4 PB";
        String expected6 = "5" + DECIMAL_SEPARATOR + "6 EB";
        assertEquals("Test format KBytes with decimal separator.", expected1, FormatUtil.formatBytesDecimal(1300));
        assertEquals("Test format MBytes with decimal separator.", expected2, FormatUtil.formatBytesDecimal(2300000));
        assertEquals("Test format GBytes with decimal separator.", expected3,
                FormatUtil.formatBytesDecimal(2200000000L));
        assertEquals("Test format TBytes with decimal separator.", expected4,
                FormatUtil.formatBytesDecimal(1100000000000L));
        assertEquals("Test format PBytes with decimal separator.", expected5,
                FormatUtil.formatBytesDecimal(3400000000000000L));
        assertEquals("Test format EBytes with decimal separator.", expected6,
                FormatUtil.formatBytesDecimal(5600000000000000000L));
    }

    /**
     * Test format hertz.
     */
    @Test
    public void testFormatHertz() {
        assertEquals("Test format zero Hertz.", "0 Hz", FormatUtil.formatHertz(0));
        assertEquals("Test format one Hertz.", "1 Hz", FormatUtil.formatHertz(1));
        assertEquals("Test format many Hertz.", "999 Hz", FormatUtil.formatHertz(999));
        assertEquals("Test format KHertz.", "1 KHz", FormatUtil.formatHertz(1000));
        assertEquals("Test format MHertz.", "1 MHz", FormatUtil.formatHertz(1000 * 1000));
        assertEquals("Test format GHertz.", "1 GHz", FormatUtil.formatHertz(1000 * 1000 * 1000));
        assertEquals("Test format THertz.", "1 THz", FormatUtil.formatHertz(1000L * 1000L * 1000L * 1000L));
    }

    /**
     * Test format elapsed secs
     */
    @Test
    public void testFormatElapsedSecs() {
        assertEquals("Test format 0 elapsed seconds.", "0 days, 00:00:00", FormatUtil.formatElapsedSecs(0));
        assertEquals("Test format many elapsed seconds.", "0 days, 03:25:45", FormatUtil.formatElapsedSecs(12345));
        assertEquals("Test format elapsed day in seconds.", "1 days, 10:17:36", FormatUtil.formatElapsedSecs(123456));
        assertEquals("Test format elapsed days in seconds.", "14 days, 06:56:07",
                FormatUtil.formatElapsedSecs(1234567));
    }

    /**
     * Test round.
     */
    @Test
    public void testRound() {
        assertEquals("Test round down.", 42.42, FormatUtil.round(42.423f, 2), 0.00001f);
        assertEquals("Test round up.", 42.43, FormatUtil.round(42.425f, 2), 0.00001f);
        assertEquals("Test round double up.", 42.5, FormatUtil.round(42.499f, 2), 0.00001f);
        assertEquals("Test round none.", 42, FormatUtil.round(42, 2), 0.00001f);
    }

    /**
     * Test unsigned int to long.
     */
    @Test
    public void testGetUnsignedInt() {
        assertEquals("Return unsigned int.", 4294967295L, FormatUtil.getUnsignedInt(-1));
    }

    /**
     * Test unsigned string
     */
    @Test
    public void testToUnsignedString() {
        assertEquals("Integer to unsigned string.", "1", FormatUtil.toUnsignedString(0x00000001));
        assertEquals("Big Integer to unsigned string.", "2147483648", FormatUtil.toUnsignedString(0x80000000));
        assertEquals("INT_MAX to unsigned string.", "4294967295", FormatUtil.toUnsignedString(0xffffffff));

        assertEquals("Long to unsigned string.", "1", FormatUtil.toUnsignedString(0x0000000000000001L));
        assertEquals("Big Long to unsigned string.", "9223372036854775808",
                FormatUtil.toUnsignedString(0x8000000000000000L));
        assertEquals("LONG_MAX to unsigned string.", "18446744073709551615",
                FormatUtil.toUnsignedString(0xffffffffffffffffL));
    }

    /**
     * Test format error
     */
    @Test
    public void testFormatError() {
        assertEquals("Format error code.", "0xB66A00A8", FormatUtil.formatError(-1234567000));
    }
}
