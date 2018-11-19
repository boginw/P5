DIR="$( cd "$( dirname "$0" )" >/dev/null && pwd )"
MAIN="$DIR/../report/main.md"
CSL="$DIR/../report/citation.csl"
BIBTEX="$DIR/../report/biblio.bib"
OUTDIR="$DIR/../dist"
OUTPUT="$OUTDIR/output.pdf"

cp "$DIR/../template/eisvogel.tex" /root/.cabal/share/x86_64-linux-ghc-8.0.2/pandoc-1.19.2.1/data/templates/eisvogel.latex

cd "$DIR/../"

if [ ! -d "$OUTDIR" ]; then
    mkdir "$OUTDIR"
fi

python $DIR/includes.py $MAIN | \
    pandoc --filter pandoc-fignos \
           --filter pandoc-eqnos \
           --filter pandoc-tablenos \
           --filter pandoc-citeproc \
           --csl=$CSL \
           --number-sections \
           --toc \
           --biblio=$BIBTEX \
           --from markdown \
           --template eisvogel \
           --listings \
           -o $OUTPUT