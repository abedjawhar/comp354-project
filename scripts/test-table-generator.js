const path = process.argv[2];
const json = JSON.parse(require('fs').readFileSync(path, 'utf8'));

const firstColSize = '4cm';
const dataColSize = '13cm';

const maxTestVarCount = getMaxVarCount();

let table = generateTableTop() + '\n'
    + generateRow('Tester Name', json.testerName) + '\n'
    + generateRow('Test Date', json.testDate) + '\n'
    + generateRow('Class Name', json.className) + '\n'
    + generateRow('Method Name', json.methodName) + '\n'
    + generateRow('Purpose', json.purpose) + '\n'
    + generateRow('Use Cases', listToTable(json.useCases)) + '\n'
    + `\\multicolumn{${(1 + maxTestVarCount)}}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Test Scenarios}}\\\\ \\hline`  + '\n';

Object.keys(json.testScenarios).forEach((scenarioName) => {
    table += `\\multicolumn{${(1 + maxTestVarCount)}}{|l|}{${stripUnderscores(scenarioName)}}\\\\ \\hline` + '\n';

    const scenario = json.testScenarios[scenarioName];
    const inputSpecification = scenario.inputSpecification;
    const testVarCount = Object.keys(inputSpecification).length;
    let headerVariableRow = '\\rowcolor[HTML]{C0C0C0} ';
    let valueRow = '\\multirow{-2}{*}{\\cellcolor[HTML]{C0C0C0}\\textbf{Input Specification}} ';
    Object.keys(inputSpecification).forEach((varName, index) => {
        let headerCol = '& ';
        let valueCol = '&';
        // IF a test has less variables than the test that has the most variables,
        // we need to use a multicolumn for the last column to take the whole width
        if (testVarCount < maxTestVarCount && (index + 1) === testVarCount) {
            headerCol += ` \\multicolumn{${maxTestVarCount - index}}{l|}{${varName}} `;
            valueCol += ` \\multicolumn{${maxTestVarCount - index}}{l|}{${formatValue(inputSpecification[varName])}} `;
        } else {
            headerCol += ` ${varName} `;
            valueCol += formatValue(inputSpecification[varName]);
        }
        headerVariableRow += headerCol;
        valueRow += valueCol;
    });
    headerVariableRow += `\\\\ \\cline{2-${(1 + maxTestVarCount)} ` + '\n';
    valueRow += '\\\\ \\hline' + '\n';

    table += headerVariableRow + valueRow;
    table += generateRow('Expected Output', listToTable(scenario.expectedOutput)) + '\n';
    table += generateRow('Actual Output', listToTable(scenario.actualOutput)) + '\n';
    table += generateRow('Bug Found', scenario.bugFound) + '\n';
    table += generateRow('Purpose', scenario.purpose) + '\n';

});

table += '\\end{longtable}\n';

console.log(table);

function getMaxVarCount() {
    let vars = 0;
    Object.keys(json.testScenarios).forEach((key) => {
        const varCount = Object.keys(json.testScenarios[key].inputSpecification).length;
        if (varCount > vars) {
            vars = varCount;
        }
    });
    return vars;
}

function generateTableTop() {
    let cols = '';
    for (let i = 0; i < maxTestVarCount; i++) {
        cols += 'l|';
    }
    return `\\begin{longtable}{|m{${firstColSize}}|l|${cols}}`
        + `\\caption[]{${json.className}.${json.methodName}}`
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