# lingvo
Use native langage in programming by replacing English keywords with functional substitutes.

The word **_lingvo_** is Esperanto for "language," which is representative of the effort to provide support for a broad range of human and programming languages.

This is inspired by the Domain-Driven Design _Ubiquitous Language_ within an explicit _Bounded Context._ You can't program effectively in a non-English native langage while mixing in English keywords.

# Goals
This is an experimental effort. It would be gratifying to support many human languages and programming languages for every human language. A lot depends on the nuances of each human language, programming language, and the depth of understanding of each by those who submit keyword replacements and their substitutes.

# Guidelines
Create a pull request for each human language and programming language pair.

## Native Human Language Support
Each human language is in a directory named by the ISO 639-1 standard. For example, Spanish is `es`, German is `de`, Italian is `it`, and French is `fr`.

When proposing support for a currently unsupported language, look up and use the two-letter ISO 639-1 code to be used for the base directory. The codes can be found [here](https://en.m.wikipedia.org/wiki/List_of_ISO_639_language_codes).

Examples follow:

```
lingvo/de
lingvo/es
lingvo/fr
lingvo/it
```

Each human language directory will have a `README.md` that describes any  oddities and other special considerations.

# Programming Language Support
Each programming language is in a subdirectory of the supported human language.

Examples follow:

```
lingvo/de/csharp
lingvo/de/java
lingvo/de/js
...
lingvo/es/sharp
lingvo/es/java
lingvo/es/js
...
lingvo/fr/csharp
lingvo/fr/java
lingvo/fr/js
...
lingvo/it/csharp
lingvo/it/java
lingvo/it/js
```

Each programming language subdirectory will have a `README.md` that describes any oddities and other special considerations.

## Examples
Currently the best way to determine how to support human languages with any given programming language is to review an existing set of substitutes. The initial example is Spanish for Java: `lingvo/es/java`

## Libraries
It's best to create a library for each set of human-programming langage substitutes. For Java there is a Maven and/or Gradle build for `lingvo-es-java-1.0.0.jar`. It's anticipated that there will be one for C#: `lingvo-es-csharp-1.0.0.dll`.

Eventually, the goal is to support standard distribution repositories such as Maven Central for JVM languages and NuGet for .NET languages.

# License
This repository is open source. The desire is for improvements to works originating here to be contributed back to **_lingvo_**. Therefore, all support is open source and licensed under MPL 2:

Copyright © 2024-2025 Vaughn Vernon. All rights reserved.

This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain one at [https://mozilla.org/MPL/2.0/](https://mozilla.org/MPL/2.0/).
