#!/bin/bash

file=${1:-../documentation/test/all_acceptance.tex}
rm $file
for dir in ../documentation/test/json/acceptance/*.json; do echo $dir && node acceptance-test-table-generator.js $dir >> $file; done