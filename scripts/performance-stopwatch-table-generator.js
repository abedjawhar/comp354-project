const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

var firstColSize = '4cm';
var dataColSize = '13cm';

const titleTableColCount = 2;

let table = generateTableTop(titleTableColCount) + '\n'
    + generateRow('Tester Name', json.testerName, titleTableColCount) + '\n'
    + generateRow('Test Date', json.testDate, titleTableColCount) + '\n'
    + generateRow('Purpose', json.purpose, titleTableColCount) + '\n'
    +`\\multicolumn{${titleTableColCount}}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{System specification}}\\\\ \\hline`  + '\n';

Object.keys(json.systemSpec).forEach((spec) => {
   table += generateRow(spec, json.systemSpec[spec], titleTableColCount) + '\n'
});

table += '\\end{longtable}\n\n';
firstColSize = '9cm';
dataColSize = '8cm';
table += generateTableTop(titleTableColCount) + '\n';

const methodTimes = json["average running time in seconds for each service method"];
Object.keys(methodTimes).forEach((method) => {
  const str = method.substring(method.lastIndexOf(".") + 1);
   table += generateRow(str, methodTimes[method], titleTableColCount) + '\n'
});

table += '\\end{longtable}\n\n';

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
