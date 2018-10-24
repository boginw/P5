
DIR="$( cd "$( dirname "$0" )" >/dev/null && pwd )"
MAIN="$DIR/../report/main.md"
CSL="$DIR/../report/citation.csl"
BIBTEX="$DIR/../report/biblio.bib"
OUTDIR="$DIR/../dist"
OUTPUT="$OUTDIR/output.pdf"

echo "$DIR/../"
cd "$DIR/../"


if [ ! -d "$OUTDIR" ]; then
    mkdir "$OUTDIR"
fi


python $DIR/includes.py $MAIN | \
    pandoc --filter pandoc-fignos \
           --filter pandoc-citeproc \
           --csl=$CSL \
           --biblio=$BIBTEX \
           --from markdown \
           -o $OUTPUT
