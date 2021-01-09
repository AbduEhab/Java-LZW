## Overview

- This is the java implementation of the `LZW Compression and Decompression` algorithm.

- I wrote this code to help me implement the algorithm in `VHDL` but I decided to post it as to help people who might have an intrest in algorithm understand it better or for people who need it to simply use it.

- To make it easier for people new to java, I wrote the methods in 2 seperate classes as `static` methods.

> I've added extencive comments as per the request of the people who messaged me. I wrote int in such a way as to make it easy to understand for even the absolute beginners

> I might add an implementation of the LZ77 algorithm sometime in the future. If you want me to prioritize it, message me.

---

## Technologies

- `JAVA SE 15`

---

## Setup

- Download the `Java SDK` from oracle or from your linux repository.
- Import the `Encoder` and the `Decoder` files into your project or simply clone this project.

---

## Usage

- Prepare the sequence of bytes to be encoded. How you transform them to bits is up to you.

  > LZW is most effective when given Strings of sentences

- Call the Classes' respective methods and utilize its effectiveness!

> This code is not meant for production use as apperant by the limit set for the encoder & decoder. However, feel free to modify the code to accommodate bigger sequnces.
> To help with this expantion I had the algo print out "out of memory" when it runs out of memory so that you can increase the allocated memory as you need inorder to not hog .
