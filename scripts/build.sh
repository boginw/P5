DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
mkdir $DIR/../dist
python $DIR/includes.py $DIR/../README.md | pandoc --from markdown -o $DIR/../dist/output.pdf
