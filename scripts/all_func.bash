#!/bin/bash

file=${1:-../documentation/test/all_func.tex}
rm $file
for dir in ../documentation/test/json/functional/*.json; do echo $dir && node functional-test-table-generator.js $dir >> $file; done