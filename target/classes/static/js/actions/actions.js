// var {store} = require('../store/department_store');

import thunk from 'redux-thunk';
import fetch from 'cross-fetch';

export const getDepartments = () => {

    console.log("fetch");
    return (dispatch) => {
        fetch("http://localhost/rest-api/departments")
            .then(resp => resp.json())
            .then(data => {
                dispatch({
                    type: "INIT",
                    departments: data
                })
            })
    }

};


// export function getDepartments() {
//     return dispatch => {
//         console.log("getting list...");
//
//         return fetch('http://localhost/rest-api/departments')
//             .then(response => response.json())
//             .then(json => dispatch({
//                     type: "INIT",
//                     departments: json
//                 }
//             ))
//     }
// }

export const createDepartment = (department) => {
    console.log("creating department...");

    return (dispatch) => {
        fetch('/rest-api/departments', {
            method: 'POST'
            ,
            headers: {
            'Accept': 'application/json, */*',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(department)
        })
        .then(res => res.json())
        .then((result) => {
            if (result.status === true) {
                $("#close_add").click();
                alert("Successful");

                dispatch({
                    type: "ADD_DEPARTMENT",
                    new_department : department
                })
            }
            else {
                alert("Failed !");
            }
            })
    }
};

export const removeDepartment = (id, index) => {

    console.log("remove department " + id + "...");

    return (dispatch) => {
        fetch('/rest-api/departments/' + id, { method: 'DELETE'})
            .then(res => res.json())
            .then((result) => {
                    if(result.status === true){

                        dispatch({type: "REMOVE_DEPARTMENT", index: index});
                    }
                    else {
                        alert("Error");
                    }
                }
            );
    }
};

export const editDepartment = (id, department, index) => {

    console.log("action: editing department" + department.title + "... ");

    return (dispatch) => {
        fetch('/rest-api/departments/' + id, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(department)
        })
            .then(resp => resp.json())
            .then((result) => {
                if(result.status === true)
                {
                    alert("Successful");
                    $("#close_edit" + index).click();
                    dispatch({type: "EDIT_DEPARTMENT", index, id , department })
                }else {
                    alert("Error");
                }
            })
    }
};