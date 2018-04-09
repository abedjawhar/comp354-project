const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

let table = `\\subsubsection{Installation: ${json.os}}` + '\n'
    + generateTableTop(json.os) + ' \n'
    + generateRow('Operating System', json.os) + '\n'
    + generateRow('Requirements', listToTable(json.requirements)) + '\n'
    + generateRow('Installation', listToTable(json.steps)) + '\n'
    + `\\multicolumn{3}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Functionalities tested}}\\\\ \\hline`  + '\n';

Object.keys(json.functionalityTested).forEach((key) => {
    table += generateRow(key, json.functionalityTested[key]) + ' \n';
});

table +='\\end{longtable}\n';

console.log(table);

function generateTableTop(title) {
    return `\\begin{longtable}{|m{${firstColSize}}|l|l|}` + ' \n'
        + `\\caption[]{${title}}` + ' \n'
        + `\\\\\\hline`;
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