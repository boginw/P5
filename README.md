# P5

Embedded System Project

## Usage

To include another file, the following command can be used:

> !{{relative_path_to_file}}

**NOTE:** this command must be the only content on the line, otherwise it will be ignored.

### Citations

To cite, please add your reference in the `report/biblio.bib` file first. The syntax for citing is as follows:

> `[@cite_key, p. 30] says fooo.`

**NOTE:** The `, p. 30` part is optional, and is only used when a specific page is refered.

## Install & Compile

Although you will not need to install anything (except a Markdown editor) as the [CI](https://circleci.com/gh/Lynhx/P5) will generate artifacts, including a compiled PDF, this can be helpful for taking a glimpse of what the final result might look like.

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

