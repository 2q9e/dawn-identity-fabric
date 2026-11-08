package dev.codex.dawnidentity;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DawnCookiePayloadTest {
    @Test
    void encodesVersionThenModifiedUtfBrandAndRef() throws Exception {
        try (DataInputStream input = new DataInputStream(new ByteArrayInputStream(DawnCookiePayload.encode()))) {
            assertEquals(DawnCookiePayload.FORMAT_VERSION, input.readUnsignedByte());
            assertEquals(DawnCookiePayload.BRAND, input.readUTF());
            assertEquals(DawnCookiePayload.REF, input.readUTF());
            assertEquals(-1, input.read());
        }
    }
}

