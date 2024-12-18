# objecthash-java
## Build jar with dependencies
`docker run --rm --volume "$PWD":/usr/src/objecthash-java --workdir /usr/src/objecthash-java openjdk:8 bash -c "./gradlew fatJar"`

objecthash-java is a Java version of [Ben Laurie's objecthash](https://github.com/benlaurie/objecthash).

This will be used by [openregister-java]() to provide a verifiable log of data. For more details see the following RFCs:

- [RFC 9: Entry hash](https://github.com/openregister/registers-rfcs/blob/master/content/entry-hash/index.md)
- [RFC 10: Item hash](https://github.com/openregister/registers-rfcs/blob/master/content/item-hash/index.md)

## Differences from the original objecthash algorithm
### Types
The set of types supported this objecthash implementation are:
- Integer
- Unicode string
- List
- Set
- Hash
- Timestamp
- Raw

Timestamp is an extension.
It represents a datetime in UTC with seconds precision, and is encoded by taking the ISO8601 string representation and prefixing with the tag "t".

Raw is also an extension. It is equivalent to the string type but signifies that the value is a raw byte array rather than human readable text.

objecthash-java does not implement Boolean or Float types.

### Redacting primitive values

TODO

## Examples

Hash a single value:

```
ObjectHash.stringToHexDigest("Hello world"); // bd75f78d2f7aa16b72d9fcdb822754a10310ef4cd5b6cca6aa48d98331e246ab
```

## Dependencies
- Java 8

## Licence

[MIT License](LICENSE.txt)
