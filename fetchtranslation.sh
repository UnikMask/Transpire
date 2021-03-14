#!/bin/bash

curl -L http://139.162.183.117/cgi-bin/trans.tar.gz > translations.tar.gz
tar -xf translations.tar.gz
rm -rf translations.tar.gz