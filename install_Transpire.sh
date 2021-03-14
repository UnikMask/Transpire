#!/usr/bin/env bash

sed -e 's/build\/libs/\/usr\/share/' -e '5d' ./transpire > /usr/bin/transpire
chmod +x /usr/bin/transpire
cp ./build/libs/Transpire-all-1.0.jar /usr/share/
