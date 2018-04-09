const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

var firstColSize = '7cm';
var dataColSize = '7cm';

let table = `\\subsubsection{${json["Acceptance Scenario"]}}` + '\n'
    + generateTableTop(json["description"]) + ' \n';


json.testCases.forEach((o) => {
   table +=`\\multicolumn{1}{|p{${firstColSize}}|}{${o["expectation"]}} & \\multicolumn{1}`
        + `{p{${dataColSize}}|}{${o["reality"]}}\\\\ \\hline` + '\n';
});

table += '\\end{longtable}\n\n';

firstColSize = '4cm';
dataColSize = '13cm';

table += `\\begin{longtable}{|m{${firstColSize}}|l|}\\hline` + ' \n';
table += generateRow('Scenario Satisfied', json['Scenario Satisfied']) + '\n'
    + generateRow('Comments', json["Comments"]) + '\n';
table += '\\end{longtable}\n\n';

console.log(table);

function generateTableTop(title) {
    return `\\begin{longtable}{|m{${firstColSize}}|l|}` + ' \n'
        + `\\caption[]{${title}}` + ' \n'
        + `\\\\\\hline`;
}

function generateRow(label, value) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{1}`
        + `{p{${dataColSize}}|}{${value}}\\\\ \\hline`;
}

function listToTable(list, ordered) {
    let itemStrings = '';
    let i = 1;
    list.forEach(function(item) {
        itemStrings += (ordered ? `${i}. ${item}\\\\ ` : `${item}\\\\ `);
        i += 1;
    });
    return `\\begin{tabular}[c]{@{}l@{}}${itemStrings}\\end{tabular}`;
}