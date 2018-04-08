#!/bin/bash

file=${1:-../documentation/test/all_glossary.tex}
rm $file
for dir in ../documentation/test/json/glossary/*.json; do echo $dir && node glossary-test-table-generator.js $dir >> $file; done