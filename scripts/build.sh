DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
mkdir $DIR/../dist
python $DIR/includes.py $DIR/../report/main.md | pandoc --filter pandoc-citeproc --csl=$DIR/../report/citation.csl --biblio=$DIR/../report/biblio.bib --from markdown -o $DIR/../dist/output.pdf

if [ $1 = "--evince" ]; then
	evince $DIR/../dist/output.pdf
fi
