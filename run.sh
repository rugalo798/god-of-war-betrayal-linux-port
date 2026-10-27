#!/bin/bash
# God of War: Betrayal - Linux Port launcher
# Usage: ./run.sh [scale]  e.g.: ./run.sh 2

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SCALE="${1:-3}"

java -cp "$SCRIPT_DIR/bin" \
     -Dsun.java2d.opengl=false \
     -Dawt.useSystemAAFontSettings=off \
     gow.Main
