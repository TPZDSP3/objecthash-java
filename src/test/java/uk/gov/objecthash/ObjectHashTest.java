package uk.gov.objecthash;
import org.junit.Test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

public class ObjectHashTest {
    @Test
    public void hashesStrings() throws NoSuchAlgorithmException {
        assertEquals(ObjectHash.stringToHexDigest("Hello, Ruby!"), "c92765c1350e6df6800dbadedb3420f398d5f3c7e38a3da48aadb3332280f85f");
    }

    @Test
    public void normalisesStrings() throws NoSuchAlgorithmException {
        assertEquals(ObjectHash.stringToHexDigest("\u03d3"), "f72826713a01881404f34975447bd6edcb8de40b191dc57097ebf4f5417a554d" );
        assertEquals(ObjectHash.stringToHexDigest("\u03d2\u0301"), "f72826713a01881404f34975447bd6edcb8de40b191dc57097ebf4f5417a554d" );
    }    
    
    @Test
    public void hashesIntegers() throws NoSuchAlgorithmException {
        assertEquals(ObjectHash.integerToHexDigest(-1), "f105b11df43d5d321f5c773ef904af979024887b4d2b0fab699387f59e2ff01e" );
        assertEquals(ObjectHash.integerToHexDigest(0), "a4e167a76a05add8a8654c169b07b0447a916035aef602df103e8ae0fe2ff390" );
        assertEquals(ObjectHash.integerToHexDigest(10), "73f6128db300f3751f2e509545be996d162d20f9e030864632f85e34fd0324ce" );
        assertEquals(ObjectHash.integerToHexDigest(1000), "a3346d18105ef801c3598fec426dcc5d4be9d0374da5343f6c8dcbdf24cb8e0b" );
    }
}
