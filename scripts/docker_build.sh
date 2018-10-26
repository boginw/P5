DIR="$( cd "$( dirname "$0" )" >/dev/null && pwd )"
CWD="$(dirname "$DIR")"

docker pull boginw/markdown-pandoc

docker run \
    -it -v $CWD:/source \
    boginw/markdown-pandoc \
    "/source/scripts/build.sh"
