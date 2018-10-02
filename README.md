# P5

Embedded System Project

## Usage

To include another file, the following command can be used:

> !{{relative_path_to_file}}

**NOTE:** this command must be the only content on the line, otherwise it will be ignored.

## Style

The following describes how to keep the agreed style of formatting.

### Titles and Headlines

In this report The Chicago Manual of Style (CMOS) will be used as style for headlines and titles. The rules are as follow:
1. Capitalize the first and last words in titles and subtitles (but see rule 7), and capitalize all other major words (nouns, pronouns, verbs, adjectives, adverbs, and some conjunctions—but see rule 4).
2. Lowercase the articles *the*, *a*, and *an*.
3. Lowercase prepositions, regardless of length, except when they are used adverbially or adjectivally (*up* in *Look Up*, *down* in *Turn Down*, *on* in *The On Button*, *to* in *Come To*, etc.) or when they compose part of a Latin expression used adjectivally or adverbially (*De Facto*, *In Vitro*, etc.).
4. Lowercase the common coordinating conjunctions *and*, *but*, *for*, *or*, and *nor*.
5. Lowercase *to* not only as a preposition (rule 3) but also as part of an infinitive (*to Run*, *to Hide*, etc.), and lowercase as in any grammatical function.
6. Lowercase the part of a proper name that would be lowercased in text, such as *de* or *von*.
7. Lowercase the second part of a species name, such as *fulvescens* in *Acipenser fulvescens*, even if it is the last word in a title or subtitle.

The list has been yanked from the [CMOS-homepage](https://www.chicagomanualofstyle.org/book/ed17/part2/ch08/psec159.html).

## Install & Compile

Although you will not need to install anything (except a Markdown editor) as the[CI](https://circleci.com/gh/Lynhx/P5) will generate artifacts, including a compiled PDF, this can be helpful for taking a glimpse of what the final result might look like.

### Install
To install you'll need some prerequisites (_these packages are fairly large_).

```bash
sudo apt-get install texlive-latex-base -y
sudo apt-get install texlive-fonts-recommended -y
sudo apt-get install texlive-fonts-extra -y
sudo apt-get install pandoc -y
```

### Compile

To compile, just execute the following command:

```bash
./scripts/build.sh
```

If no errors occurred, you should see a folder `dist` in the root of the project, containing the resulting PDF `output.pdf`.

## Example

Bellow are included files, when compiled you should see complete lyrics

### Mr. Sandman

---

!{{report/sandman/1.md}}

---
