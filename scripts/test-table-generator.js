let json = {
    "testerName": "Hrachya",
    "testDate": "1/31/18",
    "className": "com.github.comp354project.model.user.UserService",
    "methodName": "createUser(User)",
    "purpose": "This test suite tests the creation of a user",
    "useCases": [
        "01"
    ],
    "testScenarios": {
        "createUser_withNullUser_shouldThrow": {
            "inputSpecification": {
                "user": null
            },
            "expectedOutput": [
                "ValidationException is thrown"
            ],
            "actualOutput": [
                "ValidationException is thrown"
            ],
            "bugFound": false,
            "purpose": "No null user can be saved in the database"
        },
        "testCreateUser_withInvalidUser_shouldThrow": {
            "inputSpecification": {
                "user": {

                },
                "errors": 4
            },
            "expectedOutput": [
                "ValidationException is thrown",
                "4 exceptions are thrown because missing fields: username, password, firstname, lastname"
            ],
            "actualOutput": [
                "ValidationException is thrown",
                "4 exceptions are thrown because missing fields: username, password, firstname, lastname"
            ],
            "bugFound": false,
            "purpose": "No empty value user can be saved in the database"
        },
        "testCreateUser_withValidUser_shouldReturnUser": {
            "inputSpecification": {
                "user": {
                    "username": "USERNAME",
                    "password": "PASSWORD",
                    "firstName": "FIRSTNAME",
                    "lastName": "LASTNAME"
                }
            },
            "expectedOutput": [
                "User ID was autogenerated upon save",
                "The saved user is the same as the inputted user"
            ],
            "actualOutput": [
                "User ID was autogenerated upon save",
                "The saved user is the same as the inputted user"
            ],
            "bugFound": false,
            "purpose": "A valid user should be inserted in the database"
        },
        "testCreateUser_withExistingUsername_shouldThrow": {
            "inputSpecification": {
                "user": {
                    "username": "USERNAME",
                    "password": "PASSWORD",
                    "firstName": "FIRSTNAME",
                    "lastName": "LASTNAME"
                }
            },
            "expectedOutput": [
                "ValidationException is thrown"
            ],
            "actualOutput": [
                "ValidationException is thrown"
            ],
            "bugFound": false,
            "purpose": "A user cannot be created if the username is already taken"
        }
    }
};

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