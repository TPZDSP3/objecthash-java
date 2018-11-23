package uk.gov.objecthash;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


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
    public void hashesByteArrays() {
        byte[] bytes = {0x00, 0x01};
        assertEquals("4b2ef4bb2adb559b4623d0e1680529e84554439a68197241d568c52c8147d6de", ObjectHash.toHexDigest(bytes));
    }
    
    @Test
    public void doesNotNormaliseByteArrays() {
        byte[] bytes1 = "\u03d3".getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = "\u03d2\u0301".getBytes(StandardCharsets.UTF_8);
        
        assertNotEquals(ObjectHash.toHexDigest(bytes1), ObjectHash.toHexDigest(bytes2));
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
        Map<String, ObjectHashable> data = new HashMap<>();
        data.put("foo", new StringValue("bar"));
        data.put("null", null);
        String digest = ObjectHash.toHexDigest(data);
        assertEquals("7ef5237c3027d6c58100afadf37796b3d351025cf28038280147d42fdc53b960", digest);
    }
    
    @Test
    public void hashesSet() {
        Set<ObjectHashable> data = new HashSet<>();
        data.add(new StringValue("foo"));
        data.add(new StringValue("bar"));

        String digest = ObjectHash.toHexDigest(data);
        assertEquals("1d572df95be4d038068133b6a162cbe2172f15bc7d8a020faca7a9a93e8a2649", digest);
    }
    
    @Test
    public void hashesSetIgnoringNull() {
        Set<ObjectHashable> data = new HashSet<>();
        data.add(new StringValue("foo"));
        data.add(new StringValue("bar"));
        data.add(null);

        String digest = ObjectHash.toHexDigest(data);
        assertEquals("1d572df95be4d038068133b6a162cbe2172f15bc7d8a020faca7a9a93e8a2649", digest);   
    }
    
    @Test
    public void hashesList() {
        List<ObjectHashable> data = new ArrayList<>();
        data.add(new StringValue("foo"));
        data.add(new StringValue("bar"));

        String digest = ObjectHash.toHexDigest(data);
        assertEquals("32ae896c413cfdc79eec68be9139c86ded8b279238467c216cf2bec4d5f1e4a2", digest);
    }

    @Test
    public void hashesListIgnoringNull() {
        List<ObjectHashable> data = new ArrayList<>();
        data.add(new StringValue("foo"));
        data.add(new StringValue("bar"));
        data.add(null);

        String digest = ObjectHash.toHexDigest(data);
        assertEquals("32ae896c413cfdc79eec68be9139c86ded8b279238467c216cf2bec4d5f1e4a2", digest);
    }
    
    @Test
    public void hashesListIncludingRawValue() {
        List<ObjectHashable> data = new ArrayList<>();
        data.add(new StringValue("foo"));
        data.add(new RawValue(new StringValue("foo").digest()));
        
        String digest = ObjectHash.toHexDigest(data);

        assertEquals("5b64d51fe38fb3512e8a4dff6a4705bea40df7b617997f526e4205023eacd711", digest);      
    }
    
    @Test
    public void hashesDictOfMixedTypes() {
        Map<String, ObjectHashable> data = new HashMap<>();
        data.put("id", new StringValue("GB"));
        data.put("official-name", new StringValue("The United Kingdom of Great Britain and Northern Ireland"));
        data.put("name", new StringValue("United Kingdom"));
        
        Set<ObjectHashable> citizenNames = new HashSet<>();
        citizenNames.add(new StringValue("Briton"));
        citizenNames.add(new StringValue("British citizen"));
        data.put("citizen-names", new SetValue(citizenNames));

        String digest = ObjectHash.toHexDigest(data);

        assertEquals("45d9392ad17cead3fa46501eba3e5ac237cb46a39f1e175905f00ef6a6667257", digest);
    }
}
