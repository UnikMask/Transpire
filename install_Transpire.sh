#!/usr/bin/env bash

sed 's/build\/libs/\/usr\/share/' ./transpire > /usr/share/transpire
cp build/libs/Transpire-all-1.0.jar /usr/share
