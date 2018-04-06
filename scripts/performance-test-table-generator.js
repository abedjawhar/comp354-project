const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

const maxTestVarCount = 6;

let table = generateTableTop() + '\n'
    + generateRow('Tester Name', json.testerName) + '\n'
    + generateRow('Test Date', json.testDate) + '\n'
    + generateRow('Purpose', json.purpose) + '\n'
    +`\\multicolumn{${1 + maxTestVarCount}}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{System specification}}\\\\ \\hline`  + '\n';

Object.keys(json.systemSpec).forEach((spec) => {
   table += generateRow(spec, json.systemSpec[spec]) + '\n'
});

Object.keys(json.tests).forEach((testName) => {
   table += `\\multicolumn{${(1 + maxTestVarCount)}}{|l|}{${stripUnderscores(testName)}}\\\\ \\hline` + '\n';
   const test = json.tests[testName];
   table += generateRow("CPU usage chart",test["CPU usage chart"]) + '\n';
   table += generateRow("Thread Count chart",test["Thread Count chart"]) + '\n';
   table += generateRow('Events', listToTable(test["events"])) + '\n';
});

table += '\\end{longtable}\n';

console.log(table);


function generateTableTop() {
    let cols = '';
    for (let i = 0; i < maxTestVarCount; i++) {
        cols += 'l|';
    }
    return `\\begin{longtable}{|m{${firstColSize}}|l|${cols}}`
        + '\n'
        + `\\hline`;
}



function generateRow(label, value) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{${maxTestVarCount}}`
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
