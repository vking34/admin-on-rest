import {
    GET_LIST,
    GET_ONE,
    GET_MANY,
    GET_MANY_REFERENCE,
    CREATE,
    UPDATE,
    DELETE,
    fetchUtils,
    showNotificationAction
} from 'admin-on-rest';

import { stringify } from 'query-string';

const API_URL = 'http://localhost';
let bizweb_stores_resource = "bizweb-stores";
let users_resource = "users";
let pages_resource = "pages";
let accounts_resource = "accounts";

const convertRESTRequestToHTTP =  (type, resource, params) => {

    let url = '';
    const options = {};
    options.headers = new Headers({ Accept: 'application/json' });
    const token = localStorage.getItem('token');

    if(token !== null){
        options.headers.set('Authorization', `Bearer ${token}`);
    }

    switch (type){

        case GET_LIST : {

            // console.log(typeof(resource));
            console.log(type, resource , params);
        
            let pageRequest = params.pagination.page - 1;
            if(typeof params.filter.q !== "undefined"){
                // console.log(true);
                switch (resource){
                    case bizweb_stores_resource : {
                        url = `${API_URL}/${resource}/filter/?alias=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }

                    case users_resource : {
                        url = `${API_URL}/${resource}/filter/?username=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }

                    default : {
                        url = `${API_URL}/${resource}/filter/?name=${params.filter.q}&&page=${pageRequest}`;
                        break;
                    }
                }

            }else {
                // console.log(false);
                url = `${API_URL}/${resource}/?page=${pageRequest}`;
            }

            break;
        }

        case GET_ONE: {

            console.log(type, resource, params);

            url = `${API_URL}/${resource}/${params.id}`;
            break;
        }

        case GET_MANY_REFERENCE: {

            // console.log(type, resource, params);
            let pageRequest = params.pagination.page - 1;
            url = `${API_URL}/${params.target}/${params.id}/${resource}?page=${pageRequest}`;

            break;
        }

        case CREATE: {

            url = `${API_URL}/${resource}`;
            options.method = 'POST';
            options.body = JSON.stringify(params.data);
            break;
        }

        case UPDATE: {

            console.log(type, resource, params);
            url = `${API_URL}/${resource}/${params.id}`;
            options.method = 'PUT';

            var payload = {};

            switch (resource){
                case bizweb_stores_resource:{
                    payload = {
                        alias: params.data.alias,
                        packageInfo: params.data.packageInfo
                    };
                    break;
                }

                case users_resource:{
                    payload = {
                        password: params.data.password,
                        admin: params.data.admin,
                        active: params.data.active
                    };
                    break;
                }
            }

            console.log(payload);
            options.body = JSON.stringify(payload);
            break;
        }

        case DELETE: {
            console.log(type, resource, params);
            url = `${API_URL}/${resource}/${params.id}`;
            options.method = 'DELETE';
            break;
        }

        default:
            throw new Error('Unsupported fetch action ${type}');

    }

    return {url, options};
};

const convertHTTPResponseToREST = (response, type, resource, params) => {
    const { headers, json } = response;
    switch (type) {
        case GET_LIST:
        {
            return {
                data: json.content.map(x => {
                    return {...x, id: x.id}
                }),
                total: json.totalElements
            }
        }

        case GET_MANY_REFERENCE : {
            console.log(type, resource, params);
            console.log(json);
            return json
            ? {
              data : json.content.map(x => {
                  return {...x, id: x.id, parent: params.id}
              }),
              total: json.totalElements
            }
            : null ;
        }

        case CREATE:
        {
            console.log(json);
            if(json.status === true){
                return { data: { ...params.data, id: params.data.username } };
            } else{
                alert("User exists");
            }
        }

        case UPDATE:
        {
            return {
                data: { ...params.data, id: params.id }
            };
        }

        default:
            // console.log(json);
            return { data: json };
    }
};

export default (type, resource, params) => {
    const { fetchJson } = fetchUtils;
    const { url, options } = convertRESTRequestToHTTP(type, resource, params);
    return fetchJson(url, options)
        .then(response => convertHTTPResponseToREST(response, type, resource, params));
};