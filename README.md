# Overview

- This is the java implementation of the `LZW Compression and Decompression` algorithm.

- I wrote this code to help me implement the algorithm in `VHDL` but I decided to post it as to help people who might have an intrest in algorithm understand it better or for people who need it to simply use it.

- The binary string I've added and used is an English tongue twister to demonstrate the effectiveness.

  > Current Encoded-to-Decoded ratio is 0.579!

- I've added _extencive comments_ as per the request of the people who messaged me. I wrote it in such a way as to make it easy to understand for even the absolute beginners (with simple OOP knowledge)

> I might add an implementation of the LZ77 algorithm sometime in the future. If you want me to prioritize it, message me.

---

## Technologies

- `JAVA SE 14`

---

## Setup

- Download the `Java SDK` from oracle or from your linux repository.
- Import the `Encoder` and the `Decoder` files into your project or simply clone this project.

---

## Usage

- Prepare the sequence of bytes to be encoded. How you transform them to bits is up to you.

  > This implementation of LZW **ONLY** works when given Strings of bit/byte sentences

- Construct both the `Encoder` and the `Decoder` with the **same** `Bit space value`

```java
Encoder encoder = new Encoder(9);
Decoder decoder = new Decoder(9);
```

- Call the Classes' respective methods to utilize its effectiveness!

```java
encoder.encodeBinary(binaryString);
decoder.decodeBinary(binaryString);
```

> To help with this expantion I had the algo print out "out of memory" when it runs out of memory so that you can increase the allocated memory `(Bit space value)` as you need.
> A bigger than needed `Bit space value` will cause inefficiency on the software side of things and extra costs on the Hardware side.

---

## Stuff to do

- [x] Comment EVERYTHING!
- [x] Make the LZW algo scalable
- [ ] Implement the LZ77 algo (Maybe?)
