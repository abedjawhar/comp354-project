const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

let table = `\\subsubsection{${json.useCase}}` + '\n'
    + generateTableTop(json.useCase) + ' \n'
    + generateRow('Use Case', json.useCase) + '\n'
    + generateRow('Description', json.description) + '\n'
    + generateRow('Description', listToTable(json.usability)) + '\n'
    + '\\end{longtable}\n';

console.log(table);

function generateTableTop(title) {
    return `\\begin{longtable}{|m{${firstColSize}}|l|l|}` + ' \n'
        + `\\caption[]{${title}}` + ' \n'
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
        itemStrings += (ordered ? `${i}. ${item}\\\\ ` : `${item}\\\\ `);
        i += 1;
    });
    return `\\begin{tabular}[c]{@{}l@{}}${itemStrings}\\end{tabular}`;
}