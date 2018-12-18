# P5

Embedded System Project

## Usage

To include another file, the following command can be used:

> !{{relative_path_to_file}}

**NOTE:** this command must be the only content on the line, otherwise it will be ignored.

### Directory path

Any occurrence of the string `$DIR$` is replaced with the directory to the opened file.


## Template

Template options can be found [here](https://github.com/Wandmalfarbe/pandoc-latex-template#custom-template-variables).


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

### Citations
Take the source and go to [ZBib](https://zbib.org/)

Paste in the link and press cite.
![](https://imgur.com/a/Gjm9DkN.png)

If this doesn't work, you have to click on the manual entry and fill in as many of the blanks as possible.

At the (near) bottom of the page you can click the dropdown arrow on `Copy to Clipboard` and then `Download BibTeX`
![](https://imgur.com/a/UrRPaPu.png)

Add everything in the document to `report/biblio.bib`. The syntax for citing is as follows:

> `[@cite_key, p. 30] says fooo.`
or
> `Jet fuel can't melt steel beams[@waugh_here_2016].`

> `Citation can belong to a single sentence like this, where the cite-number appears before the sentence-ending period[1].`

> `Citation can sometimes belong to an entire paragraph. Such cases are usually a large number of postulations packed into multiple sentences. In these cases it was deemed necessary to write an entire set of sentences, all (or most) making statements needing citation, which might be from the same source(s). In such cases, it would be inefficient to refer to the same source(s) multiple times, instead of referencing once after the entire paragraph. After the final period.[1]`

**NOTE:** Period goes after the source, and there's no space between the last word of the sentence and the source.

**NOTE:** The `, p. 30` part is optional, and is only used when a specific page is referred.


### References

[Please see this](http://lierdakil.github.io/pandoc-crossref/)

### Misc

In order to have a checkmark (✓) character in the report, use LaTeX-syntax: `\checkmark`

## Docker

A Dockerfile is in the `/docker` directory, you can build this, or just use the published image at `boginw/markdown-pandoc`. To use the image to compile a pdf, run the following:

```bash
./scripts/docker_build.sh
```

Which produces a pdf in the `/dist` folder, called `output.pdf`

## Install & Compile

Although you will not need to install anything (except a Markdown editor) as the [CI](https://circleci.com/gh/boginw/P5) will generate artifacts, including a compiled PDF, this can be helpful for taking a glimpse of what the final result might look like.

### Install
To install you'll need some prerequisites (_these packages are fairly large_).

```bash
sudo apt-get install texlive-latex-base -y
sudo apt-get install texlive-fonts-recommended -y
sudo apt-get install texlive-fonts-extra -y
sudo apt-get install pandoc -y
sudo apt-get install pandoc-citeproc -y
```

### Compile

To compile, just execute the following command:

```bash
./scripts/build.sh
```

If no errors occurred, you should see a folder `dist` in the root of the project, containing the resulting PDF `output.pdf`.
