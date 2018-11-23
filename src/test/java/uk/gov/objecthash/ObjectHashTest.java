package uk.gov.objecthash;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class ObjectHashTest {
    
    @Test
    public void hashesStrings()  {
        assertEquals(ObjectHash.toHexDigest("Hello, Ruby!"), "c92765c1350e6df6800dbadedb3420f398d5f3c7e38a3da48aadb3332280f85f");
    }

    @Test
    public void normalisesStrings() {
        assertEquals(ObjectHash.toHexDigest("\u03d3"), "f72826713a01881404f34975447bd6edcb8de40b191dc57097ebf4f5417a554d" );
        assertEquals(ObjectHash.toHexDigest("\u03d2\u0301"), "f72826713a01881404f34975447bd6edcb8de40b191dc57097ebf4f5417a554d" );
    }    
    
    @Test
    public void hashesIntegers() {
        assertEquals(ObjectHash.toHexDigest(-1), "f105b11df43d5d321f5c773ef904af979024887b4d2b0fab699387f59e2ff01e" );
        assertEquals(ObjectHash.toHexDigest(0), "a4e167a76a05add8a8654c169b07b0447a916035aef602df103e8ae0fe2ff390" );
        assertEquals(ObjectHash.toHexDigest(10), "73f6128db300f3751f2e509545be996d162d20f9e030864632f85e34fd0324ce" );
        assertEquals(ObjectHash.toHexDigest(1000), "a3346d18105ef801c3598fec426dcc5d4be9d0374da5343f6c8dcbdf24cb8e0b" );
    }    
    
    @Test
    public void hashesTimestamps() {
        var date = LocalDate.parse("2000-01-01");
        Instant instant = date.atStartOfDay(ZoneId.of("UTC")).toInstant();
        assertEquals(ObjectHash.toHexDigest(instant), "cb34961d3d1a44386a73c37eb64c72a2b61ff40fc108abca92ef07c4954a1645" );
    }
    
    @Test
    public void hashesDict() {
        ObjectHash objectHash = new ObjectHash();
        Map<String, ObjectHashable> data = new HashMap<>();
        data.put("foo", new StringValue("bar"));
        data.put("null", null);
        String digest = objectHash.hexDigest(data);
        assertEquals(digest, "7ef5237c3027d6c58100afadf37796b3d351025cf28038280147d42fdc53b960");
    }
}
