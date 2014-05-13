# Clojure Markov Chains

Here we have a deceptively simple looking Clojure file for generating [Markov chains](http://en.wikipedia.org/wiki/Markov_chain), and using those chains to generate text.

Why? Because it's kinda cool. And I had to write "a significant program of [my] choosing in Clojure".

## How does it work?

At present there is no interface, you just modify the last line of `markov.clj` with some text to be used for the chain (or a file), and how many words you want generated.

Running the file will spit out the fun generated text.

### Sample text

There are four sample files located in the `txt` directory. They could be used for a body of initial data. Those files are:

1. `arcade-fire.txt`: The lyrics to most Arcade Fire songs. Who knows, you might be able to get their next hit from this generator...

2. `lovecraft-at-the-mountains-of-madness.txt`: HP Lovecraft's *At the Mountains of Madness*. For getting random text out of a classic pulp story. Plus it's really large. Like the largest file included. (**WARNING**: It takes forever to parse...)

3. `mahoney-killfilesystem.txt`: A blog post titled *Let's at least start to consider killing the plain old file system* by [Mark Mahoney](http://markm208.blogspot.com). The original can be found at [http://markm208.blogspot.com/2014/01/lets-at-least-start-to-consider-killing.html](http://markm208.blogspot.com/2014/01/lets-at-least-start-to-consider-killing.html)

4. `mahoney-multiposts.txt`: Multiple blog posts (like 5-ish) by [Mark Mahoney](http://markm208.blogspot.com).

## Other Files

- `old-markov.clj`: The original version of this program. Chain generation is more recursive, which caused stack overflows when parsing *At the Mountains of Madness* and the Arcade Fire songs. It works fine with smaller samples.

- `test-markov.clj`: Some tests... 