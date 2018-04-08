const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

let table = generateTableTop() + ' \n';

Object.keys(json).forEach((key) => {
   table += generateRow(key, json[key]) + ' \n';
});
table += '\\end{longtable}\n';
console.log(table);

function generateTableTop() {
    return `\\begin{longtable}{|m{${firstColSize}}|l|l|}` + ' \n'
        + `\\caption[]{Glossary}` + ' \n'
        + `\\hline`;
}

function generateRow(label, value) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{2}`
        + `{p{${dataColSize}}|}{${value}}\\\\ \\hline`;
}