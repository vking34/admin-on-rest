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
            const { page, perPage } = params.pagination;
            const { field, order } = params.sort;
            url = `${API_URL}/${resource}`;
            break;
        }

        case GET_ONE: {
            url = `${API_URL}/${resource}/${params.id}`;
            break;
        }

        case CREATE: {
            url = `${API_URL}/${resource}`;
            options.method = 'POST';
            options.body = JSON.stringify(params.data);
            break;
        }

        case UPDATE: {
            url = `${API_URL}/${resource}/${params.id}`;
            options.method = 'PUT';
            let payload = {password: params.data.password, admin: params.data.admin, active: params.data.active};
            options.body = JSON.stringify(payload);
            break;
        }

        case DELETE: {
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
                data: json.map(x => {
                        return { ...x, id: x.username}
                    }),
                total: json.length
            };
        }
        case CREATE:
        {
            console.log(json);
            if(json.status === true){
                return { data: { ...params.data, id: params.data.username } };
            } else{
                // Promise.reject("Department ID may exist");
                // alert("Department ID may exist");
                // return;
                // showNotification("Department ID may exist");
                showNotificationAction("Department ID may exist");
            }
        }



        default:
            return { data: json };
    }
};

export default (type, resource, params) => {
    const { fetchJson } = fetchUtils;
    const { url, options } = convertRESTRequestToHTTP(type, resource, params);
    return fetchJson(url, options)
        .then(response => convertHTTPResponseToREST(response, type, resource, params));
};