DIR="$( cd "$( dirname "$0" )" >/dev/null && pwd )"

docker run \
    -v "$DIR/../:/source/" \
    boginw/markdown-pandoc