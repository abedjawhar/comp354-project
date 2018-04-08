#!/bin/bash

file=${1:-../documentation/test/all_performance.tex}
rm $file
node performance-test-table-generator.js  ../documentation/test/json/performance/yourKit-tests.json >> $file
