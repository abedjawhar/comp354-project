#!/bin/bash

file=${1:-../documentation/test/all_installation.tex}
rm $file
for dir in ../documentation/test/json/installation/*.json; do echo $dir && node installation-test-table-generator.js $dir >> $file; done