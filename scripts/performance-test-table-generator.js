const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

const titleTableColCount = 2;
const eventsTableColCount = 2;
const memoryTableColCount = 6;
const cpuTableColCount = 5;

let table = generateTableTop(titleTableColCount) + '\n'
    + generateRow('Tester Name', json.testerName, titleTableColCount) + '\n'
    + generateRow('Test Date', json.testDate, titleTableColCount) + '\n'
    + generateRow('Purpose', json.purpose, titleTableColCount) + '\n'
    +`\\multicolumn{${titleTableColCount}}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{System specification}}\\\\ \\hline`  + '\n';

Object.keys(json.systemSpec).forEach((spec) => {
   table += generateRow(spec, json.systemSpec[spec], titleTableColCount) + '\n'
});

table += '\\end{longtable}\n\n';


Object.keys(json.tests).forEach((testName) => {
   table += generateTableTop(eventsTableColCount) + '\n'
   table += `\\multicolumn{${(eventsTableColCount)}}{|l|}{${stripUnderscores(testName)}}\\\\ \\hline` + '\n';
   const test = json.tests[testName];
   table += generateRow("CPU usage chart",test["CPU usage chart"], eventsTableColCount) + '\n';
   table += generateRow("Thread Count chart",test["Thread Count chart"], eventsTableColCount) + '\n';
   table += generateRow('Events', listToTable(test["Events"]), eventsTableColCount) + '\n';
   table += '\\end{longtable}\n';

   table += '\n' + `\\begin{tabularx}{18cm}{  |*{6}{Y|} }` + '\n';
   table += `\\cline{1-6}`;
   table += `\\multicolumn{6}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Memory}}\\\\ \\hline` + '\n'
             + `\\multicolumn{3}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Heap-Memory}}` + '\n'
             + `& \\multicolumn{3}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Non-Heap Memory}}\\\\ \\hline`
             + '\n'
             + `\\cellcolor[HTML]{C0C0C0}\\textbf{Used}` + '\n'
             + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Allocated}` + '\n'
             + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Limit}` + '\n'
             + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Used}` + '\n'
             + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Allocated}` + '\n'
             + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Limit} \\\\\\hline` + '\n'
             + `${test["Memory"]["Heap Memory"]["Used"]}` + '\n'
             + `& ${test["Memory"]["Heap Memory"]["Allocated"]}` + '\n'
             + `& ${test["Memory"]["Heap Memory"]["Limit"]}` + '\n'
             + `& ${test["Memory"]["Non-Heap Memory"]["Used"]}` + '\n'
             + `& ${test["Memory"]["Non-Heap Memory"]["Used"]}` + '\n'
             + `& ${test["Memory"]["Non-Heap Memory"]["Used"]}` + `\\\\ \\hline` + '\n';
   table += '\\end{tabularx}\n';

    table += `\\begin{tabularx}{18cm}{  |*{5}{Y|} }` + '\n';
    table += `\\cline{1-5}`;
    table += `\\multicolumn{5}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{CPU}}\\\\ \\hline` + '\n'
            + `\\multicolumn{1}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Classes}}` + '\n'
            + `& \\multicolumn{4}{|c|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Threads}}\\\\ \\hline`
            + '\n'
            +  `\\multirow{2}{*}{${test["CPU"]["Classes"]}}`
            + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Currently live}` + '\n'
            + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Currently live daemons}` + '\n'
            + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Peak}` + '\n'
            + `& \\cellcolor[HTML]{C0C0C0}\\textbf{Total created} \\\\` + '\n'
            + `\\cline{2-5}`
            + `& ${test["CPU"]["Threads"]["Currently live"]}` + '\n'
            + `& ${test["CPU"]["Threads"]["Currently live daemons"]}` + '\n'
            + `& ${test["CPU"]["Threads"]["Peak"]}` + '\n'
            + `& ${test["CPU"]["Threads"]["Total created"]}` + `\\\\ \\hline` + '\n';
    table += '\\end{tabularx}\n' + `\\break \\break \\break` + '\n';
});



console.log(table);


function generateTableTop(colCount) {
    let cols = '';
    for (let i = 0; i < colCount - 1; i++) {
        cols += 'l|';
    }
    return `\\begin{longtable}{|m{${firstColSize}}|${cols}}`
        + '\n'
        + `\\hline`;
}



function generateRow(label, value, cols) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{${cols-1}}`
        + `{p{${dataColSize}}|}{${value}}\\\\ \\hline`;
}

function listToTable(list) {
    let itemStrings = '';
    list.forEach(function(item) {
        itemStrings += `${item}\\\\ `;
    });
    return `\\begin{tabular}[c]{@{}l@{}}${itemStrings}\\end{tabular}`;
}
function stripUnderscores(str) {
    return str.replace(/_/g, '\\_');
}

function formatValue(value) {
    if (value && value instanceof Object) {
        const arr = [];
        Object.keys(value).forEach((v) => {
            arr.push(`${v}: ${value[v]}`);
        });
        return ` ${listToTable(arr)}`;
    } else {
        return ` ${value} `;
    }
}
