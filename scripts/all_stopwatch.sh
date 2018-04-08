#!/bin/bash

file=${1:-../documentation/test/all_stopwatch.tex}
rm $file
node performance-stopwatch-table-generator.js  ../documentation/test/json/performance/stopwatch.json >> $file
