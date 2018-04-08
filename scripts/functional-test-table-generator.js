const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

let table = `\\subsubsection{${json.useCase}}` + '\n';

Object.keys(json.testCases).forEach((testCaseName) => {
    table += generateTableTop(testCaseName) + '\n'
       + generateRow('Test Case', testCaseName) + '\n'
       + generateRow('Description', json.testCases[testCaseName].description) + '\n'
       + generateRow('Input/Steps',  listToTable(json.testCases[testCaseName].input, true)) + '\n'
       + generateRow('Output/Results',  listToTable(json.testCases[testCaseName].output, false)) + '\n'
       + '\\end{longtable}\n';
});

console.log(table);

function generateTableTop() {
    return `\\begin{longtable}{|m{${firstColSize}}|l|l|}` + '\n'
        + `\\hline`;
}

function generateRow(label, value) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{2}`
        + `{p{${dataColSize}}|}{${value}}\\\\ \\hline`;
}

function listToTable(list, ordered) {
    let itemStrings = '';
    let i = 1;
    list.forEach(function(item) {
        itemStrings += (ordered ? `${i}. ${item}\\\\ ` : `$\\bullet$ ${item}\\\\ `);
        i += 1;
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
