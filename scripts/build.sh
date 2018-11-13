DIR="$( cd "$( dirname "$0" )" >/dev/null && pwd )"
MAIN="$DIR/../report/main.md"
CSL="$DIR/../report/citation.csl"
BIBTEX="$DIR/../report/biblio.bib"
OUTDIR="$DIR/../dist"
OUTPUT="$OUTDIR/output.pdf"

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