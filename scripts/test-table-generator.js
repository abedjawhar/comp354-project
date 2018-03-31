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

const firstColSize = '3cm';
const dataColSize = '12cm';

const variableCount = getVariableCount();

let table = generateTableTop() + '\n'
    + generateRow('Tester Name', json.testerName) + '\n'
    + generateRow('Test Date', json.testDate) + '\n'
    + generateRow('Class Name', json.className) + '\n'
    + generateRow('Method Name', json.methodName) + '\n'
    + generateRow('Purpose', json.purpose) + '\n'
    + generateRow('Use Cases', listToItems(json.useCases)) + '\n'
    + `\\multicolumn{${(1 + variableCount)}}{|l|}{\\cellcolor[HTML]{C0C0C0}\\textbf{Test Scenarios}}\\\\ \\hline`  + '\n';

Object.keys(json.testScenarios).forEach((scenarioName) => {
    table += `\\multicolumn{${(1 + variableCount)}}{|l|}{${stripUnderscores(scenarioName)}}\\\\ \\hline` + '\n';
});

console.log(table)

function getVariableCount() {
    let vars = 0;
    Object.keys(json.testScenarios).forEach((key) => {
        const varCount = Object.keys(json.testScenarios[key].inputSpecification).length;
        if (varCount > vars) {
            vars = varCount;
        }
    });
    return vars
}

function generateTableTop() {
    let cols = '';
    for (let i = 0; i < variableCount; i++) {
        cols += 'l|';
    }
    return `\\begin{table}[]\\centering\\caption{${json.className}.${json.methodName}`
        + `}\\label{my-label}\\begin{tabular}{|m{${firstColSize}}|l|${cols}}\\hline`;
}

function generateRow(label, value) {
    return `\\cellcolor[HTML]{C0C0C0}\\textbf{${label}} & \\multicolumn{${variableCount}}`
        + `{p{${dataColSize}}|}{${value}}\\\\ \\hline`;
}

function listToItems(list) {
    let itemStrings = '';
    list.forEach(function(item) {
        itemStrings += '\\item ' + item;
    });
    return `\\begin{itemize}${itemStrings}\\end{itemize}`;
}

function stripUnderscores(str) {
    return str.replace(/_/g, '\\_');
}