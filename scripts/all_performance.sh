#!/bin/bash

file=${1:-../documentation/test/all_performance.tex}
rm $file
for dir in ../documentation/test/json/performance/*.json; do echo $dir && node performance-test-table-generator.js $dir >> $file; done
