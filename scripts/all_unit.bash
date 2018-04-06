#!/bin/bash

file=${1:-../documentation/test/all_unit.tex}
rm $file
for dir in ../documentation/test/json/unit/*.json; do echo $dir && node test-table-generator.js $dir >> $file; done