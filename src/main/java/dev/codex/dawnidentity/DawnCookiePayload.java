package dev.codex.dawnidentity;

import net.minecraft.util.Identifier;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Encodes the public Dawn client-info cookie layout. The brand/ref values are
 * emulation values; they are not issued or authenticated by Dawn.
 */
public final class DawnCookiePayload {
    public static final Identifier KEY = Identifier.of("dawn", "client_info");
    public static final int FORMAT_VERSION = 1;
    public static final String BRAND = "Dawn";
    public static final String REF = "release";

    private DawnCookiePayload() {
    }

    public static byte[] encode() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (DataOutputStream output = new DataOutputStream(bytes)) {
            output.writeByte(FORMAT_VERSION);
            output.writeUTF(BRAND);
            output.writeUTF(REF);
        } catch (IOException exception) {
            throw new UncheckedIOException("Could not encode Dawn client-info cookie", exception);
        }
        return bytes.toByteArray();
    }
}

