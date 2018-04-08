#!/bin/bash

file=${1:-../documentation/test/stopwatch.txt}
rm $file

awk -F ' ' '
             NR>1{
                 m_sec[$1":"$2]   += $5
                 c_sec[$1]   += $5
                 m_count[$1":"$2] += 1
                 c_count[$1] += 1
             }
             END{
                 print "method avg.sec";
                 for (a in m_sec) {
                     print a " " m_sec[a] / m_count[a];
                 }
                 print "class avg.sec";
                 for (a in c_sec) {
                      print a " " c_sec[a] / c_count[a];
                 }
             }
         ' '../log/run/timing_column.log' > $file